package com.snake.eat.finish.contract

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface IRecordsContract {

    interface View{
        fun showHome()
        fun showEmptyMessage()
    }

    interface Presenter{
        fun goBack()
        fun initLeadersRv(recyclerView: RecyclerView, context: Context)
    }
}