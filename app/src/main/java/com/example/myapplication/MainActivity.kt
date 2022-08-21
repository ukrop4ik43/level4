package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import com.example.myapplication.Constants.DOG_SIGN

import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        Log.d(TAG, "$message")
        var name = addingNames(message)
        name = name.replace(oldChar = '.', newChar = ' ', ignoreCase = false)
        transformingName(name)
    }

    private fun transformingName(name: String) {
        for (index in name.indices) {
            if (name[index] == ' ') {
                break
            }
        }
        binding.UserNameTextView.text = name.capitalizeWords()
    }

    private fun String.capitalizeWords() = split(" ")
        .joinToString(" ") { it -> it.lowercase().replaceFirstChar { it.uppercase() } }



    private fun addingNames(message: String?): String {

        val index = message?.indexOf(DOG_SIGN) ?: -1
        return message?.substring(
            0, if (index < 0) message.length else index
        ).toString()
    }

    private companion object {
        private const val TAG = "MainActivity"
    }
}