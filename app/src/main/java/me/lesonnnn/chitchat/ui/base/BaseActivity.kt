package me.lesonnnn.chitchat.ui.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import me.lesonnnn.chitchat.ui.base.BaseFragment.Callback
import me.lesonnnn.chitchat.utils.NetworkUtils
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, N, V : BaseViewModel<N>> : AppCompatActivity(),
    Callback {

    private lateinit var mViewDataBinding: T

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    abstract fun addAnimTransition()

    abstract fun init()

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        initThemeMode()
        super.onCreate(savedInstanceState)
        addAnimTransition()
        performDataBinding()
        init()
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    open fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    private fun initThemeMode() {
        if (preferencesHelper.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewDataBinding.setVariable(bindingVariable, viewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    fun showLoading() {
        TODO("Not yet implemented")
    }

    fun hideLoading() {
        TODO("Not yet implemented")
    }
}
