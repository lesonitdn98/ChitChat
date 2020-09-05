package me.lesonnnn.chitchat.ui.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import me.lesonnnn.chitchat.data.local.prefs.PreferencesHelper
import me.lesonnnn.chitchat.ui.base.BaseFragment.Callback
import me.lesonnnn.chitchat.utils.NetworkUtils
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, N, V : BaseViewModel<N>> : AppCompatActivity(),
    Callback {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    var preferencesHelper: PreferencesHelper? = null
        @Inject set

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getViewModel(): V?

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    override fun onFragmentAttached() {
        TODO("Not yet implemented")
    }

    override fun onFragmentDetached(tag: String?) {
        TODO("Not yet implemented")
    }

    open fun getViewDataBinding(): T? {
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

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.lifecycleOwner = this
        mViewDataBinding?.executePendingBindings()
    }

    fun showLoading() {
        TODO("Not yet implemented")
    }

    fun hideLoading() {
        TODO("Not yet implemented")
    }
}
