//
//  IosStringResources.swift
//  iosApp
//
//  Created by Darren on 2025-02-11.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

func IosStringResources(id:StringResource,args:[Any]) -> String {
    return LandStringsResources().get(id: id,args: args)
}
