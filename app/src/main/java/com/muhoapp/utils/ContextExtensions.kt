package com.muhoapp.utils

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

class ContextExtensions {
    inline fun <reified T : View> View.find(id: Int): T = findViewById(id) as T
    inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T
    inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById(id) as T
}