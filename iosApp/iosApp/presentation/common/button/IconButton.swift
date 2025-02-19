//
//  IconButton.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import shared

struct IconButton: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void
    
    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack{
            
                RoundedRectangle(cornerRadius: 10, style: .continuous).fill(Color.iconbuttonBackground).frame(width: 36.5,height: 35.96).shadow(radius: 2)
                Image(resource: content).resizable().frame(width: 16.5,height: 15.96)
            }
        }
    }
}

struct IconBottomBar: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void
    let activeButton: Bool
    let labelIcon: String

    var body: some View {
        Button(action: {
            condition()
        }) {
            HStack {
                if activeButton {
                    ZStack {
                        RoundedRectangle(cornerRadius: 15, style: .continuous).fill(Color.buttonBackground).frame(width: 35.73, height: 36.88).shadow(radius: 2)
                        Image(resource: content)
                            .resizable()
                            .frame(width: 16, height: 16)
                    }
                } else {
                    Image(resource: content)
                        .resizable()
                        .frame(width: 16, height: 16)
                    if labelIcon != "" {
                        Text(labelIcon)
                            .font(.custom(.pBody))
                            .foregroundColor(Color.textColor)
                    }
                }
            }
            .padding()
            .background(Color.clear)
        }
    }
}

struct IconIOSButton: View {
    let content: String
    var condition: () -> Void
    
    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack{
            
                RoundedRectangle(cornerRadius: 10, style: .continuous).fill(Color.iconbuttonBackground).frame(width: 36.5,height: 35.96).shadow(radius: 2)
                Image(systemName: content).resizable().frame(width: 16.5,height: 15.96).foregroundColor(Color.errorRed)
            }
        }
    }
}

struct IconSizedButton: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void
    
    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack{
            
                RoundedRectangle(cornerRadius: 10, style: .continuous).fill(Color.iconbuttonBackground).frame(width: 36.5,height: 35.96).shadow(radius: 2)
                Image(resource: content)
            }
        }
    }
}

// ==== IPAD Component =====

struct IconIpadButton: View {
    let content: KeyPath<SharedRes.images, shared.ImageResource>
    var condition: () -> Void
    
    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack{
            
                RoundedRectangle(cornerRadius: 10, style: .continuous).fill(Color.iconbuttonBackground).frame(width: 40,height: 40).shadow(radius: 2)
                Image(resource: content).resizable().frame(width: 25,height: 25)
            }
        }
    }
}

struct IconIOSIpadButton: View {
    let content: String
    var condition: () -> Void
    
    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack{
            
                RoundedRectangle(cornerRadius: 10, style: .continuous).fill(Color.iconbuttonBackground).frame(width: 40,height: 40).shadow(radius: 2)
                Image(systemName: content).resizable().frame(width: 25,height: 25).foregroundColor(Color.errorRed)
            }
        }
    }
}
