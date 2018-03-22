package com.example.raghav.theguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author raghav
 */

fun ViewGroup.inflate(layout: Int, attachToParameter: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToParameter)
}