//
//  Color.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension SwiftUI.Color{
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()

    static let darkBackground = Color(hex: colors.DarkBackground)
    static let darkForeground = Color(hex: colors.DarkForeground)
    static let lightBackground = Color(hex: colors.LightBackground)
    static let lightForeground = Color(hex: colors.LightForeground)

    static let lightBottomTopBarBackground = Color(hex: colors.LightBottomTopBackground)
    static let darkBottomTopBarBackground = Color(hex: colors.DarkBottomTopBackground)

    static let successColor = Color(hex: colors.SuccessGreen)
    static let errorColor = Color(hex: colors.ErrorRed)
    static let infoColor = Color(hex: colors.InfoBlue)
    static let warningColor = Color(hex: colors.WarningYellow)
    static let switchTrackUnchecked = Color(hex: colors.uncheckedSwitch)
    
    static let primaryColor = Color(light: .lightForeground, dark: .darkForeground)
    static let background = Color(light: .lightBackground, dark: .darkBackground)
    static let onPrimary = Color(light: .lightBackground, dark: .darkBackground)
    static let onBackground = Color(light: .lightForeground, dark: .darkForeground)
    static let surface = Color(light: .darkBackground, dark: .darkBackground)
    static let onSurface = Color(light: .lightBackground, dark: .lightBackground)
    
    static let bottomBarBackground = Color(light: .lightBottomTopBarBackground, dark: .darkBottomTopBarBackground)
    static let topBarBackground = Color(light: .lightBottomTopBarBackground, dark: .darkBottomTopBarBackground)

    static let buttonFeedbackBackground = .infoColor
    static let feedbackBoxBackground = Color(light: .lightBackground, dark: .darkBackground)
    static let feedbackTextForeground = Color(light: .lightForeground, dark: .darkForeground)

    static let buttonBackground = Color(light: .lightBackground, dark: .darkBackground)
    static let buttonContent = Color(light: .lightForeground, dark: .darkForeground)

    static let iconbuttonBackground = Color(light: .lightBackground, dark: .darkBackground)
    static let iconbuttonContent = Color(light: .lightForeground, dark: .darkForeground)

    static let clickableIconContent = Color(light: .lightForeground, dark: .darkForeground)
    static let textColor = Color(light: .lightForeground, dark: .darkForeground)

    static let profilContentTop = Color(light: .lightBackground, dark: .darkBackground)
    static let profilContent = Color(light: .lightForeground, dark: .darkForeground)

    static let textFieldBackground = Color(light: .darkForeground, dark: .lightBackground)
    static let textFieldContent = Color(light: .lightForeground, dark: .lightForeground)

    static let popUpBackground = Color(light: .lightBackground, dark: .darkBackground)
    static let popUpContent = Color(light: .lightForeground, dark: .darkForeground)
    
}

private extension SwiftUI.Color{
    init(light: Self, dark: Self) {
        if UITraitCollection.current.userInterfaceStyle == .dark {
            self = dark
        } else {
            self = light
        }
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
