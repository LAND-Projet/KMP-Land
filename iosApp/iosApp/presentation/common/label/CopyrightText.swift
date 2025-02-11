//
//  CopyrightText.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CopyrightText: View {
    var body: some View {
        Text(IosStringResources(id:SharedRes.strings().land_copyright_text,args:[]))
        .bold()
    }
}
