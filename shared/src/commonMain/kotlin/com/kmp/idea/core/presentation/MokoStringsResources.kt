package com.kmp.idea.core.presentation

import dev.icerock.moko.resources.StringResource

expect class MokoStringsResources {
    fun get(id: StringResource, args: List<Any>): String
}