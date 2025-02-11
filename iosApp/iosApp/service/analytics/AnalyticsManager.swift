//
//  AnalyticsManager.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import FirebaseAnalytics

final class AnalyticsManager {
    static let manager = AnalyticsManager()
    private init() { }
    
    func logEvent(name: String, params: [String:Any]? = nil){
        Analytics.logEvent(name, parameters: params)
    }
    
    func setUserId(userId:String) {
        Analytics.setUserID(userId)
    }
    
    func setUserProperty(value:String?, property: String){
        Analytics.setUserProperty(value, forName: property)
    }
}
