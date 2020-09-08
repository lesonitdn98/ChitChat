package me.lesonnnn.chitchat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import me.lesonnnn.chitchat.R

class TestActivity : AppCompatActivity() {

    companion object {
        private var instance: Intent? = null

        @JvmStatic
        fun getIntent(context: Context) = instance ?: synchronized(this) {
            instance
                ?: Intent(context, TestActivity::class.java).also { instance = it }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    }

    private fun getTextInputLayoutTopSpace(): Int {
        var currentView: View = tilSearch
        var space = 0
        do {
            space += currentView.top
            currentView = currentView.parent as View
        } while (currentView.id != tilSearch.id)
        return space
    }

    private fun updateHintPosition(hasFocus: Boolean, hasText: Boolean) {
        if (hasFocus || hasText) {
            tilSearch.setPadding(0, 0, 0, 0)
        } else {
            tilSearch.setPadding(0, 0, 0, getTextInputLayoutTopSpace())
        }
    }
}
