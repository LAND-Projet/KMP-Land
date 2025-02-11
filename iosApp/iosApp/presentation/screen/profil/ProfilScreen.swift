//
//  ProfilScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import FirebaseStorage
import shared
import SwiftUI

struct ProfilScreen: View {
    @State private var selection: String? = nil
    @State private var postArgument: String = ""
    @State private var showDialogFollower = false
    @State private var showDialogFollowing = false
    @State private var landLocation: LandLocation? = LandLocation(name: "default", lat: 46.122917, lng: -70.670349)
    @ObservedObject var viewModel: ProfilViewModel
    let feedbackTip = FeedbackTip()

    init(userID: String, yourProfil: Bool) {
        viewModel = ProfilViewModel(userID: userID, yourProfil: yourProfil)
    }

    var followUser: () -> Void {
        return {
            let isPrivate = viewModel.landUser.IsPrivate
            if !isPrivate {
                viewModel.followUser()
                viewModel.isRequestFollowSend = false
                viewModel.isFollow = true
                AnalyticsManager.manager.logEvent(name: "Click_Follow_Button")
            } else {
                viewModel.sendRequestFollowAccount()
                viewModel.isRequestFollowSend = true
                viewModel.isFollow = false
                AnalyticsManager.manager.logEvent(name: "Click_Send_Request_Follow_Button")
            }
        }
    }

    var unfollowUser: () -> Void {
        return {
            viewModel.unfollowUser()
            AnalyticsManager.manager.logEvent(name: "Click_UnFollow_Button")
        }
    }

    var cancelRequestFollowUser: () -> Void {
        return {
            viewModel.deleteRequestfollow()
            AnalyticsManager.manager.logEvent(name: "Click_Cancel_Request_Follow_Button")
        }
    }

    var body: some View {
        ZStack {
            VStack(spacing: 0) {
                NavigationLink(destination: DetailPostScreen(postId: postArgument), tag: "PostDetail", selection: $selection) {
                    EmptyView()
                }
                NavigationLink(destination: ParameterScreen(), tag: "Parameter", selection: $selection) {
                    EmptyView()
                }
                NavigationLink(destination: HomeScreen(), tag: "Home", selection: $selection) {
                    EmptyView()
                }
                NavigationLink(destination: AuthMenuScreen(), tag: "AuthMenu", selection: $selection) {
                    EmptyView()
                }

                ZStack(alignment: .top) {
                    Color.background
                        .ignoresSafeArea()
                        .overlay(
                            Image(resource: \.reliefbleu)
                                .resizable()
                                .scaledToFill()
                                .opacity(0.2)
                                .edgesIgnoringSafeArea(.all)
                        )
                    RoundedRectCorner(radius: 50, corners: [.bottomLeft, .bottomRight])
                        .frame(maxWidth: .infinity)
                        .frame(height: 300)
                        .foregroundColor(Color.backgroundTop)
                        .shadow(color: Color.black.opacity(0.5), radius: 2, x: 0, y: 2)
                    VStack {
                        LandProfilTopBar(
                            selection: $selection,
                            isYourProfil: $viewModel.isYourProfil,
                            isSameUserId: $viewModel.isSameUserId,
                            eventClick: {
                                AnalyticsManager.manager.logEvent(name: "Click_Disconnect_Button")
                                await viewModel.signOut()
                            },
                            blockClick: {
                                viewModel.isBlockPopUpOpen = true
                            },
                            reportClick: {
                                viewModel.isReportPopUpOpen = true
                            }
                        )

                        if viewModel.isLoading {
                            LandLoadingIcon()
                        } else {
                            LandUserInfoItem(
                                user: viewModel.landUser,
                                eventClick: followUser,
                                isYourProfil: viewModel.isYourProfil,
                                isSameUserId: viewModel.isSameUserId,
                                showDialogFollower: $showDialogFollower,
                                showDialogFollowing: $showDialogFollowing,
                                imageURL: viewModel.imageURL,
                                followerCount: viewModel.followerCount,
                                followingCount: viewModel.followingCount,
                                unfollowClick: unfollowUser,
                                cancelRequestClick: cancelRequestFollowUser,
                                isRequestFollowSend: $viewModel.isRequestFollowSend,
                                isFollow: $viewModel.isFollow
                            )
                        }

                    }
                }.frame(height: 280)
                    .edgesIgnoringSafeArea(.top)
                LandSurvey(onFeedbackClick: {
                    if #available(iOS 17.0, *) {
                        feedbackTip.invalidate(reason: .actionPerformed)
                    }
                    guard let url = URL(string: "https://forms.gle/gPNMZ7NcommuQLT4A") else { return }
                    UIApplication.shared.open(url)
                })
                /*if viewModel.isLoading {
                    LandLoadingIcon()
                } else {
                    CarouselViewPostUser(
                        postList: $viewModel.postList,
                        selection: $selection,
                        postArgument: $postArgument
                    ).background(Color.clear)
                }*/
                Spacer()
                
            }.background(
                Color.background
                    .ignoresSafeArea()
                    .overlay(
                        Image(resource: \.reliefbleu)
                            .resizable()
                            .scaledToFill()
                            .opacity(0.2)
                            .edgesIgnoringSafeArea(.all)
                    )
            ).navigationBarBackButtonHidden(true)
                
            if viewModel.isBlockPopUpOpen {
                LandBoxBlockUserPopUp(
                    eventBlockClick: { viewModel.blockUser() },
                    eventUnBlockClick: { viewModel.unblockUser() },
                    isVisible: $viewModel.isBlockPopUpOpen
                )
            }
            if viewModel.isReportPopUpOpen {
                LandBoxReportUserPopUp(
                    eventReportClick: {  },
                    isVisible: $viewModel.isReportPopUpOpen
                )
            }
            if showDialogFollower {
                LandUserEventPopUp(
                    isVisible: $showDialogFollower,
                    isLoading: $viewModel.isLoadingFollower,
                    list: self.$viewModel.listFollower,
                    TitlePopUp: IosStringResources(id: SharedRes.strings().follower_text_label, args: []),
                    HintPopUp: IosStringResources(id: SharedRes.strings().no_follower_hint_label_text, args: [])
                )
            }
            if showDialogFollowing {
                LandUserEventPopUp(
                    isVisible: $showDialogFollowing,
                    isLoading: $viewModel.isLoadingFollowing,
                    list: self.$viewModel.listFollowing,
                    TitlePopUp: IosStringResources(id: SharedRes.strings().following_text_label, args: []),
                    HintPopUp: IosStringResources(id: SharedRes.strings().no_following_hint_label_text, args: [])
                )
            }
        }.onAppear(perform: {
            viewModel.initializeData()
            viewModel.InitializeFollowList()
        })
        .onDisappear(perform: {
            viewModel.resetData()
        })
    }
}
