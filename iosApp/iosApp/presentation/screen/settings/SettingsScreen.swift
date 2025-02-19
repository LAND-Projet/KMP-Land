//
//  SettingsScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SettingsScreen: View {
    @State private var selection: String? = nil
    @State private var isSuccessRemoveUserToast: Bool = false
    @State private var isFailRemoveUserToast: Bool = false
    @ObservedObject var viewModel: ParameterViewModel
    
    init() {
        viewModel = ParameterViewModel()
    }
    
    var handleEraseAccount: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Remove_Account")
            viewModel.eraseAccount { result in
                if result.successful {
                    viewModel.isRemovePopUpOpen = false
                    isSuccessRemoveUserToast = true
                    selection = "AuthMenu"
                } else {
                    viewModel.isRemovePopUpOpen = false
                    isFailRemoveUserToast = true
                }
            }
        }
    }
    
    var body: some View {
        ZStack{
            VStack{
                NavigationLink(destination: ProfilScreen(userID: viewModel.userId, yourProfil: true),tag: "Profil", selection: $selection){
                    EmptyView()
                }
                NavigationLink(destination: AuthMenuScreen(), tag: "AuthMenu", selection: $selection) {
                    EmptyView()
                }
                
                ScrollView(showsIndicators: false) {
                    VStack{
                        arameterInfoSection(
                            eventClickProfilPicture: viewModel.modifyProfilPicture,
                            eventClickUsername: viewModel.modifyUsername,
                            verifyUsername: viewModel.verifyUsername
                        )
                        ParameterPrivateInfoSection(
                            eventClickPassword: viewModel.modifyPassword,
                            eventClickTypeAccount: viewModel.modifyTypeAccount,
                            verifyPassword: viewModel.verifyPassword,
                            verifyRepeatedPassword: viewModel.verifyRepeatedPassword
                        )
                        RemoveButton(
                            text: IosStringResources(id: SharedRes.strings().delete_account, args: []),
                            condition: {
                                viewModel.isRemovePopUpOpen = true
                            }
                        )
                        Spacer()
                    }.padding(.top, 70)
                }
            }.overlay(
                ZStack{
                    RoundedRectCorner(radius: 50, corners: [.bottomLeft, .bottomRight])
                        .frame(maxWidth: .infinity)
                        .frame(height: 106)
                        .foregroundColor(Color.background)
                        .shadow(color: Color.black.opacity(0.5), radius: 2, x: 0, y: 2)
                    ParameterTopBar(selection: $selection)
                }.frame(height: 106)
                    .edgesIgnoringSafeArea(.top),
                alignment: .top
            ).background(
                Color.background
                    .ignoresSafeArea()
                    .overlay(
                        Image(resource: \.reliefbleu)
                            .resizable()
                            .scaledToFill()
                            .opacity(0.2)
                            .edgesIgnoringSafeArea(.all)
                    )
            ).toast(
                isPresenting: $isSuccessRemoveUserToast,
                message: IosStringResources(id: SharedRes.strings().delete_account_success, args: []),
                icon: .success,
                backgroundColor: .green
            )
            .toast(
                isPresenting: $isFailRemoveUserToast,
                message: IosStringResources(id: SharedRes.strings().delete_account_failure, args: []),
                icon: .error,
                backgroundColor: .red
            ).navigationBarBackButtonHidden(true)
            if viewModel.isRemovePopUpOpen {
                BoxRemoveUserPopUp(
                    eventRemoveUserClick: handleEraseAccount,
                    eventCancelClick: {
                        viewModel.isRemovePopUpOpen = false
                    }
                )
            }
        }
    }
}
