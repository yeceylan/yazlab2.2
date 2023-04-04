package com.yonis.yazlab22.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yonis.yazlab22.R
import com.yonis.yazlab22.adapter.CourseRVAdapter
import com.yonis.yazlab22.model.CourseRVModal
import kotlinx.android.synthetic.main.activity_game_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class GameFragment : AppCompatActivity() {


    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>
    var pastId: Int = 0
    lateinit var pastIdList: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_fragment)
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

        for (i in 1..24) {
            val rand = ('A'..'Z').random()
            courseList.add(CourseRVModal(i, rand.toString()))
        }
        // on below line we are notifying adapter that data has been updated.
        courseRVAdapter.notifyDataSetChanged()
        //get text in assert file
        //  val text = applicationContext?.let { readTextFromAssets(it, "veri.txt") }
        // println(text)
        pastIdList = ArrayList()

    }

    private fun clickedCard(id: Int, letter: String) {
        val temp = idText.text.toString()

        // Find the clicked view in the RecyclerView
        var clickedView = courseRV.findViewHolderForAdapterPosition(id - 1)?.itemView
        // Change the background color of the clicked view
        clickedView?.setBackgroundColor(Color.BLUE)
        if (id != pastId) {
            //for doesnt remove before letters
            if(!pastIdList.contains(id)){
                idText.text = temp + letter
                pastId = id
                pastIdList.add(id)
            }
        }
        else {
            clickedView?.setBackgroundColor(Color.WHITE)
            idText.text = temp.dropLast(1)
            pastIdList.remove(pastId);
            //doesn't erase the previous letters again
            pastId=0;

        }

    }

    fun readTextFromAssets(context: Context, fileName: String): String {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val scanner = Scanner(inputStream)
        val stringBuilder = StringBuilder()

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
            stringBuilder.append(System.getProperty("line.separator"))
        }
        scanner.close()
        return stringBuilder.toString()
    }
}