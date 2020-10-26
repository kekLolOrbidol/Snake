package com.snake.eat.finish.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IRecordsContract
import com.snake.eat.finish.presenter.RecordsPresenter
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class RecordsFragment: Fragment(), IRecordsContract.View {

    private lateinit var mPresenter: IRecordsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = RecordsPresenter(this)
        mPresenter.initLeadersRv(rv_leaders, activity!!.applicationContext)
        iv_leader_back_btn.setOnClickListener {
            mPresenter.goBack()
        }
    }

    override fun showHome() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, MainScreenFragment()).commit()
    }

    override fun showEmptyMessage() {
        tv_leader_empty_message.visibility = View.VISIBLE
    }
}