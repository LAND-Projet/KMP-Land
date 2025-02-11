//
//  CameraScreen.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Photos

class CameraDataHolder: ObservableObject {
    @Published var latitudeValue: Double = 0.0
    @Published var longitudeValue: Double = 0.0
    @Published var titleValue: String = ""
    @Published var descriptionValue: String = ""
}

struct CameraScreen: View {
    @State private var selection: String? = nil
    @State private var capturedPhoto: UIImage? = nil
    @State private var show: Bool = true
    let cameraService: CameraService = CameraService()
    @ObservedObject var viewModel: CameraViewModel
    let locationManager = LandLocationManager()
    
    init(latitudeValue: Double, longitudeValue: Double, titleValue: String, descriptionValue: String) {
        viewModel = CameraViewModel(latitudeValue: latitudeValue, longitudeValue: longitudeValue, titleValue: titleValue, descriptionValue: descriptionValue)
    }
    
    var body: some View {
        ZStack {
            Color.background.ignoresSafeArea()
            NavigationLink(
                destination: CreationPostScreen(
                    latitudeValue: viewModel.latitudeValue,
                    longitudeValue: viewModel.longitudeValue,
                    capturedPhoto: capturedPhoto,
                    titleValue: viewModel.titleValue,
                    descriptionValue: viewModel.descriptionValue
                ),
                tag: "CreationPost",
                selection: $selection
            ){
                EmptyView()
            }
            NavigationLink(destination: HomeScreen(),tag: "Home", selection: $selection){
                EmptyView()
            }
            CustomCameraCaptureView(cameraService: cameraService) { result in
                switch result {
                    case .success(let photoData):
                        if let data = photoData.fileDataRepresentation(),
                        let image = UIImage(data: data) {
                            let (latitude, longitude) = fetchMetadata(from: data)
                            if let latitude = latitude, let longitude = longitude {
                                print("Latitude: \(latitude), Longitude: \(longitude)")
                                viewModel.latitudeValue = latitude
                                viewModel.longitudeValue = longitude
                            } else {
                                print("Impossible d'extraire les données de localisation de l'image.")
                            }
                            capturedPhoto = image
                            selection = "CreationPost"
                            print("Next Page")
                            
                        } else {
                            print("Can not convert photo from data")
                        }
                    case .failure(let error):
                        print(error.localizedDescription)
                }

                show.toggle()
            }.ignoresSafeArea()
            VStack {
                Spacer()
                
                HStack(alignment: .center){
                    Spacer().frame(width: 90)
                    Button {
                        cameraService.capturePhoto()
                        
                    } label: {
                        Circle()
                            .fill(Color.lightGrey)
                            .frame(width: 60, height: 60, alignment: .center)
                    }
                    .padding(.bottom)
                    Spacer().frame(width: 40)
                    Button(action: {
                        cameraService.toggleCameraPosition()
                    }) {
                        Image(systemName: "camera.rotate.fill")
                            .resizable()
                            .frame(width: 60, height: 50)
                            .foregroundColor(Color.lightGrey)
                    }
                    .padding(.bottom)
                }
                
            }
        }.toolbar {
            ToolbarItem(placement: .navigationBarTrailing){
                Button(action: {
                    selection = "Home"
                }) {
                    ZStack{
                        Image(systemName:"xmark").resizable().frame(width: 30,height: 30).foregroundColor(Color.lightGrey)
                    }
                }
            }
        }.navigationBarBackButtonHidden(true)
    }
    
    func fetchMetadata(from imageData: Data) -> (Double?, Double?) {
        guard let imageSource = CGImageSourceCreateWithData(imageData as CFData, nil) else {
            return (nil, nil)
        }
        
        if let properties = CGImageSourceCopyPropertiesAtIndex(imageSource, 0, nil) as? [String: Any],
           let latitude = properties[kCGImagePropertyGPSLatitude as String] as? Double,
           let longitude = properties[kCGImagePropertyGPSLongitude as String] as? Double {
            return (latitude, longitude)
        }
        
        
        let metadata = (CGImageSourceCopyPropertiesAtIndex(imageSource, 0, nil))
        let test = CGImageSourceCopyMetadataAtIndex(imageSource, 0, nil)
        
        if let userLoc = locationManager.getUserLocationForCamera() {
            return (userLoc.lat,userLoc.lng)
        }
        
        return (nil, nil)
    }
}
