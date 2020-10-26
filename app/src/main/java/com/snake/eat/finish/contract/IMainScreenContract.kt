package com.snake.eat.finish.contract

interface IMainScreenContract {

    interface View{
        fun showGame()
        fun showLeaders()
    }

    interface Presenter{
        fun play()
        fun openLeaderboards()
    }
}