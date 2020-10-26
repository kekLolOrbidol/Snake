package com.snake.eat.finish.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IResultContract
import com.snake.eat.finish.presenter.ResultPresenter
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment: Fragment(), IResultContract.View {

    private lateinit var mPresenter: IResultContract.Presenter

    companion object {
        fun newInstance(scores: Int): ResultFragment{
            val mBundle = Bundle()
            mBundle.putInt("scores_count", scores)
            val mFragment = ResultFragment()
            mFragment.arguments = mBundle
            return mFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = ResultPresenter(this)
        mPresenter.frOnCreated()

        iv_result_home_btn.setOnClickListener {
            mPresenter.goHome()
        }

        iv_result_restart_btn.setOnClickListener {
            mPresenter.restartGame()
        }

        iv_result_save_btn.setOnClickListener {
            mPresenter.saveResult()
        }
    }

    override fun getScores(): Int {
        return arguments!!.getInt("scores_count")
    }

    override fun showHome() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, MainScreenFragment()).commit()
    }

    override fun showGame() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, PlayFragment()).commit()
    }

    override fun showSave(scores: Int) {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, SavingFragment.newInstance(scores)).commit()
    }

    override fun showScores(scores: Int) {
        tv_result_scores.text = scores.toString()
    }
}