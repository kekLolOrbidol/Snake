package com.snake.eat.finish.contract

interface IMainContract {

    interface View{
        fun showStart()
    }

    interface Presenter{
        fun acOnCreated()
    }
}