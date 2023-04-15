package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import android.view.animation.AnimationUtils
import androidx.core.view.size
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yonis.yazlab22.R
import com.yonis.yazlab22.activity.GameActivity
import com.yonis.yazlab22.model.CourseRVModal

class TimeCounter(private val context: Context, countUpInterval: Long) : CountUpTimer(countUpInterval) {

    override fun onTick(millisElapsed: Long) {
        val newCourse = CourseRVModal(GameActivity.courseList.size, "a")
        GameActivity.courseList.add(newCourse)
        GameActivity.courseRVAdapter.notifyDataSetChanged()
    }
}
