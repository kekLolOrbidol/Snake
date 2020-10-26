package com.snake.eat.finish.contract

interface IResultContract {

    interface View{
        fun getScores(): Int
        fun showHome()
        fun showGame()
        fun showSave(scores: Int)
        fun showScores(scores: Int)
    }

    interface Presenter{
        fun frOnCreated()
        fun goHome()
        fun restartGame()
        fun saveResult()
    }
}