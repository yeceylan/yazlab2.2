package com.yonis.yazlab22.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yonis.yazlab22.R

class ScoreBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
        val point = intent.getIntExtra("point", 0)
        val message = when (point) {
            in 0..50 -> "Keep practicing! You can do better."
            in 51..75 -> "Good job! You're getting better."
            in 76..Int.MAX_VALUE -> "Excellent! You're a pro now."
            else -> "Invalid score"
        }

        val emoji = when (point) {
            in 0..50 -> "😕"
            in 51..75 -> "😊"
            in 76..Int.MAX_VALUE -> "👍"
            else -> "❌"
        }

        findViewById<TextView>(R.id.scoreTextView).text = point.toString()
        findViewById<TextView>(R.id.messageTextView).text = message
        findViewById<TextView>(R.id.emojiTextView).text = emoji
    }

}