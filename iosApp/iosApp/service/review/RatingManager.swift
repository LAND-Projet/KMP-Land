//
//  RatingManager.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import StoreKit
import _StoreKit_SwiftUI


class RatingManager {
    private let daysBeforePrompt = 7
    private let usesBeforePrompt = 10
    
    private let lastPromptDateKey = "lastPromptDate"
    private let hasUserRatedKey = "hasUserRated"
    
    init() {
        
    }
    
    func checkAndAskForReview() {
        let defaults = UserDefaults.standard

        // Vérifiez si l'utilisateur a déjà noté l'application
        let hasUserRated = defaults.bool(forKey: hasUserRatedKey)
        guard !hasUserRated else { return }

        // Vérifiez si le nombre de lancements est atteint
        var appLaunchCount = defaults.integer(forKey: "appLaunchCount")
        appLaunchCount += 1
        defaults.set(appLaunchCount, forKey: "appLaunchCount")

        // Vérifiez si le délai entre les prompts est atteint
        if appLaunchCount >= usesBeforePrompt {
            let currentDate = Date()
            if let lastPromptDate = defaults.object(forKey: lastPromptDateKey) as? Date {
                let daysSinceLastPrompt = Calendar.current.dateComponents([.day], from: lastPromptDate, to: currentDate).day ?? 0
                
                if daysSinceLastPrompt >= daysBeforePrompt {
                    showReviewPrompt()
                    defaults.set(currentDate, forKey: lastPromptDateKey)
                }
            } else {
                // Première fois que le prompt s'affiche, enregistrez la date actuelle
                showReviewPrompt()
                defaults.set(currentDate, forKey: lastPromptDateKey)
            }
        }
    }

    private func showReviewPrompt() {
        if #available(iOS 16.0, *) {
            SKStoreReviewController.requestReview()
        } else {
            SKStoreReviewController.requestReview()
        }
    }
    
    func userDidRateApp() {
        let defaults = UserDefaults.standard
        defaults.set(true, forKey: hasUserRatedKey)
    }
}
