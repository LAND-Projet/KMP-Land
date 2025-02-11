//
//  Button.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import shared

struct Button: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Spacer()
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.h3Large))
                .foregroundColor(Color.lightGrey)
                .padding()
            Spacer()
        }.frame( height: 75).background(LinearGradient(
            gradient: Gradient(colors: [Color.blueShade, Color.navyBlue]), // Vous pouvez ajuster les couleurs ici
            startPoint: .leading, // Point de départ du dégradé (à gauche)
            endPoint: .trailing // Point d'arrêt du dégradé (à droite)
        )).cornerRadius(50).padding()
    }
}

struct GoogleButton: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            ZStack {
                RoundedRectangle(cornerRadius: 50)
                    .fill(Color.lightGrey)
                    .frame(height: 75)
                    .shadow(color: Color.black.opacity(0.2), radius: 3, x: 0, y: 2)
                    .overlay(
                        RoundedRectangle(cornerRadius: 50)
                            .stroke(Color.blueShade, lineWidth: 5)
                    )
                
                HStack(spacing: 10) {
                    Image(resource:\.googlelogo) // Assurez-vous que l'image existe dans vos assets
                        .resizable()
                        .frame(width: 30, height: 30)
                    
                    Text(text)
                        .foregroundColor(Color.navyBlue)
                        .fontWeight(.bold)
                        .font(.custom("Poppins-Regular", size: 15))
                }
            }
            .frame(height: 75)
        }
        .padding(.horizontal, 16)
    }
}

struct DisableGoogleButton: View {
    let text: String

    var body: some View {
        Button(action: {
        }) {
            ZStack {
                RoundedRectangle(cornerRadius: 50)
                    .fill(LinearGradient(
                        gradient: Gradient(colors: [Color.gray, Color.navyBlue]), // Vous pouvez ajuster les couleurs ici
                        startPoint: .leading, // Point de départ du dégradé (à gauche)
                        endPoint: .trailing // Point d'arrêt du dégradé (à droite)
                    ))
                    .frame(height: 75)
                    .shadow(color: Color.black.opacity(0.2), radius: 3, x: 0, y: 2)
                
                HStack(spacing: 10) {
                    Image(resource:\.googlelogo) // Assurez-vous que l'image existe dans vos assets
                        .resizable()
                        .frame(width: 30, height: 30)
                    
                    Text(text)
                        .foregroundColor(Color.lightGrey)
                        .fontWeight(.bold)
                        .font(.custom("Poppins-Regular", size: 15))
                }
            }
            .frame(height: 75)
        }
        .padding(.horizontal, 16)
    }
}

struct RemoveButton: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Spacer()
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.h3Large))
                .foregroundColor(Color.lightGrey)
                .padding()
            Spacer()
        }.frame( height: 75).background(LinearGradient(
            gradient: Gradient(colors: [Color.errorRed, Color.navyBlue]), // Vous pouvez ajuster les couleurs ici
            startPoint: .leading, // Point de départ du dégradé (à gauche)
            endPoint: .trailing // Point d'arrêt du dégradé (à droite)
        )).cornerRadius(50).padding()
    }
}

struct DisableButton: View {
    let text: String

    var body: some View {
        Button(action: {
        }) {
            Spacer()
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.h3Large))
                .foregroundColor(Color.lightGrey)
                .padding()
            Spacer()
        }.frame( height: 75).background(LinearGradient(
            gradient: Gradient(colors: [Color.gray, Color.navyBlue]), // Vous pouvez ajuster les couleurs ici
            startPoint: .leading, // Point de départ du dégradé (à gauche)
            endPoint: .trailing // Point d'arrêt du dégradé (à droite)
        )).cornerRadius(50).padding().disabled(true)
    }
}

struct TextButton: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.h3Large))
                .foregroundColor(Color.textColor)
                .padding()
        }.frame(height: 48)
            .background(Color.background)
            .cornerRadius(50)
            .shadow(color: Color.black.opacity(0.1), radius: 10, x: 0, y: 5)
    }
}

struct ButtonNoticationAccept: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.btnLarge))
                .foregroundColor(Color.lightGrey)
                .padding()
        }.frame( height: 40).background(LinearGradient(
            gradient: Gradient(colors: [Color.blueShade, Color.navyBlue]),
            startPoint: .leading,
            endPoint: .trailing
        )).cornerRadius(50)
    }
}

struct ButtonNoticationRedAccept: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.btnLarge))
                .foregroundColor(Color.lightGrey)
                .padding()
        }.frame( height: 40).background(LinearGradient(
            gradient: Gradient(colors: [Color.errorRed, Color.navyBlue]),
            startPoint: .leading,
            endPoint: .trailing
        )).cornerRadius(50)
    }
}

struct ButtonNoticationRefuse: View {
    let text: String
    var condition: () -> Void

    var body: some View {
        Button(action: {
            condition()
        }) {
            Text(text)
                .fontWeight(.bold)
                .font(.custom(.btnLarge))
                .foregroundColor(Color.navyBlue)
                .padding()
        }.frame( height: 40).background(Color.lightGrey).cornerRadius(50)
    }
}
