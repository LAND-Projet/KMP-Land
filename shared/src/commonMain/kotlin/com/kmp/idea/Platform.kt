package com.kmp.idea

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform