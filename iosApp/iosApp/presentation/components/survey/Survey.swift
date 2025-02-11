//
//  Survey.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI
import shared

struct Survey: View {
    var onFeedbackClick: () -> Void

    var body: some View {
        VStack {
            Image(resource: UITraitCollection.current.userInterfaceStyle == .dark ? \.pinwhite : \.pinlocation)
                .resizable()
                .scaledToFit()
                .frame(width: 48, height: 48)
            
            Spacer().frame(height: 16)

            Text(IosStringResources(id:SharedRes.strings().survey_feedback_title,args:[]))
                .font(.custom(.h3Large))
                .foregroundColor(Color.feedbackTextColor)
                .multilineTextAlignment(.center)

            Spacer().frame(height: 8)

            Text(IosStringResources(id:SharedRes.strings().survey_feedback_message,args:[]))
                .font(.custom(.pSurveyBody))
                .foregroundColor(Color.feedbackTextColor)
                .multilineTextAlignment(.center)

            Spacer().frame(height: 16)

            Button(action: {
                onFeedbackClick()
            }) {
                Text(IosStringResources(id:SharedRes.strings().survey_feedback_action,args:[]))
                    .font(.custom(.pBoldBody))
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.buttonFeedbackBackground)
                    .foregroundColor(Color.navyBlue)
                    .cornerRadius(8)
            }
        }
        .padding()
        .background(Color.feedbackBoxColor)
        .cornerRadius(12)
        .shadow(radius: 5)
        .padding()
    }
}
