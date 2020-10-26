package com.snake.eat.finish.model

import com.snake.eat.finish.App
import com.snake.eat.finish.model.entity.Result

class RecordModel {

    fun saveResult(name: String, scores: Int){
        App.resultDb!!.resultDao().insert(Result(null, name, scores))
    }
}