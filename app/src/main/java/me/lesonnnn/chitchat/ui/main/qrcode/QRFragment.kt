package me.lesonnnn.chitchat.ui.main.qrcode

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.FragmentQrBinding
import me.lesonnnn.chitchat.ui.base.BaseFragment
import javax.inject.Inject

class QRFragment : BaseFragment<FragmentQrBinding, QRNavigator, QRViewModel>(), QRNavigator {

    companion object {
        private var instance: QRFragment? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QRFragment().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_qr
    override val viewModel: QRViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(QRViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

}
