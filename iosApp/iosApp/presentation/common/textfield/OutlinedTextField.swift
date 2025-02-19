//
//  OutlinedTextField.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct OutlinedTextField: View {
    @Binding var textValue: String
    let contentText:String
    let labelText:String
    var sfSymbolName: String
    var isPasswordField:Bool = false
    let eventValidate: () -> ValidateResult
    @State var prompt: ValidateResult = ValidateResult(successful: true,errorMessage: SharedRes.strings().logo_land, errorString: "")
    @State var showPassword: Bool = false
    
    
    var body: some View {
        VStack(alignment: .leading) {
            if(labelText != ""){
                Text(labelText)
                    .font(.custom(.h1Large))
                    .foregroundColor(Color.textFieldContent)
            }
            
            HStack{
                if isPasswordField {
                    Button(action: {
                        showPassword = !showPassword
                    }) {
                        Image(systemName: sfSymbolName)
                            .foregroundColor(Color.textFieldContent)
                            .font(.headline)
                    }
                    if showPassword == false {
                        SecureField("", text: $textValue)
                            .autocapitalization(.none)
                            .foregroundColor(Color.textFieldContent)
                            .onChange(of: textValue) { newValue in
                                print("Name changed to \(textValue)!")
                                prompt = eventValidate()
                            }
                            .placeholder(when: textValue.isEmpty) {
                                    Text(contentText).foregroundColor(Color.textFieldContent).opacity(50)
                            }
                    } else {
                        TextField("", text: $textValue)
                            .autocapitalization(.none)
                            .foregroundColor(Color.textFieldContent)
                            .onChange(of: textValue) { newValue in
                                print("Name changed to \(textValue)!")
                                prompt = eventValidate()
                            }
                            .placeholder(when: textValue.isEmpty) {
                                    Text(contentText).foregroundColor(Color.textFieldContent).opacity(50)
                            }
                    }
                } else {
                    Image(systemName: sfSymbolName)
                        .foregroundColor(Color.textFieldContent)
                        .font(.headline)
                    TextField("", text: $textValue)
                        .autocapitalization(.none)
                        .foregroundColor(Color.textFieldContent)
                        .onChange(of: textValue) { newValue in
                            print("Name changed to \(textValue)!")
                            prompt = eventValidate()
                        }
                        .placeholder(when: textValue.isEmpty) {
                                Text(contentText).foregroundColor(Color.textFieldContent).opacity(50)
                        }
                }
            }.padding(20)
                .frame(height: 75)
                .background(RoundedRectangle(cornerRadius: 50).fill(Color.textFieldBackground).shadow(radius: 2))
            if !prompt.successful {
                Text(IosStringResources(id: (prompt.errorMessage)!, args: []))
                    .fixedSize(horizontal: false, vertical: true)
                    .font(.caption)
                    .foregroundColor(.red)
            }
        }.padding()
    }
}

extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {

        ZStack(alignment: alignment) {
            placeholder().opacity(shouldShow ? 1 : 0)
            self
        }
    }
}

