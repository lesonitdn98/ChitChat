package me.lesonnnn.chitchat.ui.main.menu

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.FragmentMenuBinding
import me.lesonnnn.chitchat.ui.base.BaseFragment
import javax.inject.Inject

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuNavigator, MenuViewModel>(), MenuNavigator {

    companion object {
        private var instance: MenuFragment? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MenuFragment().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_menu
    override val viewModel: MenuViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(MenuViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

}
