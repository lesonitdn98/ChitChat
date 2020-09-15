package me.lesonnnn.chitchat.ui.main.menu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.fragment_menu.*
import me.lesonnnn.chitchat.BR
import me.lesonnnn.chitchat.R
import me.lesonnnn.chitchat.ViewModelProviderFactory
import me.lesonnnn.chitchat.databinding.FragmentMenuBinding
import me.lesonnnn.chitchat.ui.base.BaseFragment
import javax.inject.Inject

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuNavigator, MenuViewModel>(),
    MenuNavigator, CompoundButton.OnCheckedChangeListener {

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

    override fun init() {
        viewModel.setNavigator(this)
        swDarkMode.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        swDarkMode.isEnabled = false
        viewModel.setDarkMode(p1)
        if (p1) {
            Handler(Looper.getMainLooper()).postDelayed({
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                swDarkMode.isEnabled = true
            }, 400)
        }
        else {
            Handler(Looper.getMainLooper()).postDelayed({
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                swDarkMode.isEnabled = true
            }, 400)
        }
    }

}
