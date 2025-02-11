//
//  RegisterScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import GoogleSignIn
import shared
import SwiftUI
import AuthenticationServices

struct RegisterScreen: View {
    @State private var user: GIDGoogleUser?
    @State private var selection: String?
    @State private var emailValue: String
    @State private var usernameValue: String
    @State private var typeAccountValue: Bool
    @State private var passwordValue: String
    @State private var repeatedPasswordValue: String
    @State private var isSuccessToast: Bool
    @State private var isFailToast: Bool
    @State private var messageToast: String
    @State private var isLoading: Bool = false
    @State private var termsAccepted = false
    private var sessionCache = AppStorageSessionCache()
    @ObservedObject var viewModel: AuthSignUpViewModel

    init() {
        selection = nil
        emailValue = ""
        usernameValue = ""
        typeAccountValue = false
        passwordValue = ""
        repeatedPasswordValue = ""
        isSuccessToast = false
        isFailToast = false
        messageToast = ""
        viewModel = AuthSignUpViewModel()
    }

    var selectionApplyHome: () -> Void {
        return {
            isLoading = true

            let user = LandSignUpData(
                username: usernameValue,
                email: emailValue,
                password: passwordValue,
                repeatPassword: repeatedPasswordValue,
                IsPrivate: typeAccountValue,
                token: ""
            )

            viewModel.enrollToLandApp(signUpData: user) { result in
                DispatchQueue.main.async {
                    self.messageToast = result!

                    if self.messageToast == IosStringResources(id: SharedRes.strings().enroll_success_message, args: []) {
                        self.isSuccessToast = true
                        selection = "Home"
                    } else {
                        self.isFailToast = true
                    }
                    isLoading = false
                }
            }
        }
    }

