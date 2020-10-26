package com.snake.eat.finish.presenter

import com.snake.eat.finish.contract.IResultContract

class ResultPresenter(val mView: IResultContract.View): IResultContract.Presenter {

    override fun frOnCreated() {
        mView.showScores(mView.getScores())
    }

    override fun goHome() {
        mView.showHome()
    }

    override fun restartGame() {
        mView.showGame()
    }

    override fun saveResult() {
        mView.showSave(mView.getScores())
    }
}