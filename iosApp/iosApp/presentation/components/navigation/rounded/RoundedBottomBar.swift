//
//  RoundedBottomBar.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//


import shared
import SwiftUI
import TipKit

struct RoundedBottomBar: View {
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
            Capsule()
                .frame(height: 81)
                .foregroundColor(Color.background)
                .shadow(radius: 2)
            /*RoundedRectangle(cornerRadius: 50)
                .fill(Color.background)
                .frame(height: 81)
                .shadow(radius: 2)*/
            HStack {
                Spacer(minLength: 3)
                if UIDevice.current.userInterfaceIdiom != .pad {
                    LandIconBottomBar(
                        content: \.homeiconlight,
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
                        LandInfoButton(
                            content: \.pinlocation,
                            smallText: "Swipy",
                            clickEvent: onClickSwipy
                        ).popoverTip(bottomBarTip)
                    } else {
                        LandInfoButton(
                            content: \.pinlocation,
                            smallText: "Swipy",
                            clickEvent: onClickSwipy
                        )
                    }
                    Spacer(minLength: 2)
                    /*LandIconBottomBar(
                        content: colorScheme == .dark ? \.addicondark : \.addiconlight,
                        condition: onClickAdd,
                        activeButton: false,
                        labelIcon: ""
                    )
                    Spacer(minLength: 2)*/
                    if #available(iOS 17.0, *) {
                        LandIconBottomBar(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        ).popoverTip(feedbackTip)
                    } else {
                        LandIconBottomBar(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        )
                    }
                } else {
                    LandIconIpadClickable(
                        content: \.homeiconlight,
                        condition: onClickHome,
                        activeButton: true,
                        labelIcon: ""
                    )

                    /*Spacer()
                    LandIconIpadClickable(
                        content: colorScheme == .dark ? \.searchicondark : \.searchiconlight,
                        condition: onClickSearch,
                        activeButton: false,
                        labelIcon: ""
                    )*/
                    
                    Spacer()
                    if #available(iOS 17.0, *) {
                        LandInfoiPadButton(
                            content: \.pinlocation,
                            smallText: "Swipy",
                            clickEvent: onClickSwipy
                        ).popoverTip(bottomBarTip)
                    } else {
                        LandInfoiPadButton(
                            content: \.pinlocation,
                            smallText: "Swipy",
                            clickEvent: onClickSwipy
                        )
                    }
                    /*Spacer()
                    
                    LandIconIpadClickable(
                        content: colorScheme == .dark ? \.addicondark : \.addiconlight,
                        condition: onClickAdd,
                        activeButton: false,
                        labelIcon: ""
                    )*/

                    Spacer()
                    if #available(iOS 17.0, *) {
                        LandIconIpadClickable(
                            content: colorScheme == .dark ? \.usericondark : \.usericonlight,
                            condition: onClickProfil,
                            activeButton: false,
                            labelIcon: ""
                        ).popoverTip(feedbackTip)
                    } else {
                        LandIconIpadClickable(
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
                LandPopUpAddNavigation(
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
        /* if (refreshPins != nil) {
             Spacer()
             LandIconButton(content: "arrow.clockwise", condition: refreshPins!)
         }
         Spacer() */
    }
}
