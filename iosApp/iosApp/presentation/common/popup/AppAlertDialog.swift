//
//  AppAlertDialog.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AlertDialog: View {
    @Binding var isPresented: Bool
    
    var body: some View {
        VStack {
            Text(IosStringResources(id:SharedRes.strings().experimental_version_text_title,args:[]))
                .font(.headline)
                .padding()

            Text(IosStringResources(id:SharedRes.strings().experimental_version_text_label,args:[]))
                .font(Font.custom(.pBody))
                .padding()

            Button(IosStringResources(id:SharedRes.strings().accept_text_button,args:[])) {
                isPresented = false
            }
            .padding()
        }
        .frame(width: 300)
        .background(Color.background)
        .cornerRadius(12)
        .shadow(radius: 10)
    }
}
