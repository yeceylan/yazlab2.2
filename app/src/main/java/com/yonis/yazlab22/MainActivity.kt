package com.yonis.yazlab22

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>
    var pastId:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseRV = findViewById(R.id.idRVCourses)

        courseList = ArrayList()

        //val layoutManager = GridLayoutManager(this, 8)
        courseRV.setLayoutManager(
            GridLayoutManager(
                this,
                8,
                LinearLayoutManager.VERTICAL,
                true
            )
        )

        //courseRV.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = CourseRVAdapter(courseList, clickedCard = ::clickedCard)

        // on below line we are setting adapter to our recycler view.
        courseRV.adapter = courseRVAdapter

        for (i in 1..15) {
            val rand = ('A'..'Z').random()
            courseList.add(CourseRVModal(i,rand.toString()))
        }

        // on below line we are notifying adapter that data has been updated.
        courseRVAdapter.notifyDataSetChanged()

    }
private fun clickedCard(id:Int,letter:String){
    val temp=idText.text.toString()
    if(id!== pastId){
        idText.text=temp + letter
        pastId=id
    }else{
        idText.text=temp.dropLast(1)
    }

}
}
