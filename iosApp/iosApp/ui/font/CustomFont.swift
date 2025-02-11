//
//  CustomFont.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//


import Foundation
import SwiftUI

enum CustomFont{
    case h1Large
        case h1Medium
        case h1Light
        case h2Large
        case h2Medium
        case h2Light
        case h3Large
        case pBoldBody
        case pBody
        case pSurveyBody
        case btnLarge
        case btnSmall
        
        struct FontValues {
            let fontName: String
            let fontSize: CGFloat
        }
        
        var values: FontValues {
            switch self {
            case .h1Large:
                return FontValues(fontName: "Comfortaa-Bold", fontSize: 25)
            case .h1Medium:
                return FontValues(fontName: "Comfortaa-Medium", fontSize: 25)
            case .h1Light:
                return FontValues(fontName: "Comfortaa-Light", fontSize: 25)
            case .h2Large:
                return FontValues(fontName: "Comfortaa-Bold", fontSize: 20)
            case .h2Medium:
                return FontValues(fontName: "Comfortaa-Medium", fontSize: 20)
            case .h2Light:
                return FontValues(fontName: "Comfortaa-Regular", fontSize: 20)
            case .h3Large:
                return FontValues(fontName: "Poppins-Regular", fontSize: 20)
            case .pBoldBody:
                return FontValues(fontName: "Comfortaa-Bold", fontSize: 14)
            case .pBody:
                return FontValues(fontName: "Poppins-Regular", fontSize: 14)
            case .pSurveyBody:
                return FontValues(fontName: "Poppins-Regular", fontSize: 11)
            case .btnLarge:
                return FontValues(fontName: "Poppins-Regular", fontSize: 20)
            case .btnSmall:
                return FontValues(fontName: "Poppins-Regular", fontSize: 17)
            }
        }
}

extension Font{
    static func custom(_ font: CustomFont) -> SwiftUI.Font {
        SwiftUI.Font.custom(font.values.fontName, size: font.values.fontSize)
    }
}
