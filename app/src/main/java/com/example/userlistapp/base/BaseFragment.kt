package com.example.userlistapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.userlistapp.BR
import org.koin.androidx.viewmodel.ext.android.viewModel

import kotlin.reflect.KClass



abstract class BaseFragment<out VM : BaseViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) :
    Fragment() {

    open val viewModel: VM by viewModel(null, viewModelClass)

    lateinit var binding: DB

    abstract val getLayoutId: Int

    open fun initialize() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }


}

