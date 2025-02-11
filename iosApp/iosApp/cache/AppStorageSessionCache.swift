//
//  AppStorageSessionCache.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import shared

struct TokenConnexionSwift: Codable {
    let currentUserGuid: String
    let jwtToken: String
    
    init(from TokenConnexion: TokenConnexion) {
        self.currentUserGuid = tokenConnexion.currentUserGuid
        self.jwtToken = tokenConnexion.jwtToken
    }
    
    func toTokenConnexion() -> TokenConnexion {
        return TokenConnexion(currentUserGuid: currentUserGuid, jwtToken: jwtToken)
    }
}

class AppStorageSessionCache {
    @AppStorage(Settings.sessionNameKey, store: UserDefaults.standard) private var storedSessionData: Data?
    @AppStorage(Settings.onboardingKey, store: UserDefaults.standard) private var storedOnboarding: Bool = false
    
    func saveSession(session: TokenConnexion) {
        let swiftSession = TokenConnexionSwift(from: session)
        let data = try? JSONEncoder().encode(swiftSession)
        storedSessionData = data
    }
    
    func getActiveSession() -> TokenConnexion? {
        guard let data = storedSessionData else {
            return nil
        }
        
        do {
            let swiftSession = try? JSONDecoder().decode(TokenConnexionSwift.self, from: data)
            return swiftSession?.toTokenConnexion()
        } catch {
            return nil
        }
    }
    
    func clearSession() {
        storedSessionData = nil
    }
    
    func saveOnboarding(hasSeenOnboarding: Bool) {
        storedOnboarding = hasSeenOnboarding
    }
    
    func getOnboarding() -> Bool {
        return storedOnboarding
    }
}
