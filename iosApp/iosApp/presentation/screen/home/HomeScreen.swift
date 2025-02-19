//
//  HomeScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import GoogleMaps
import shared
import TipKit

struct HomeScreen: View {
    @State private var selection: String? = nil
    @State private var postId: String = ""
    @State private var eventId: String = ""
    @State private var ticketmasterId: String = ""
    @State private var landSwipy: LandSwipy? = nil
    @State private var landLocation: LandLocation? =  LandLocation(name: "default", lat: 46.122917, lng: -70.670349)
    @State private var timer: Timer?
    @State private var showAddPopup = false
    @ObservedObject var viewModel: HomeViewModel
    private var ratingManager: RatingManager = RatingManager()
    let bottomBarTip = BottomBarTip()
    let mapTip = MapTip()
    let pinPlaceTip = PinPlaceTip()
    let pinTicketmasterTip = PinTicketmasterTip()
    
    init() {
        viewModel = HomeViewModel()
    }
    
    var startTimer: () -> Void {
        return {
            // First load
            refreshPins()

            timer = Timer.scheduledTimer(withTimeInterval: 1 * 60, repeats: true) { _ in
                refreshPins()
            }
        }
    }
    
    var stopTimer: () -> Void {
        return {
            timer?.invalidate()
            timer = nil
        }
    }
    
    var refreshPins: () -> Void {
        return {
            viewModel.mapViewController.updatePins()
            viewModel.mapViewController.fetchDataPin(userId: viewModel.idUserSession) { success in
                if (!success) {
                    print("Error: fetchDataPin in HomeScreen")
                }
            }
            Task {
                await viewModel.mapViewController.fetchAndProcessSwipyPosts(){ success in
                    if (!success) {
                        print("Error: fetchDataPin in HomeScreen")
                    }
                }
            }
        }
    }
    
    var body: some View {
        ZStack(alignment: .top) {
            Color.background.ignoresSafeArea()
            NavigationLink(destination: DetailPostScreen(postId: postId),tag: "PostDetail", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: ProfilScreen(userID: viewModel.idUserSession,yourProfil: true),tag: "Profil", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: SearchScreen(),tag: "Search", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: PlaceFinderScreen(),tag: "Swipe", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: NotificationScreen(),tag: "Notification", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: ParameterScreen(), tag: "Parameter", selection: $selection) {
                EmptyView()
            }
            NavigationLink(destination: CameraScreen(latitudeValue: 0.0, longitudeValue: 0.0,titleValue: "",descriptionValue: ""),tag: "Camera", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: CreationEventScreen(), tag: "Create_Event", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: DetailEventScreen(eventId: eventId), tag: "Detail_Event", selection: $selection){
                EmptyView()
            }
            NavigationLink(destination: DetailTicketmasterScreen(id: ticketmasterId), tag: "Detail_Ticketmaster", selection: $selection){
                EmptyView()
            }
            if let landSwipy = landSwipy {
                NavigationLink(destination: PlaceResultScreen(landSwipy: landSwipy), tag: "Matched", selection: $selection) {
                    EmptyView()
                }
            }

            if showAddPopup {
                Color.clear
                    .background(Color.black.opacity(0.001))
                    .edgesIgnoringSafeArea(.all)
                    .onTapGesture {
                        withAnimation {
                            showAddPopup = false
                        }
                    }
                    .zIndex(0)
            }
            
            VStack {
                Spacer()
                BottomBar(
                    selection: $selection,
                    showingAddPopup: $showAddPopup,
                    refreshPins: refreshPins
                )
            }
            
        }
        .onAppear(perform: {
            viewModel.userHaveARequest()
            ratingManager.checkAndAskForReview()
            AnalyticsManager.manager.logEvent(name: "HomeView_Appear")
            AnalyticsManager.manager.setUserId(userId: viewModel.idUserSession)
        })
        .onDisappear{
            AnalyticsManager.manager.logEvent(name: "HomeView_Disappear")
        }.navigationBarBackButtonHidden(true)
    }
}
