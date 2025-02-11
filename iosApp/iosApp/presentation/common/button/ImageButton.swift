//
//  ImageButton.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ImageButton: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    let smallText: String
    var clickEvent: () -> Void
    
    var body: some View {
        Button(action: {
            clickEvent()
        }) {
            HStack {
                VStack {
                    Image(resource: content).resizable().frame(width: 16, height: 16)
                    
                    Text(smallText)
                        .font(.system(size: 10))
                        .foregroundColor(Color.textColor)
                        .fixedSize(horizontal: true, vertical: false)
                }
            }
            .padding()
            .background(Color.clear)
        }
    }
}

struct ImageiPadButton: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    let smallText: String
    var clickEvent: () -> Void
    
    var body: some View {
        Button(action: {
            clickEvent()
        }) {
            VStack {
                Image(resource: content).resizable().frame(width: 30, height: 30)
                
                Text(smallText)
                    .font(.custom(.pBody))
                    .foregroundColor(Color.textColor)
                    .fixedSize(horizontal: true, vertical: false)
            }
            .padding()
            .background(Color.clear)
        }
    }
}
