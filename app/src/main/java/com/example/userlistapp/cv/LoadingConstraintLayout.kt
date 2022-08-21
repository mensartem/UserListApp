package com.example.userlistapp.cv

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.userlistapp.R
import com.example.userlistapp.util.extension.gone
import com.example.userlistapp.util.extension.visible

class LoadingConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar

    private val layoutNoData: ConstraintLayout
    private val tvError: AppCompatTextView
    private val tvDesc: AppCompatTextView
    private val imgWarning: AppCompatImageView

    init {
        inflate(context, R.layout.custom_loading_constraint_layout, this)

        progressBar = findViewById(R.id.progress_bar)
        tvDesc = findViewById(R.id.tv_desc)
        layoutNoData = findViewById(R.id.layout_no_data)
        tvError = layoutNoData.findViewById(R.id.tv_error)
        imgWarning = layoutNoData.findViewById(R.id.img_warning)
    }

    fun onError(msg: String) {
        progressBar.gone()
        tvDesc.gone()
        layoutNoData.visible()
        tvError.text = msg
    }

    fun onInternalError() {
        progressBar.gone()
        tvDesc.gone()
        layoutNoData.gone()
    }

    fun onLoading() {
        progressBar.visible()
        tvDesc.visible()
        layoutNoData.gone()
    }

    fun onSuccess() {
        progressBar.gone()
        tvDesc.gone()
        layoutNoData.gone()
    }
}