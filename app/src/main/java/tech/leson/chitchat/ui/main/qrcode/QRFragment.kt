package tech.leson.chitchat.ui.main.qrcode

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.FragmentQrBinding
import tech.leson.chitchat.ui.base.BaseFragment
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

    override fun init() {
    }

}
