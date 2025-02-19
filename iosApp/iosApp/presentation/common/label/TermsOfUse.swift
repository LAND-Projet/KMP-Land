//
//  TermsOfUse.swift
//  iosApp
//
//  Created by Darren on 2025-02-19.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import shared
import Foundation

struct TermsOfUse: View {
    @Binding var termsAccepted: Bool
    
    var body: some View {
        HStack(spacing: 5){
            Button(action: {
                termsAccepted.toggle()
            }) {
                HStack {
                    Image(systemName: termsAccepted ? "checkmark.square.fill" : "square")
                }
            }
            Text(IosStringResources(id:SharedRes.strings().accept_use_condition,args:[])).font(.custom(.pBody))
            
            Text(IosStringResources(id:SharedRes.strings().terms_of_use_text_label,args:[]))
                .font(.custom(.pBody))
                .foregroundColor(.blueShade)
                .onTapGesture {
                    guard let url = URL(string: "https://www.termsfeed.com/live/ff8a726a-3f98-40b1-9987-ce642a96e5c0") else { return }
                    UIApplication.shared.open(url)
                }
        }.padding()
    }
}
