package tech.leson.chitchat.ui.main.group

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.FragmentGroupBinding
import tech.leson.chitchat.ui.base.BaseFragment
import javax.inject.Inject

class GroupFragment : BaseFragment<FragmentGroupBinding, GroupNavigator, GroupViewModel>(),
    GroupNavigator {

    companion object {
        private var instance: GroupFragment? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GroupFragment().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_group
    override val viewModel: GroupViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(GroupViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun init() {
    }

}
