package com.snake.eat.finish.presenter

import com.snake.eat.finish.contract.ITroubleContract

class TroublePresenter(val mView: ITroubleContract.View): ITroubleContract.Presenter {

    override fun goHome() {
        mView.showInfo()
    }
}