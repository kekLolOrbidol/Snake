package com.snake.eat.finish.contract

interface IPlayContract {

    interface View{
        fun showScores(scores: Int)
        fun showResult(scores: Int)
        fun showMessageExit()
        fun pauseGame()
        fun goRight()
        fun goLeft()
        fun goTop()
        fun goBottom()
    }

    interface Presenter{
        fun frOnCreated()
        fun right()
        fun left()
        fun top()
        fun bottom()
    }
}