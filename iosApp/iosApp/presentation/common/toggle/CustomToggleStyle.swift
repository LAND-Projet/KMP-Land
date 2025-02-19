//
//  CustomToggleStyle.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct CustomToggleStyle: ToggleStyle {
    
    func makeBody(configuration: Self.Configuration) -> some View {
        HStack {
            Spacer()
            
            RoundedRectangle(cornerRadius: 16)
                .fill(configuration.isOn ? Color.successColor : Color.gray)
                .frame(width: 51, height: 31)
                .overlay(
                    Circle()
                        .fill(Color.lightBackground)
                        .padding(3)
                        .overlay(
                            Circle()
                                .stroke(configuration.isOn ? Color.successColor : Color.gray, lineWidth: 1)
                        )
                        .shadow(color: .gray, radius: 3, x: 0, y: 3)
                        .offset(x: configuration.isOn ? 10 : -10)
                )
                .onTapGesture { configuration.isOn.toggle() }
                .animation(.easeInOut, value: configuration.isOn)
            
            configuration.label
                .foregroundColor(.black)
            
            Spacer()
        }
        .padding(.horizontal)
    }
}
