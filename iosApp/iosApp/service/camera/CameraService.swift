//
//  CameraService.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import AVFoundation

class CameraService {
    var session: AVCaptureSession?
    var delegate: AVCapturePhotoCaptureDelegate?
    
    let outPut = AVCapturePhotoOutput()
    let previewLayer = AVCaptureVideoPreviewLayer()
    
    func start(delegate: AVCapturePhotoCaptureDelegate, complition: @escaping (Error?) -> ()) {
        self.delegate = delegate
        checkForPermission(complition: complition)
    }
    
    private func checkForPermission(complition: @escaping (Error?) -> ()) {
        switch AVCaptureDevice.authorizationStatus(for: .video) {
            
        case .notDetermined:
            AVCaptureDevice.requestAccess(for: .video) { [weak self] granted in
                guard granted else { return }
                DispatchQueue.main.async {
                    self?.setupCamera(complition: complition)
                }
            }
        case .restricted:
            break
        case .denied:
            break
        case .authorized:
            DispatchQueue.main.async {
                self.setupCamera(complition: complition)
            }
        @unknown default:
            break
        }
    }
    
    private func setupCamera(complition: @escaping (Error?) -> ()) {
        let session = AVCaptureSession()
        if let device = AVCaptureDevice.default(for: .video) {
            do {
                let input = try AVCaptureDeviceInput(device: device)
                if session.canAddInput(input) {
                    session.addInput(input)
                }
                
                if session.canAddOutput(outPut) {
                    session.addOutput(outPut)
                }
                
                previewLayer.videoGravity = .resizeAspectFill
                previewLayer.session = session
                
                session.startRunning()
                self.session = session
                
            } catch {
                complition(error)
            }
        }
    }
    
    func capturePhoto(with settings: AVCapturePhotoSettings = AVCapturePhotoSettings()) {
        let previewPixelType = settings.availablePreviewPhotoPixelFormatTypes.first!
        let previewFormat = [kCVPixelBufferPixelFormatTypeKey as String: previewPixelType,
                            kCVPixelBufferWidthKey as String: 160,
                            kCVPixelBufferHeightKey as String: 160]
        settings.previewPhotoFormat = previewFormat

        self.outPut.capturePhoto(with: settings, delegate: delegate!)
    }
    
    
    func toggleCameraPosition() {
        guard let session = session else { return }
        session.beginConfiguration()
        
        // Récupération de la caméra actuelle
        guard let currentInput = session.inputs.first as? AVCaptureDeviceInput else { return }
        let currentPosition = currentInput.device.position
        
        // Recherche d'une autre caméra disponible
        let newPosition: AVCaptureDevice.Position
        switch currentPosition {
        case .back:
            newPosition = .front
        case .front:
            newPosition = .back
        case .unspecified:
            return
        @unknown default:
            return
        }
        
        guard let newCamera = AVCaptureDevice.default(.builtInWideAngleCamera, for: .video, position: newPosition) else { return }
        
        // Création d'un nouvel input avec la nouvelle caméra et remplacement de l'input actuel
        do {
            let newInput = try AVCaptureDeviceInput(device: newCamera)
            session.removeInput(currentInput)
            if session.canAddInput(newInput) {
                session.addInput(newInput)
            }
        } catch {
            print("Erreur lors de la configuration de la caméra: \(error.localizedDescription)")
        }
        
        session.commitConfiguration()
    }
}
