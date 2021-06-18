package com.android.sealedclassdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.android.sealedclassdemo.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private val chatType: ChatType by lazy { intent.extras?.getSerializable(CHAT_TYPE) as ChatType? ?: throw IllegalStateException("You must pass $CHAT_TYPE parameter by intent") }

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        setTitle(getActionBarTitleRes())

        binding.textViewChatName.text = getChatName()
        binding.textViewChatStatusOrDescription.text = getChatStatusOrDescription()

        getChatCreationDate()?.let { creationDate ->
            binding.textViewChatCreationDate.text = creationDate
        } ?: kotlin.run {
            binding.textViewChatCreationDate.visibility = View.GONE
        }
    }

    @StringRes
    private fun getActionBarTitleRes(): Int {
        return when (chatType) {
            is ChatType.Personal -> {
                R.string.chat_personal
            }
            is ChatType.Group -> {
                R.string.chat_group
            }
        }
    }

    private fun getChatName(): String {
        return when (chatType) {
            is ChatType.Personal -> {
                getString(R.string.chat_personal_name, (chatType as ChatType.Personal).personName)
            }
            is ChatType.Group -> {
                getString(R.string.chat_group_name, (chatType as ChatType.Group).groupName)
            }
        }
    }

    private fun getChatStatusOrDescription(): String {
        return when (chatType) {
            is ChatType.Personal -> {
                getString(R.string.chat_personal_status, (chatType as ChatType.Personal).status)
            }
            is ChatType.Group -> {
                getString(R.string.chat_group_description, (chatType as ChatType.Group).description)
            }
        }
    }

    private fun getChatCreationDate(): String? {
        return when (chatType) {
            is ChatType.Personal -> {
                null
            }
            is ChatType.Group -> {
                getString(R.string.chat_group_creation_date, (chatType as ChatType.Group).creationDate.toString())
            }
        }
    }

    companion object {
        const val CHAT_TYPE = "CHAT_TYPE"
    }
}