package me.lesonnnn.chitchat.ui.main.contact

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.FragmentContactBinding
import me.lesonnnn.chitchat.ui.base.BaseFragment
import javax.inject.Inject

class ContactFragment : BaseFragment<FragmentContactBinding, ContactNavigator, ContactViewModel>(), ContactNavigator {

    companion object {
        private var instance: ContactFragment? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ContactFragment().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_contact
    override val viewModel: ContactViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(ContactViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }
}
