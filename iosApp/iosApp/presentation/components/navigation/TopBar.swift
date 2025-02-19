//
//  TopBar.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RoundedTopBar: View {
    @Environment(\.colorScheme) var colorScheme
    @Binding var selection: String?
    @Binding var isNotified: Bool
    let pinPlaceTip = PinPlaceTip()
    let pinTicketmasterTip = PinTicketmasterTip()
    
    var onClickTheme: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Notification_Button")
            selection = "Notification"
        }
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            Rectangle()
                .frame(maxWidth: .infinity)
                .frame(height: 106)
                .foregroundColor(Color.background)
                .shadow(color: Color.black.opacity(0.5), radius: 2, x: 0, y: 2)
                
            HStack(alignment: .center) {
                Spacer()
                if #available(iOS 17.0, *) {
                    Text("[APP Name]")
                        .font(.custom(.h2Large))
                        .foregroundColor(Color.textColor)
                        .padding(.trailing, 5)
                        .popoverTip(pinPlaceTip)
                        .popoverTip(pinTicketmasterTip)
                } else {
                    Text("[APP Name]")
                        .font(.custom(.h2Large))
                        .foregroundColor(Color.textColor)
                        .padding(.trailing, 5)
                }
                Spacer()
            }.padding(.horizontal, 10)
                .padding(.top, 45)
        }.frame(height: 106)
            .edgesIgnoringSafeArea(.top)
    }
}
