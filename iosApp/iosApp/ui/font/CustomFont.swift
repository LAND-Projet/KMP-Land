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
        let fontWeight: Font.Weight
    }

    var values: FontValues {
        switch self {
                case .h1Large:
                    return FontValues(fontName: "poppins_bold", fontSize: 25, fontWeight: .bold)
                case .h1Medium:
                    return FontValues(fontName: "poppins_medium", fontSize: 25, fontWeight: .medium)
                case .h1Light:
                    return FontValues(fontName: "poppins_regular", fontSize: 25, fontWeight: .regular)
                case .h2Large:
                    return FontValues(fontName: "poppins_semibold", fontSize: 20, fontWeight: .semibold)
                case .h2Medium:
                    return FontValues(fontName: "poppins_medium", fontSize: 20, fontWeight: .medium)
                case .h2Light:
                    return FontValues(fontName: "poppins_regular", fontSize: 20, fontWeight: .regular)
                case .h3Large:
                    return FontValues(fontName: "poppins_regular", fontSize: 20, fontWeight: .regular)
                case .pBoldBody:
                    return FontValues(fontName: "poppins_medium", fontSize: 14, fontWeight: .medium)
                case .pBody:
                    return FontValues(fontName: "poppins_regular", fontSize: 14, fontWeight: .regular)
                case .pSurveyBody:
                    return FontValues(fontName: "poppins_light", fontSize: 12, fontWeight: .light)
                case .btnLarge:
                    return FontValues(fontName: "poppins_semibold", fontSize: 20, fontWeight: .semibold)
                case .btnSmall:
                    return FontValues(fontName: "poppins_regular", fontSize: 17, fontWeight: .regular)
        }
    }
}

extension Font{
    static func custom(_ font: CustomFont) -> SwiftUI.Font {
        SwiftUI.Font.custom(font.values.fontName, size: font.values.fontSize)
    }
}
