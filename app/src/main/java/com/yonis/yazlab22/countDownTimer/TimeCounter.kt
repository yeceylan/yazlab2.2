package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import com.yonis.yazlab22.activity.GameActivity
import com.yonis.yazlab22.model.CourseRVModal

class TimeCounter(private val context: Context, countUpInterval: Long) : CountUpTimer(countUpInterval) {

    override fun onTick(millisElapsed: Long) {
        val rnds = (71..87).random()
       GameActivity.courseList.get(rnds).courseText="a"

    }
}