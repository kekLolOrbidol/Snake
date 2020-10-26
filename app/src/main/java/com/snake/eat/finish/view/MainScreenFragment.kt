package com.snake.eat.finish.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IMainScreenContract
import com.snake.eat.finish.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.fragment_home.*

class MainScreenFragment: Fragment(), IMainScreenContract.View {

    private lateinit var mPresenter: IMainScreenContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mPresenter = MainScreenPresenter(this)
        iv_home_leaders_btn.setOnClickListener {
            mPresenter.openLeaderboards()
        }
        iv_home_play_btn.setOnClickListener {
            mPresenter.play()
        }
    }

    override fun showGame() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, PlayFragment()).commit()
    }

    override fun showLeaders() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, RecordsFragment()).commit()
    }
}