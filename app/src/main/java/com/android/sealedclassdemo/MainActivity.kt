package com.android.sealedclassdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.sealedclassdemo.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonPersonal.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java).apply {
                putExtra(
                    ChatActivity.CHAT_TYPE,
                    ChatType.Personal(
                        personName = "Mark Zuckerberg",
                        status = "Hi! I'm using this demo app"
                    )
                )
            })
        }

        binding.buttonGroup.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java).apply {
                putExtra(
                    ChatActivity.CHAT_TYPE,
                    ChatType.Group(
                        groupName = "Android Developers Group",
                        description = "This group means to help Android Developers ;)",
                        creationDate = Date()
                    )
                )
            })
        }
    }
}