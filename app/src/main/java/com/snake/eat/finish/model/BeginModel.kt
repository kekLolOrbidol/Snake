package com.snake.eat.finish.model

import com.snake.eat.finish.BuildConfig

class BeginModel {
    val code = BuildConfig.code
    val input = Array<Int>(code.length, {-1})

    fun setInput(number: Int){
        for (i in 0 until input.size){
            if (input[i] == -1){
                input[i] = number
                return
            }
        }
    }

    fun inputToString(): String{
        var res = ""
        for (i in 0 until input.size){
            res += input[i].toString()
        }
        return res
    }
}