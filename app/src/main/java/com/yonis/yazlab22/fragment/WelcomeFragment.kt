package com.yonis.yazlab22.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.yonis.yazlab22.R


class WelcomeFragment : Fragment() {
    private lateinit var startGameButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startGameButton=view.findViewById(R.id.startGameButton)
        startGameButton.setOnClickListener{
            val action=R.id.action_welcomeFragment_to_gameFragment;
            Navigation.findNavController(it).navigate(action);
        }

    }

}