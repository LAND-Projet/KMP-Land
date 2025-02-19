//
//  BottomBar.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared
import SwiftUI
import TipKit

struct BottomBar: View {
    @Environment(\.colorScheme) var colorScheme
    @Binding var selection: String?
    @Binding var showingAddPopup: Bool
    var refreshPins: (() -> Void)? = nil
    let bottomBarTip = BottomBarTip()
    let feedbackTip = FeedbackTip()

    var onClickHome: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Home_Button")
            selection = "Home"
        }
    }

    var onClickSearch: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Search_Button")
            selection = "Search"
        }
    }

    var onClickAdd: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Add_Button")
            showingAddPopup = true
        }
    }

    var onNavPostCreation: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Post_Creation_Button")
            selection = "Camera"
        }
    }

    var onNavEventCreation: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Event_Creation_Button")
            selection = "Create_Event"
        }
    }

    var onClickProfil: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Profil_Button")
            selection = "Profil"
        }
    }
    
    var onClickSwipy: () -> Void {
        return {
            AnalyticsManager.manager.logEvent(name: "Click_Swipy_Button")
            selection = "Swipe"
            if #available(iOS 17.0, *) {
                bottomBarTip.invalidate(reason: .actionPerformed)
            }
        }
    }

    var body: some View {
        ZStack(alignment: .center) {
            Rectangle()
                .frame(height: 81)
                .foregroundColor(Color.background)
                .shadow(radius: 2)
            HStack {
                Spacer(minLength: 3)
                if UIDevice.current.userInterfaceIdiom != .pad {
                    IconBottomBar(
                        content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                        condition: onClickHome,
                        activeButton: true,
                        labelIcon: ""
                    )
                    /*Spacer(minLength: 2)
                    LandIconBottomBar(
                        content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                        condition: onClickSearch,
                        activeButton: false,
                        labelIcon: ""
                    )*/
                    Spacer(minLength: 2)
                    if #available(iOS 17.0, *) {
                        IconBottomBar(
                            content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                            condition: onClickHome,
                            activeButton: true,
                            labelIcon: ""
                        ).popoverTip(bottomBarTip)
                    } else {
                        IconBottomBar(
                            content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                            condition: onClickHome,
                            activeButton: true,
                            labelIcon: ""
                        )
                    }
                    Spacer(minLength: 2)
                    if #available(iOS 17.0, *) {
                        IconBottomBar(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        ).popoverTip(feedbackTip)
                    } else {
                        IconBottomBar(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        )
                    }
                } else {
                    IconIpadClickable(
                        content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                        condition: onClickHome,
                        activeButton: true,
                        labelIcon: ""
                    )
                    Spacer()
                    if #available(iOS 17.0, *) {
                        IconIpadClickable(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        ).popoverTip(bottomBarTip)
                    } else {
                        IconIpadClickable(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        )
                    }
                    Spacer()
                    if #available(iOS 17.0, *) {
                        IconIpadClickable(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        ).popoverTip(feedbackTip)
                    } else {
                        IconIpadClickable(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        )
                    }
                }

                Spacer(minLength: 3)
            }.frame(height: 80)
            if showingAddPopup {
                PopUpAddNavigation(
                    isPresented: $showingAddPopup,
                    onCreatePostClick: {
                        onNavPostCreation()
                        showingAddPopup = false
                    },
                    onCreateEventClick: {
                        onNavEventCreation()
                        showingAddPopup = false
                    }
                )
                .transition(.move(edge: .bottom))
                .zIndex(1)
                .frame(maxWidth: .infinity)
                .background(Color.clear)
                .offset(y: 0)
            }
        }.padding(.horizontal)
    }
}
