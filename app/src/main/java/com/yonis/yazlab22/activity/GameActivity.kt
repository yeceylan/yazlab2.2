package com.yonis.yazlab22.activity


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yonis.yazlab22.R
import com.yonis.yazlab22.adapter.CourseRVAdapter
import com.yonis.yazlab22.countDownTimer.ClickCounter
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
    var p: Int = 0
    var health: Int = 3
    var timer: Long = 5000L
    lateinit var pastIdList: ArrayList<Int>
    private lateinit var buttonX: Button
    private lateinit var point: TextView
    private lateinit var healt1: TextView
    private lateinit var healt2: TextView
    private lateinit var buttonTick: Button
    private lateinit var readTextFromAssets: ArrayList<String>
    private lateinit var countUpTimer: TimeCounter
    private lateinit var clickTimer: ClickCounter

    companion object {
        lateinit var courseList: ArrayList<CourseRVModal>
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
        courseRVAdapter.notifyDataSetChanged()
        pastIdList = ArrayList()
        loadTextFromAssets()
        countUpTimer = TimeCounter(applicationContext, timer)
        countUpTimer.start()

    }

    override fun onResume() {
        super.onResume()
        buttonX = findViewById<Button>(R.id.buttonX)
        buttonTick = findViewById<Button>(R.id.buttonTick)
        healt1 = findViewById(R.id.healt1)
        healt2 = findViewById(R.id.healt2)

        buttonX.setOnClickListener {
            deleteText()
        }
        buttonTick.setOnClickListener {
            if (idText.length() < 3) {
                Toast.makeText(applicationContext, "Min 3 Letter Pls'", Toast.LENGTH_SHORT).show()

            } else {
                if (!compareText()) {
                    getEightLetter()
                    if (health == 3) {
                        health--
                        healt1.setTextColor(Color.RED)
                    } else if (health == 2) {
                        health--
                        healt2.setTextColor(Color.RED)
                    } else {
                        val intent = Intent(this, ScoreBoard::class.java)
                        intent.putExtra("point", p)
                        startActivity(intent)
                    }
                }

            }

        }

        clickTimer = ClickCounter(applicationContext, 1000)
        clickTimer.onTick(1000)
        clickTimer.start()
        sortList()
    }

    private fun createList(countVoew: Int, countConsonant: Int): List<String> {
        val vowels = arrayOf('A', 'E', 'I', 'İ', 'O', 'Ö', 'U', 'Ü')
        val turkishLetters = "BCÇDFGĞHJKLMNPRSŞTVYZ"
        val stringList = mutableListOf<String>()

        for (i in 0..countVoew) {
            stringList.add(vowels.random().toString())
        }
        for (i in 0..countConsonant) {
            stringList.add(turkishLetters.random().toString())
        }
        stringList.shuffle()
        return stringList
    }

    private fun getEightLetter() {
        val vowels = "AEIİOÖUÜ"
        var stringList: List<String>

        stringList = createList(8, 15)
        var temp = 0
        for (i in 79..87) {
            if (vowels.contains(stringList.get(temp))) {
                courseList.get(i).courseText = stringList.get(temp)
                courseList.get(i).background = R.drawable.oval_background
            } else {
                courseList.get(i).courseText = stringList.get(temp)
                courseList.get(i).background = R.drawable.rectangle_background
            }
            temp++
        }

    }

    private fun addToList(start: Int, end: Int, stringList: List<String>) {
        val vowels = "AEIİOÖUÜ"

        for (i in start..end) {
            var color = generateRandColor()
            if (vowels.contains(stringList.get(i))) {
                courseList.add(
                    CourseRVModal(
                        i,
                        stringList.get(i),
                        false,
                        color,
                        R.drawable.oval_background,
                        0
                    )
                )
            } else {
                courseList.add(
                    CourseRVModal(
                        i,
                        stringList.get(i),
                        false,
                        color,
                        R.drawable.rectangle_background,
                        0
                    )
                )
            }

        }
    }

    private fun generateRandColor(): Int {
        val colorList = mutableListOf<Int>()
        val fields = Class.forName("$packageName.R\$color").declaredFields
        for (field in fields) {
            val colorId = field.getInt(null)
            if (colorId > R.color.black && colorId < R.color.white) {
                colorList.add(colorId)
            } else {
                continue
            }
        }
        colorList.shuffle()
        return colorList.get(0)
    }

    private fun generateCourseList() {

        var stringList: List<String>

        stringList = createList(8, 15)
        addToList(0, 23, stringList)

        val dotList = mutableListOf<String>()

        for (i in 0..63) {
            dotList.add(".")
        }
        addToList(0, 63, dotList)

    }

    private fun clickedCard(id: Int, letter: String) {
        val temp = idText.text.toString()

        // Find the clicked view in the RecyclerView
        var clickedView = courseRV.findViewHolderForAdapterPosition(id)?.itemView
        // Change the background color of the clicked view

        if (id != pastId) {
            //for doesnt remove before letters
            if (!pastIdList.contains(id)) {
                idText.text = temp + letter
                pastId = id
                pastIdList.add(id)
            }
        } else {

            clickedView?.backgroundTintList =
                ContextCompat.getColorStateList(
                    applicationContext,
                    courseList.get(pastId).backColor
                )

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

                for (j in 0 until pastIdList.size) {
                    if (courseList.get(pastIdList.get(j)).ice == 2 || courseList.get(
                            pastIdList.get(
                                j
                            )
                        ).ice == 1
                    ) {
                        courseList.get(pastIdList.get(j)).ice = 0
                    } else if (courseList.get(pastIdList.get(j)).ice == 0) {
                        courseList.get(pastIdList.get(j)).courseText = "."
                    } else {

                    }

                }
                getPoint()
                deleteText()
                sortList()
                makeFalse()
                courseRVAdapter.notifyDataSetChanged()
                return true
            }
        }
        return false;
    }

    private fun getPoint(): Int {
        point = findViewById(R.id.point)
        for (i in idText.text.toString().lowercase()) {
            for (j in LetterStartEnd.letterStartEndArrayList) {
                if (j.lettername.toString().equals(i.toString())) {
                    p += j.point
                }
            }
        }
        point.text = p.toString()
        return p
    }

    private fun sortList() {
        var counter = 0;
        for (i in courseList) {
            i.id = counter++
        }
    }

    private fun makeFalse() {
        for (i in courseList) {
            i.isClicked = false
        }
    }

    //düzenlenmesi gerek
    private fun deleteText() {
        idText.text = ""
        for (i in pastIdList) {
            var clickedView = courseRV.findViewHolderForAdapterPosition(i)?.itemView
            clickedView?.setBackgroundColor(Color.WHITE)
        }
        pastIdList.clear()
        makeFalse()
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