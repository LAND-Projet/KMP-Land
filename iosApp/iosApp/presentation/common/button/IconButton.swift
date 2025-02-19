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
