package me.lesonnnn.chitchat.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.lesonnnn.chitchat.data.DataManager
import me.lesonnnn.chitchat.utils.rx.SchedulerProvider
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(
    private var mDataManager: DataManager,
    private var mSchedulerProvider: SchedulerProvider
) : ViewModel() {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mNavigator: WeakReference<N>? = null

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    val compositeDisposable: CompositeDisposable
        get() = mCompositeDisposable

    val dataManager: DataManager
        get() = mDataManager

    val navigator: N?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    val schedulerProvider: SchedulerProvider
        get() = mSchedulerProvider
}
