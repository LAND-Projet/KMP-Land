//
//  IconTextButton.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct IconTextButton: View {
    let text: String
    let icon: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            HStack {
                Image(resource: icon)
                    .resizable()
                    .frame(width: 20.63, height: 15)
                    .foregroundColor(Color.buttonContent)

                Text(text)
                    .fontWeight(.bold)
                    .font(.custom(.h3Large))
                    .foregroundColor(Color.buttonContent)
            }
            .padding()
        }.frame(width: 149, height: 48).background(Color.buttonBackground).cornerRadius(50)
    }
}

struct IconTextCancelButton: View {
    let text: String
    let icon: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            HStack {
                Text(text)
                    .fontWeight(.bold)
                    .font(.custom(.h3Large))
                    .foregroundColor(Color.navyBlue)

                Image(resource: icon)
                    .resizable()
                    .frame(width: 20.63, height: 15)
            }
            .padding()
        }.frame(height: 48).background(Color.errorRed).cornerRadius(50)
    }
}
