package com.vinoth.interview.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vinoth.interview.R

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_activity)
    }

    fun nextAction(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}