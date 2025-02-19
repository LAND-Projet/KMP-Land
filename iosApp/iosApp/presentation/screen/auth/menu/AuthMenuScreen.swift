//
//  AuthMenuScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthMenuScreen: View {
    @State private var selection: String? = nil

    
    var body: some View {
        ZStack{
            Color.background
                .ignoresSafeArea()
                .overlay(
                    Image(resource: \.reliefbleu)
                        .resizable()
                        .scaledToFill()
                        .opacity(0.2)
                        .edgesIgnoringSafeArea(.all)
                )
            VStack{
                NavigationLink(destination: AuthSignInScreen(),tag: "SignIn", selection: $selection){
                    EmptyView()
                }
                NavigationLink(destination: AuthSignUpScreen(),tag: "SignUp", selection: $selection) {
                    EmptyView()
                }
                Image(resource: \.logoauth)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 111.23, height: 135.35)
                Group{
                    Button(text:IosStringResources(id:SharedRes.strings().connexion_text_button,args:[]), condition: selectionApplySignIn)
                    
                    Button(text:IosStringResources(id:SharedRes.strings().enroll_text_button,args:[]), condition: selectionApplySignUp)
                    
                    CopyrightText()
                }.frame(maxHeight: .infinity,alignment: .bottom)
            }
        }.navigationBarBackButtonHidden(true)
    }
    
    var selectionApplySignIn: () -> Void {
        return {
            selection = "SignIn"
        }
    }
    
    var selectionApplySignUp: () -> Void {
        return {
            selection = "SignUp"
        }
    }
}
