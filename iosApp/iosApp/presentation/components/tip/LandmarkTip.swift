//
//  LandmarkTip.swift
//  iosApp
//
//  Created by Darren on 2025-02-19.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import TipKit
import shared

struct MapTip: Tip, Identifiable {
    var id = UUID()

    var title: Text {
        Text(IosStringResources(id: SharedRes.strings().onboarding_tuto_map, args: []))
    }

    var image: Image? {
        Image(systemName: "map")
    }
}

struct FeedbackTip: Tip, Identifiable {
    var id = UUID()

    var title: Text {
        Text(IosStringResources(id: SharedRes.strings().survey_feedback_title, args: []))
    }

    var image: Image? {
        Image(systemName: "person.fill")
    }
}

struct BottomBarTip: Tip, Identifiable {
    var id = UUID()

    var title: Text {
        Text(IosStringResources(id: SharedRes.strings().onboarding_tuto_bottom_bar, args: []))
    }
}

struct SwipyTip: Tip, Identifiable {
    var id = UUID()

    var title: Text {
        Text(IosStringResources(id: SharedRes.strings().onboarding_tuto_swipy, args: []))
    }
}
