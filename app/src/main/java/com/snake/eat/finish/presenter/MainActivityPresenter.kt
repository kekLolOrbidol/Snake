package com.snake.eat.finish.presenter

import com.snake.eat.finish.contract.IMainContract

class MainActivityPresenter(val mView: IMainContract.View) : IMainContract.Presenter {

    override fun acOnCreated() {
        mView.showStart()
    }
}