package com.snake.eat.finish.presenter

import com.snake.eat.finish.contract.ISavingContract
import com.snake.eat.finish.model.RecordModel

class SavingPresenter(val mView: ISavingContract.View): ISavingContract.Presenter {

    private val mModel = RecordModel()

    override fun frOnCreated() {
        mView.showScores(mView.getScores())
    }

    override fun saveResult() {
        if (mView.getName().isEmpty())
            mView.showInputError()
        else{
            mModel.saveResult(mView.getName(), mView.getScores())
            mView.showLeaders()
        }
    }
}