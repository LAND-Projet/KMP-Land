//
//  PopUpNavigation.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PopUpAddNavigation: View {
    @Environment(\.colorScheme) var colorScheme
    @Binding var isPresented: Bool
    var onCreatePostClick: () -> Void
    var onCreateEventClick: () -> Void
    
    var body: some View {
        VStack(alignment: .center, spacing: 5) {
            AddNavButton(
                eventClick: onCreatePostClick,
                content: IosStringResources(id:SharedRes.strings().add_post_popup_text,args:[]),
                iconName: colorScheme == .dark ? \.cameradark : \.cameralight
            )
            AddNavButton(
                eventClick: onCreateEventClick,
                content: IosStringResources(id:SharedRes.strings().add_event_popup_text,args:[]),
                iconName: colorScheme == .dark ? \.eventlight : \.eventdark
            )
        }
        .padding()
        .background(Color.background)
        .cornerRadius(20)
        .shadow(radius: 10)
        .frame(maxWidth: .infinity)
    }
}

struct AddNavButton: View {
    var eventClick: () -> Void
    var content: String
    var iconName: KeyPath<SharedRes.images, shared.ImageResource>
    
    var body: some View {
        Button(action: eventClick) {
            HStack {
                Image(resource: iconName)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 30, height: 30)
                Text(content)
                    .foregroundColor(.textColor)
            }
            .padding()
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .background(Color.clear)
    }
}
