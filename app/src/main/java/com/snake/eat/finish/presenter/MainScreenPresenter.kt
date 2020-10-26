package com.snake.eat.finish.presenter

import com.snake.eat.finish.contract.IMainScreenContract

class MainScreenPresenter(val mView: IMainScreenContract.View): IMainScreenContract.Presenter {

    override fun play() {
        mView.showGame()
    }

    override fun openLeaderboards() {
        mView.showLeaders()
    }
}