package tech.leson.chitchat.ui.update.dialog

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.dialog_avatar.*
import tech.leson.chitchat.BR
import tech.leson.chitchat.R
import tech.leson.chitchat.ViewModelProviderFactory
import tech.leson.chitchat.databinding.DialogAvatarBinding
import tech.leson.chitchat.ui.base.BaseFragmentDialog
import tech.leson.chitchat.ui.update.UpdateActivity
import tech.leson.chitchat.ui.update.UpdateNavigator
import tech.leson.chitchat.ui.update.dialog.adapter.AvatarAdapter
import tech.leson.chitchat.ui.update.dialog.adapter.OnClickItemAvatar
import tech.leson.chitchat.ui.update.dialog.model.Avatar
import javax.inject.Inject

class AvatarDialog :
    BaseFragmentDialog<DialogAvatarBinding, AvatarDialogNavigator, AvatarDialogViewModel>(),
    AvatarDialogNavigator, OnClickItemAvatar {

    companion object {
        private var instance: AvatarDialog? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AvatarDialog().also { instance = it }
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var currentAvatar: Int = 0
    private var currentBackground: Int = 0
    private var mAvatar: Int = 1

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.dialog_avatar
    override val viewModel: AvatarDialogViewModel
        get() = factory.let {
            ViewModelProvider(
                this,
                it
            ).get(AvatarDialogViewModel::class.java)
        }

    var avatar: String = ""

    override fun init() {
        viewModel.setNavigator(this)

        rvAvatar.layoutManager = GridLayoutManager(context, 5)
        val avatarData = ArrayList<Avatar>()
        avatarData.add(Avatar(0,0, R.drawable.av_1))
        avatarData.add(Avatar(1,0, R.drawable.av_2))
        avatarData.add(Avatar(2,0, R.drawable.av_3))
        avatarData.add(Avatar(3,0, R.drawable.av_4))
        avatarData.add(Avatar(4,0, R.drawable.av_5))
        avatarData.add(Avatar(5,0, R.drawable.av_6))
        avatarData.add(Avatar(6,0, R.drawable.av_7))
        avatarData.add(Avatar(7,0, R.drawable.av_8))
        avatarData.add(Avatar(8,0, R.drawable.av_9))
        rvAvatar.adapter = context?.let { AvatarAdapter(it, avatarData, this) }

        rvBackground.layoutManager = GridLayoutManager(context, 5)
        val backgroundData = ArrayList<Avatar>()
        backgroundData.add(Avatar(0,1, R.drawable.bg_alizarin))
        backgroundData.add(Avatar(1,1, R.drawable.bg_carrot))
        backgroundData.add(Avatar(2,1, R.drawable.bg_sun_flower))
        backgroundData.add(Avatar(3,1, R.drawable.bg_emerald))
        backgroundData.add(Avatar(4,1, R.drawable.bg_peter_river))
        rvBackground.adapter = context?.let { AvatarAdapter(it, backgroundData, this) }

        if (!avatar.isBlank()) {
            context?.let { Glide.with(it).load(avatar).centerCrop().into(imvAvatarDialog) }
        }
    }

    override fun onItemClick(item: Avatar) {
        when (item.type) {
            0 -> currentAvatar = item.id
            1 -> currentBackground = item.id
        }

        updateAvatar()
    }

    private fun updateAvatar() {
        if (currentAvatar == 0 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_1).centerCrop().into(imvAvatarDialog) }
            mAvatar = 1
        } else if (currentAvatar == 1 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_2).centerCrop().into(imvAvatarDialog) }
            mAvatar = 2
        } else if (currentAvatar == 2 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_3).centerCrop().into(imvAvatarDialog) }
            mAvatar = 3
        } else if (currentAvatar == 0 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_4).centerCrop().into(imvAvatarDialog) }
            mAvatar = 4
        } else if (currentAvatar == 1 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_5).centerCrop().into(imvAvatarDialog) }
            mAvatar = 5
        } else if (currentAvatar == 2 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_6).centerCrop().into(imvAvatarDialog) }
            mAvatar = 6
        } else if (currentAvatar == 0 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_7).centerCrop().into(imvAvatarDialog) }
            mAvatar = 7
        } else if (currentAvatar == 1 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_8).centerCrop().into(imvAvatarDialog) }
            mAvatar = 8
        } else if (currentAvatar == 2 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_9).centerCrop().into(imvAvatarDialog) }
            mAvatar = 9
        } else if (currentAvatar == 0 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_10).centerCrop().into(imvAvatarDialog) }
            mAvatar = 0
        } else if (currentAvatar == 1 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_11).centerCrop().into(imvAvatarDialog) }
            mAvatar = 11
        } else if (currentAvatar == 2 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_12).centerCrop().into(imvAvatarDialog) }
            mAvatar = 12
        } else if (currentAvatar == 0 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_13).centerCrop().into(imvAvatarDialog) }
            mAvatar = 13
        } else if (currentAvatar == 1 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_14).centerCrop().into(imvAvatarDialog) }
            mAvatar = 14
        } else if (currentAvatar == 2 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_15).centerCrop().into(imvAvatarDialog) }
            mAvatar = 15
        } else if (currentAvatar == 3 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_16).centerCrop().into(imvAvatarDialog) }
            mAvatar = 16
        } else if (currentAvatar == 4 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_17).centerCrop().into(imvAvatarDialog) }
            mAvatar = 17
        } else if (currentAvatar == 5 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_18).centerCrop().into(imvAvatarDialog) }
            mAvatar = 18
        } else if (currentAvatar == 3 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_19).centerCrop().into(imvAvatarDialog) }
            mAvatar = 19
        } else if (currentAvatar == 4 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_20).centerCrop().into(imvAvatarDialog) }
            mAvatar = 20
        } else if (currentAvatar == 5 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_21).centerCrop().into(imvAvatarDialog) }
            mAvatar = 21
        } else if (currentAvatar == 3 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_22).centerCrop().into(imvAvatarDialog) }
            mAvatar = 22
        } else if (currentAvatar == 4 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_23).centerCrop().into(imvAvatarDialog) }
            mAvatar = 23
        } else if (currentAvatar == 5 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_24).centerCrop().into(imvAvatarDialog) }
            mAvatar = 24
        } else if (currentAvatar == 3 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_25).centerCrop().into(imvAvatarDialog) }
            mAvatar = 25
        } else if (currentAvatar == 4 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_26).centerCrop().into(imvAvatarDialog) }
            mAvatar = 26
        } else if (currentAvatar == 5 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_27).centerCrop().into(imvAvatarDialog) }
            mAvatar = 27
        } else if (currentAvatar == 3 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_28).centerCrop().into(imvAvatarDialog) }
            mAvatar = 28
        } else if (currentAvatar == 4 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_29).centerCrop().into(imvAvatarDialog) }
            mAvatar = 29
        } else if (currentAvatar == 5 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_30).centerCrop().into(imvAvatarDialog) }
            mAvatar = 30
        } else if (currentAvatar == 6 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_31).centerCrop().into(imvAvatarDialog) }
            mAvatar = 31
        } else if (currentAvatar == 7 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_32).centerCrop().into(imvAvatarDialog) }
            mAvatar = 32
        } else if (currentAvatar == 8 && currentBackground == 0) {
            context?.let { Glide.with(it).load(R.drawable.avatar_33).centerCrop().into(imvAvatarDialog) }
            mAvatar = 33
        } else if (currentAvatar == 6 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_34).centerCrop().into(imvAvatarDialog) }
            mAvatar = 34
        } else if (currentAvatar == 7 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_35).centerCrop().into(imvAvatarDialog) }
            mAvatar = 35
        } else if (currentAvatar == 8 && currentBackground == 1) {
            context?.let { Glide.with(it).load(R.drawable.avatar_36).centerCrop().into(imvAvatarDialog) }
            mAvatar = 36
        } else if (currentAvatar == 6 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_37).centerCrop().into(imvAvatarDialog) }
            mAvatar = 37
        } else if (currentAvatar == 7 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_38).centerCrop().into(imvAvatarDialog) }
            mAvatar = 38
        } else if (currentAvatar == 8 && currentBackground == 2) {
            context?.let { Glide.with(it).load(R.drawable.avatar_39).centerCrop().into(imvAvatarDialog) }
            mAvatar = 39
        } else if (currentAvatar == 6 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_40).centerCrop().into(imvAvatarDialog) }
            mAvatar = 40
        } else if (currentAvatar == 7 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_41).centerCrop().into(imvAvatarDialog) }
            mAvatar = 41
        } else if (currentAvatar == 8 && currentBackground == 3) {
            context?.let { Glide.with(it).load(R.drawable.avatar_42).centerCrop().into(imvAvatarDialog) }
            mAvatar = 42
        } else if (currentAvatar == 6 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_43).centerCrop().into(imvAvatarDialog) }
            mAvatar = 43
        } else if (currentAvatar == 7 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_44).centerCrop().into(imvAvatarDialog) }
            mAvatar = 44
        } else if (currentAvatar == 8 && currentBackground == 4) {
            context?.let { Glide.with(it).load(R.drawable.avatar_45).centerCrop().into(imvAvatarDialog) }
            mAvatar = 45
        }
    }

    override fun onClickDone() {
        val activityUpdate = activity as UpdateActivity
        activityUpdate.updateAvatar(mAvatar)
        dismiss()
    }
}
