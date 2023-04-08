package com.yonis.yazlab22.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.yonis.yazlab22.model.CourseRVModal
import com.yonis.yazlab22.R
import java.util.*
import kotlin.collections.ArrayList

// on below line we are creating
// a course rv adapter class.
class CourseRVAdapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<CourseRVModal>,
    val clickedCard: (id:Int,letter:String) -> Unit
) : RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_rv_item,
            parent, false
        )

        // at last we are returning our view holder
        // class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        /*val rnd = Random()
        val currentStrokeColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.itemView.setBackgroundColor(currentStrokeColor)*/
        //eklenirken rengini rastgele yapıyor fakat notify her seferinde çalışmasına sebep oluyor



        if(courseList.get(position).courseText == "*"){
           holder.itemView.setVisibility(View.GONE)
        }
        holder.courseIV.text = courseList.get(position).courseText
        holder.itemView.setOnClickListener {
            clickedCard(courseList.get(position).id,courseList.get(position).courseText)
            //holder.itemView.setBackgroundColor(Color.BLUE)
        }

    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        //val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
        val courseIV: TextView = itemView.findViewById(R.id.idIVCourse)

    }
}
