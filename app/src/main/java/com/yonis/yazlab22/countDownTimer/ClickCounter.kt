package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import com.yonis.yazlab22.activity.GameActivity
import com.yonis.yazlab22.model.CourseRVModal

class ClickCounter (private val context: Context, countUpInterval: Long) : CountUpTimer(countUpInterval){
    override fun onTick(millisElapsed: Long) {
        var text:String=""

        for(pos in 0..87){
            if(GameActivity.courseList.get(pos).courseText != "."){
                text=GameActivity.courseList.get(pos).courseText
                dropItem(pos,text)
            }
        }
        GameActivity.courseRVAdapter.notifyDataSetChanged()
    }
    fun dropItem(pos:Int,text:String): Int {
        if(pos>8) {
            if (GameActivity.courseList.get(pos - 8).courseText.equals(".")) {
                GameActivity.courseList.get(pos).courseText = "."
                GameActivity.courseList.get(pos - 8).courseText = text
            } else {
                return 1
            }
        }

            return 0

    }

}