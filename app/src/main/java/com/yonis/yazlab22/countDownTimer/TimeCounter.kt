package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import com.yonis.yazlab22.R
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
        val rnd = 9
       GameActivity.courseList.get(rnds).courseText=stringList.get(0)
        if(rnd==9) {
            GameActivity.courseList.get(rnds).background= R.drawable.ice_background
            GameActivity.courseList.get(rnds).backColor=R.color.ice
            GameActivity.courseList.get(rnds).ice=2
        }else{
            if(turkishLetters.contains(stringList.get(0))){
                GameActivity.courseList.get(rnds).background= R.drawable.rectangle_background
            }else{
                GameActivity.courseList.get(rnds).background=R.drawable.oval_background
            }
        }


    }
}