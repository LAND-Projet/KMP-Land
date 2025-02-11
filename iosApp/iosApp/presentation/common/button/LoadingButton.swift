//
//  LoadingButton.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct LoadingButton: View {
    var body: some View {
        Button(action: {}) {
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: Color.lightGrey))
        }.frame(width: 322,height: 75).background(LinearGradient(
            gradient: Gradient(colors:[Color.blueShade, Color.navyBlue]),
            startPoint: .leading,
            endPoint: .trailing
        )).cornerRadius(50)
            .disabled(true)
        
    }
}

struct LoadingButtonParameter: View {
    var body: some View {
        Button(action: {}) {
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: Color.lightGrey))
        }.frame(width: 150,height: 50).background(LinearGradient(
            gradient: Gradient(colors:[Color.blueShade, Color.navyBlue]),
            startPoint: .leading,
            endPoint: .trailing
        )).cornerRadius(50)
            .disabled(true)
        
    }
}

struct LoadingIcon: View {
    var body: some View {
        ProgressView()
            .progressViewStyle(CircularProgressViewStyle(tint: Color.textColor))
    }
}