    var selectionApplyHomeGoogle: () -> Void {
        return {
            isLoading = true

            guard let rootViewController = UIApplication.shared.windows.first?.rootViewController else {
                print("Erreur : Impossible d'obtenir rootViewController")
                isLoading = false
                return
            }

            Task {
                do {
                    let signInResult = try await GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController)
                    let user = signInResult.user
                    
                    if let idToken = user.idToken?.tokenString {
                        let userData = LandSignUpGoogleData(
                            id: user.userID ?? "",
                            fullName: user.profile?.name ?? "",
                            username: user.profile?.givenName ?? "",
                            email: user.profile?.email ?? "",
                            photoUrl: "", // user.profile?.imageURL(withDimension: 200)?.absoluteString ?? ""
                            IsPrivate: true,
                            token: ""
                        )
                        
                        viewModel.enrollGoogleToLandApp(signUpData: userData) { result in
                            DispatchQueue.main.async {
                                self.messageToast = result!

                                if self.messageToast == IosStringResources(id: SharedRes.strings().enroll_success_message, args: []) {
                                    self.isSuccessToast = true
                                    selection = "Home"
                                } else {
                                    self.isFailToast = true
                                }
                                isLoading = false
                            }
                        }
                    } else {
                        print("Erreur : Impossible de récupérer le token ID")
                        isLoading = false
                    }
                } catch {
                    print("Erreur Google Sign-In : \(error.localizedDescription)")
                    isLoading = false
                }
            }
        }
    }


    private func handleSuccessfulLogin(with authorization: ASAuthorization) {
        if let userCredential = authorization.credential as? ASAuthorizationAppleIDCredential {
            print("this is the user ID :")
            print(userCredential.user)
            
            if userCredential.authorizedScopes.contains(.fullName) {
                print(userCredential.fullName?.givenName ?? "No given name")
            }
            
            if userCredential.authorizedScopes.contains(.email) {
                print(userCredential.email ?? "No email")
            }
            
            // Vous pouvez maintenant utiliser ces informations pour inscrire l'utilisateur
            let userData = LandSignUpGoogleData(
                id: userCredential.user,
                fullName: userCredential.fullName?.givenName ?? "",
                username: userCredential.fullName?.nickname ?? "",
                email: userCredential.email ?? "",
                photoUrl: "",
                IsPrivate: true,
                token: ""
            )
            
            viewModel.enrollGoogleToLandApp(signUpData: userData) { result in
                DispatchQueue.main.async {
                    self.messageToast = result!

                    if self.messageToast == IosStringResources(id: SharedRes.strings().enroll_success_message, args: []) {
                        self.isSuccessToast = true
                        selection = "Home"
                    } else {
                        self.isFailToast = true
                    }
                }
            }
        }
    }


    var body: some View {
        ZStack {
            Color.background
                .ignoresSafeArea()
                .overlay(
                    Image(resource: \.reliefbleu)
                        .resizable()
                        .scaledToFill()
                        .opacity(0.2)
                        .edgesIgnoringSafeArea(.all)
                )
            ScrollView(showsIndicators: false) {
                VStack {
                    NavigationLink(destination: HomeScreen(), tag: "Home", selection: $selection) {
                        EmptyView()
                    }
                    NavigationLink(destination: OnboardingScreen(), tag: "Onboarding", selection: $selection) {
                        EmptyView()
                    }
                    NavigationLink(destination: AuthSignInScreen(), tag: "SignIn", selection: $selection) {
                        EmptyView()
                    }

                    Image(resource: \.logoauth)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 111.23, height: 135.35)
                    Text(IosStringResources(id: SharedRes.strings().signup_text_label, args: []))
                        .font(.custom(.h1Large))
                        .fontWeight(.bold)
                        .foregroundColor(Color.textColor)

                    Group {
                        LandOutlinedTextField(
                            textValue: $emailValue,
                            contentText: IosStringResources(id: SharedRes.strings().email_text_label, args: []),
                            labelText: IosStringResources(id: SharedRes.strings().email_text_label, args: []),
                            sfSymbolName: "envelope",
                            isSecure: false,
                            eventValidate: { viewModel.onValidateEmail(emailValue: emailValue) }
                        )
                        LandOutlinedTextField(
                            textValue: $usernameValue,
                            contentText: IosStringResources(id: SharedRes.strings().username_text_label, args: []),
                            labelText: IosStringResources(id: SharedRes.strings().username_text_label, args: []),
                            sfSymbolName: "person",
                            isSecure: false,
                            eventValidate: { viewModel.onValidateUsername(usernameValue: usernameValue) }
                        )
                        LandOutlinedTextField(
                            textValue: $passwordValue,
                            contentText: IosStringResources(id: SharedRes.strings().password_text_label, args: []),
                            labelText: IosStringResources(id: SharedRes.strings().password_text_label, args: []),
                            sfSymbolName: "lock",
                            isSecure: true,
                            eventValidate: { viewModel.onValidatePassword(passwordValue: passwordValue) }
                        )
                        LandOutlinedTextField(
                            textValue: $repeatedPasswordValue,
                            contentText: IosStringResources(id: SharedRes.strings().repeated_password_text_label, args: []),
                            labelText: "",
                            sfSymbolName: "lock",
                            isSecure: true,
                            eventValidate: { viewModel.onValidateRepeatPassword(passwordValue: passwordValue, repeatedPasswordValue: repeatedPasswordValue) }
                        )
                        Toggle(IosStringResources(id: SharedRes.strings().type_account_text_label, args: []), isOn: $typeAccountValue)
                            .frame(width: 200)
                        if typeAccountValue {
                            Text(IosStringResources(id: SharedRes.strings().private_text_label, args: []))
                        } else {
                            Text(IosStringResources(id: SharedRes.strings().public_text_label, args: []))
                        }

                        if termsAccepted {
                            if isLoading {
                                LandLoading()
                            } else {
                                LandButton(text: IosStringResources(id: SharedRes.strings().enroll_text_button, args: []), condition: selectionApplyHome)
                                LandGoogleButton(text: IosStringResources(id: SharedRes.strings().enroll_google_text_button, args: []), condition: selectionApplyHomeGoogle)
                                SignInWithAppleButton(.signUp) { request in
                                    request.requestedScopes = [.fullName, .email]
                                } onCompletion: { result in
                                    switch result {
                                    case .success(let authorization):
                                        self.handleSuccessfulLogin(with: authorization)
                                        isLoading = false
                                    case .failure(let error):
                                        print("Erreur Apple Sign-In : \(error.localizedDescription)")
                                        isFailToast = true
                                        isLoading = false
                                    }
                                }
                                .frame(height: 75)
                                .cornerRadius(50)
                                .padding(.horizontal, 16)
                            }
                        } else {
                            LandDisableButton(text: IosStringResources(id: SharedRes.strings().enroll_text_button, args: []))
                            LandDisableGoogleButton(text: IosStringResources(id: SharedRes.strings().enroll_google_text_button, args: []))
                            SignInWithAppleButton(.signUp) { request in
                                
                            } onCompletion: { result in
                                
                            }
                            .frame(height: 75)
                            .cornerRadius(50)
                            .padding(.horizontal, 16)
                            .disabled(true)
                        }
                        LandTermsOfUse(termsAccepted: $termsAccepted)

                        Spacer()
                        HStack(spacing: 5) {
                            Text(IosStringResources(id: SharedRes.strings().navigate_to_connexion_text_label, args: []))
                                .foregroundColor(Color.textColor)
                            Text(IosStringResources(id: SharedRes.strings().connexion_text_button, args: []))
                                .foregroundColor(Color.blueShade)
                                .fontWeight(.bold)
                                .padding(.leading, 5)
                                .onTapGesture {
                                    selection = "SignIn"
                                }
                        }.padding(10)
                        LandCopyrightText()
                    }.frame(maxHeight: .infinity, alignment: .bottom)
                }
            }
        }.navigationBarBackButtonHidden(true)
            .toast(isPresenting: $isSuccessToast, message: messageToast, icon: .success, backgroundColor: .green)
            .toast(isPresenting: $isFailToast, message: messageToast, icon: .error, backgroundColor: .red)
    }
}
