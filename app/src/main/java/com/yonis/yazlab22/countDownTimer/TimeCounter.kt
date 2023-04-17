package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import com.yonis.yazlab22.activity.GameActivity
import com.yonis.yazlab22.model.CourseRVModal

class TimeCounter(private val context: Context, countUpInterval: Long) : CountUpTimer(countUpInterval) {

    override fun onTick(millisElapsed: Long) {
        val vowels = arrayOf('A', 'E', 'I', 'İ', 'O', 'Ö', 'U', 'Ü')
        val turkishLetters = "BCÇDFGĞHJKLMNPRSŞTVYZ"
        val stringList = mutableListOf<String>()

        for (i in 0..8) {
            stringList.add(vowels.random().toString())
        }
        for (i in 0..15) {
            stringList.add(turkishLetters.random().toString())
        }
        stringList.shuffle()
        val rnds = (71..87).random()
       GameActivity.courseList.get(rnds).courseText=stringList.get(0)

    }
}