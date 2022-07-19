package dev.goobar.androidstudyguide

fun isEmail(email: String): Boolean  = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
