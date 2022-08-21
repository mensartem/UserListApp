package com.example.userlistapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import com.example.userlistapp.BR
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<out VM : BaseViewModel, DB : ViewDataBinding>(
    viewModelClass: KClass<VM>,
) :
    AppCompatActivity() {

    open val viewModel: VM by viewModel(null,viewModelClass)

    abstract val layoutRes: Int

    lateinit var binding: DB

    open fun initialize() {

    }

    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        initialize()

    }
}