package com.snake.eat.finish.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.snake.eat.finish.R
import kotlinx.android.synthetic.main.fragment_error.*

class TroubleActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_error)

        tv_finish_btn.setOnClickListener {
            finish()
        }
    }
}