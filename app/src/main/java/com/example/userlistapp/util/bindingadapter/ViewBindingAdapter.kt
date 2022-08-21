package com.example.userlistapp.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.userlistapp.util.extension.gone
import com.example.userlistapp.util.extension.visible

@BindingAdapter("isVisible", requireAll = false)
fun isVisible(view: View, value :Boolean = false) {
    if (value) view.visible()
    else view.gone()
}