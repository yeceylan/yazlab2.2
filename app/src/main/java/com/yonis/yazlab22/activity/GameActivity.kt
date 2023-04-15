package com.yonis.yazlab22.activity


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yonis.yazlab22.R
import com.yonis.yazlab22.adapter.CourseRVAdapter
import com.yonis.yazlab22.countDownTimer.TimeCounter
import com.yonis.yazlab22.model.CourseRVModal
import com.yonis.yazlab22.model.LetterStartEnd
import kotlinx.android.synthetic.main.activity_game_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class GameActivity : AppCompatActivity() {

    var handler = Handler()
    var runnable = Runnable { }
    var time = 0L
    var pastTime: Int = 1
    var pastId: Int = 0
    lateinit var pastIdList: ArrayList<Int>
    private lateinit var buttonX: Button
    private lateinit var buttonTick: Button
    private lateinit var readTextFromAssets: ArrayList<String>
    private lateinit var countUpTimer: TimeCounter

    companion object{
        lateinit var  courseList: ArrayList<CourseRVModal>
        lateinit var courseRVAdapter: CourseRVAdapter
        lateinit var courseRV: RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readTextFromAssets = ArrayList()
        setContentView(R.layout.activity_game_fragment)
        courseRV = findViewById(R.id.idRVCourses)
        courseList = ArrayList()
        courseRV.setLayoutManager(
            GridLayoutManager(
                this,
                8,
                LinearLayoutManager.VERTICAL,
                true
            )
        )
        //for file reading and adding list
        // on below line we are initializing our adapter
        courseRVAdapter = CourseRVAdapter(courseList, clickedCard = ::clickedCard)
        // on below line we are setting adapter to our recycler view.
        courseRV.adapter = courseRVAdapter
        generateCourseList()
        // on below line we are notifying adapter that data has been updated.
        pastIdList = ArrayList()
        loadTextFromAssets()
        countUpTimer= TimeCounter(applicationContext,4000)
        countUpTimer.onTick(4000)
        countUpTimer.start()

    }

//    fun addLetter(){
//        val timer = Timer()
//        val task = object : TimerTask() {
//            override fun run() {
//                runOnUiThread {
//                    courseRVAdapter.notifyDataSetChanged()
//                }
//            }
//        }
//        val delay = 0L
//        val period = 4000L
//        timer.schedule(task, delay, period)
//
//    }
    override fun onResume() {
        super.onResume()

        buttonX = findViewById<Button>(R.id.buttonX)
        buttonTick = findViewById<Button>(R.id.buttonTick)

        buttonX.setOnClickListener {
            deleteText()
        }
        buttonTick.setOnClickListener {
            if (idText.length() < 3) {
                Toast.makeText(applicationContext, "Min 3 Letter Pls'", Toast.LENGTH_SHORT).show()

            } else {

                println(compareText())
            }
        }
    }

    private fun generateCourseList() {
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
        for (i in 0..23) {
            courseList.add(CourseRVModal(i, stringList.get(i)))

        }
    }

    private fun clickedCard(id: Int, letter: String) {
        val temp = idText.text.toString()

        // Find the clicked view in the RecyclerView
        var clickedView = courseRV.findViewHolderForAdapterPosition(id)?.itemView
        // Change the background color of the clicked view
        val rnd = Random()
        val currentStrokeColor =
            Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        clickedView?.setBackgroundColor(currentStrokeColor)
        if (id != pastId) {
            //for doesnt remove before letters
            if (!pastIdList.contains(id)) {
                idText.text = temp + letter
                pastId = id
                pastIdList.add(id)
            }
        } else {
            clickedView?.setBackgroundColor(Color.WHITE)
            idText.text = temp.dropLast(1)
            pastIdList.remove(pastId);
            //doesn't erase the previous letters again=>fixed
            if (!pastIdList.isEmpty()) {
                pastId = pastIdList.last()
            } else {
                pastId = -1
            }
        }

    }

    private fun compareText(): Boolean {
        val firstChar = idText.text.toString().substring(0, 1).lowercase()
        var start = 0;
        var end = 0;
        for (i in LetterStartEnd.letterStartEndArrayList) {
            if (i.lettername.equals(firstChar))
                start = i.start
            end = i.end
        }

        var sublist = readTextFromAssets.subList(start, end)
        for (i in sublist) {
            if (i.equals(idText.text.toString().lowercase())) {
                println(idText.text.toString())
                pastIdList.reverse()
                for (j in 0 until pastIdList.size) {
                    var pos = courseList.get(pastIdList.get(j)).id
                    while (courseList.size > pos + 8) {
                         if(courseList.size > pos + 16 && pastIdList.contains(courseList.get(pos + 8).id )){
                            courseList.get(pos).courseText = "."

                            pos += 8
                        }
                      else  if (courseList.size > pos + 16 && pastIdList.contains(courseList.get(pos + 16).id )) {
                            courseList.get(pos).courseText = "."
                            courseList.get(pos).courseText =
                                courseList.get(pos + 8).courseText
                            pos += 8
                        }
                        else {
                            courseList.get(pos).courseText =
                                courseList.get(pos + 8).courseText
                            pos += 8
                        }
                    }
                    courseList.get(pos).courseText = "."
                }
                deleteText()
                sortList()
                courseRVAdapter.notifyDataSetChanged()

                return true
            }
        }
        return false;
    }


    private fun sortList() {
        var counter = 0;
        for (i in courseList) {
            i.id = counter++
        }
    }

    private fun deleteText() {
        idText.text = ""
        for (i in pastIdList) {
            var clickedView = courseRV.findViewHolderForAdapterPosition(i)?.itemView
            clickedView?.setBackgroundColor(Color.WHITE)
        }
        pastIdList.clear()
    }

    private fun loadTextFromAssets() {
        GlobalScope.launch {
            val fileContent = readTextFromAssets(applicationContext, "veri.txt")
            readTextFromAssets.add(fileContent)
        }
    }


    fun readTextFromAssets(context: Context, fileName: String): String {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val scanner = Scanner(inputStream)
        val stringBuilder = StringBuilder()

        while (scanner.hasNextLine()) {
            readTextFromAssets.add(scanner.nextLine())
            stringBuilder.append(System.getProperty("line.separator"))
        }
        scanner.close()
        return stringBuilder.toString()
    }

}