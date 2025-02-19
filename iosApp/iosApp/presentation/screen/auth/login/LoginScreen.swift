//
//  LoginScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import GoogleSignIn
import shared
import SwiftUI
import ToastSwiftUI
import AuthenticationServices

struct LoginScreen: View {
    @State private var user: GIDGoogleUser?
    @State private var selection: String?
    @State private var emailValue: String
    @State private var passwordValue: String
    @State private var isSuccessToast: Bool
    @State private var isFailToast: Bool
    @State private var messageToast: String
    @State private var isLoading: Bool = false
    private var sessionCache = AppStorageSessionCache()
    @ObservedObject var viewModel: AuthSignInViewModel

    init() {
        selection = nil
        emailValue = ""
        passwordValue = ""
        isSuccessToast = false
        isFailToast = false
        messageToast = ""
        viewModel = AuthSignInViewModel()
    }

    var selectionApplyHome: () -> Void {
        return {
            isLoading = true

            viewModel.connectToLandApp(signInData: LandSignInData(email: emailValue, password: passwordValue, token: "")) { result in
                DispatchQueue.main.async {
                    self.messageToast = result!

                    if self.messageToast == IosStringResources(id: SharedRes.strings().connexion_success_message, args: []) {
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
                        let signInData = LandSignInGoogleData(id: user.userID ?? "", email: user.profile?.email ?? "", token: "")

                        viewModel.connectGoogleToLandApp(signInData: signInData) { result in
                            DispatchQueue.main.async {
                                self.messageToast = result!

                                if self.messageToast == IosStringResources(id: SharedRes.strings().connexion_success_message, args: []) {
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
            
            if userCredential.authorizedScopes.contains(.email) {
                print(userCredential.email ?? "No email")
            }
            
            let signInData = LandSignInGoogleData(id: userCredential.user, email: userCredential.email ?? "", token: "")
            
            viewModel.connectGoogleToLandApp(signInData: signInData) { result in
                DispatchQueue.main.async {
                    self.messageToast = result!

                    if self.messageToast == IosStringResources(id: SharedRes.strings().connexion_success_message, args: []) {
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
                    NavigationLink(destination: AuthSignUpScreen(), tag: "SignUp", selection: $selection) {
                        EmptyView()
                    }
                    NavigationLink(destination: AuthMenuScreen(), tag: "Menu", selection: $selection) {
                        EmptyView()
                    }
                    Image(resource: \.logoauth)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 111.23, height: 135.35)
                    Text(IosStringResources(id: SharedRes.strings().signin_text_label, args: []))
                        .font(.custom(.h1Large))
                        .fontWeight(.bold)
                        .foregroundColor(.textColor)
                    Group {
                        OutlinedTextField(
                            textValue: $emailValue,
                            contentText: IosStringResources(id: SharedRes.strings().email_text_label, args: []),
                            labelText: IosStringResources(id: SharedRes.strings().email_text_label, args: []),
                            sfSymbolName: "envelope",
                            isSecure: false,
                            eventValidate: { viewModel.onValidateEmail(emailValue: emailValue) }
                        )
                        OutlinedTextField(
                            textValue: $passwordValue,
                            contentText: IosStringResources(id: SharedRes.strings().password_text_label, args: []),
                            labelText: IosStringResources(id: SharedRes.strings().password_text_label, args: []),
                            sfSymbolName: "lock",
                            isSecure: true,
                            eventValidate: { viewModel.onValidatePassword(passwordValue: passwordValue) }
                        )
                        if isLoading {
                            Loading()
                        } else {
                            Button(text: IosStringResources(id: SharedRes.strings().connexion_text_button, args: []), condition: selectionApplyHome)
                            GoogleButton(text: IosStringResources(id: SharedRes.strings().connexion_google_text_button, args: []), condition: selectionApplyHomeGoogle)
                            SignInWithAppleButton(.signIn) { request in
                                // authorization request for an Apple ID
                                request.requestedScopes = [.fullName, .email]
                            } onCompletion: { result in
                                switch result {
                                case .success(let authorization):
                                    self.handleSuccessfulLogin(with: authorization)
                                    isLoading = false
                                case .failure(let error):
                                    print("Erreur Apple Sign-In : \(error.localizedDescription)")
                                    isLoading = false
                                }
                            }
                            .frame(height: 75)
                            .cornerRadius(50)
                            .padding(.horizontal, 16)
                        }
                        Spacer()
                        HStack(spacing: 5) {
                            Text(IosStringResources(id: SharedRes.strings().navigate_to_enroll_text_label, args: []))
                            Text(IosStringResources(id: SharedRes.strings().enroll_text_button, args: []))
                                .foregroundColor(Color.blueShade)
                                .fontWeight(.bold)
                                .padding(.leading, 5)
                                .onTapGesture {
                                    selection = "SignUp"
                                }
                        }
                        .padding(10)
                        CopyrightText()
                    }.frame(maxHeight: .infinity, alignment: .bottom)
                }
            }
        }.navigationBarBackButtonHidden(true)
            .toast(isPresenting: $isSuccessToast, message: messageToast, icon: .success, backgroundColor: .green)
            .toast(isPresenting: $isFailToast, message: messageToast, icon: .error, backgroundColor: .red)
    }
}
