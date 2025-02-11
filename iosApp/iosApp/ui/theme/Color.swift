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
    static let blueShade = Color(hex:colors.BlueShade)
    static let darkBlue = Color(hex:colors.DarkBlue)
    static let navyBlue = Color(hex:colors.NavyBlue)
    static let lightGrey = Color(hex:colors.LightGrey)
    
    static let successGreen = Color(hex:colors.SuccessGreen)
    static let errorRed = Color(hex:colors.ErrorRed)
    static let infoBlue = Color(hex:colors.InfoBlue)
    static let warningYellow = Color(hex:colors.WarningYellow)
    
    static let ticketmasterForegroundLight = Color(hex:colors.ticketmasterForegroundLight)
    static let ticketmasterForegroundDark = Color(hex:colors.ticketmasterForegroundDark)
    static let ticketmasterBackgroundLight = Color(hex:colors.ticketmasterBackgroundLight)
    static let ticketmasterBackgroundDark = Color(hex:colors.ticketmasterBackgroundDark)
    
    static let primaryColor = Color(light: .blueShade, dark: .navyBlue)
    static let background = Color(light: .lightGrey, dark: .navyBlue)
    static let onPrimary = Color(light: .lightGrey, dark: .lightGrey)
    static let onBackground = Color(light: .navyBlue, dark: .lightGrey)
    static let onBackgroundTicketMaster = Color(light: .ticketmasterBackgroundLight, dark: .ticketmasterBackgroundDark)
    static let onForegroundTicketMaster = Color(light: .ticketmasterForegroundLight, dark: .ticketmasterForegroundDark)
    static let surface = Color(light: .darkBlue, dark: .darkBlue)
    static let onSurface = Color(light: .lightGrey, dark: .lightGrey)
    
    static let buttonBackground = Color(light: .lightGrey, dark: .blueShade)
    static let buttonContent = Color(light: .blueShade, dark: .lightGrey)
    
    static let iconbuttonBackground = Color(light: .lightGrey, dark: .navyBlue)
    static let iconbuttonContent = Color(light: .blueShade, dark: .lightGrey)
    
    static let clickableIconContent = Color(light: .navyBlue, dark: .lightGrey)
    
    static let textColor = Color(light: .navyBlue, dark: .lightGrey)
    static let backgroundTop = Color(light: .blueShade, dark: .navyBlue)
    
    static let buttonFeedbackBackground = Color(light: .blueShade, dark: .lightGrey)
    static let feedbackBoxColor = Color(light: .lightGrey, dark: .navyBlue)
    static let feedbackTextColor = Color(light: .navyBlue, dark: .lightGrey)
    
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
