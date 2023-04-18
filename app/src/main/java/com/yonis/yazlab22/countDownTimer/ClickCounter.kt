package com.yonis.yazlab22.countDownTimer

import CountUpTimer
import android.content.Context
import com.yonis.yazlab22.R
import com.yonis.yazlab22.activity.GameActivity
import com.yonis.yazlab22.model.CourseRVModal

class ClickCounter (private val context: Context, countUpInterval: Long) : CountUpTimer(countUpInterval){
    override fun onTick(millisElapsed: Long) {
        var text:String=""
        var backgrund:Int=0
        var backColor:Int=0

        for(pos in 0..87){
            if(GameActivity.courseList.get(pos).courseText != "."){
                text=GameActivity.courseList.get(pos).courseText
                backgrund=GameActivity.courseList.get(pos).background
                backColor=GameActivity.courseList.get(pos).backColor
                dropItem(pos,text,backgrund,backColor)
            }
            if(GameActivity.courseList.get(pos).ice==2){
               //makeItIce(pos)
            }
        }
        GameActivity.courseRVAdapter.notifyDataSetChanged()
    }
    fun dropItem(pos:Int,text:String,background:Int,backColor:Int): Int {
        if(pos>8) {
            if (GameActivity.courseList.get(pos - 8).courseText.equals(".")) {
                GameActivity.courseList.get(pos).courseText = "."
                GameActivity.courseList.get(pos - 8).courseText = text
                GameActivity.courseList.get(pos - 8).background =background
                GameActivity.courseList.get(pos - 8).backColor =backColor
            } else {
                return 1
            }
        }
            return 0
    }
    fun makeItIce(pos:Int){
        val iceList = mutableListOf<Int>()
        var icePos=pos-9
        for(i in 0..2){
            for(j in 0..2){
                if(icePos+j < 80){
                    iceList.add(icePos+j)
                }
            }
            icePos++
        }
        for(i in 0..iceList.size-1){
            if(GameActivity.courseList.get(iceList.get(i)).ice == 0){
                GameActivity.courseList.get(iceList.get(i)).ice=1
                GameActivity.courseList.get(iceList.get(i)).backColor= R.color.ice
            }else{
                continue
            }
        }
    }

}