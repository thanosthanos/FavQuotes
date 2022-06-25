package com.store.favquotes.extension

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

const val PASSWORD_MIN_LENGTH = 5

fun String.isEmailValid(): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isUserNameValid(): Boolean {
    val ptrn = Pattern.compile(
        "^[a-z][-a-z0-9\\._]*$"
    )
    val match: Matcher = ptrn.matcher(this)
    return match.find()
}

fun String.isPasswordValid(): Boolean =
    this.length >= PASSWORD_MIN_LENGTH
