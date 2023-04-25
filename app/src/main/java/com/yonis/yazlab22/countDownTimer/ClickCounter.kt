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
        var isIce:Int=0

        for(pos in 0..87){
            if(GameActivity.courseList.get(pos).courseText != "."){
                text=GameActivity.courseList.get(pos).courseText
                backgrund=GameActivity.courseList.get(pos).background
                backColor=GameActivity.courseList.get(pos).backColor
                isIce=GameActivity.courseList.get(pos).ice
                dropItem(pos,text,backgrund,backColor,isIce)
            }
            if(GameActivity.courseList.get(pos).ice==2){
               makeItIce(pos)
                GameActivity.courseList.get(pos).ice=1
            }
        }
        GameActivity.courseRVAdapter.notifyDataSetChanged()
    }
    fun dropItem(pos:Int,text:String,background:Int,backColor:Int,isIce:Int): Int {
        val rand=(40..87).random()
        val color=GameActivity.courseList.get(rand).backColor
        if(pos>8) {
            if (GameActivity.courseList.get(pos - 8).courseText.equals(".")) {
                GameActivity.courseList.get(pos).courseText = "."
                GameActivity.courseList.get(pos).backColor=color
                GameActivity.courseList.get(pos - 8).courseText = text
                GameActivity.courseList.get(pos - 8).background =background
                GameActivity.courseList.get(pos - 8).backColor =backColor
                GameActivity.courseList.get(pos - 8).ice =isIce
            } else {
                return 1
            }
        }
            return 0
    }

    fun makeItIce(pos:Int){
        val iceList = mutableListOf<Int>()
        var icePos=pos-9
        if(pos%8!=0){
            for(i in 0..2){
                for(j in 0..2){
                    if(icePos+j < 80){
                        iceList.add(icePos+j)
                    }
                }
                icePos+=8
            }
        }else{
            for(i in 0..2){
                for(j in 0..2){
                    if(icePos+j < 80 && icePos+j!=pos-1 && icePos+j!=pos-9){
                        iceList.add(icePos+j)
                    }
                }
                icePos+=8
            }

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