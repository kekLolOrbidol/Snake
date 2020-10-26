package com.snake.eat.finish.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IPlayContract
import com.snake.eat.finish.features.OnSwipeTouchListener
import com.snake.eat.finish.presenter.PlayPresenter
import kotlinx.android.synthetic.main.fragment_game.*

class PlayFragment : Fragment(), IPlayContract.View {

    private lateinit var presenter: IPlayContract.Presenter
    private var isRunningGame = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sv_game_main.initSnake(this)

        presenter = PlayPresenter(this)
        presenter.frOnCreated()
        root_view_game.setOnTouchListener(object : OnSwipeTouchListener(activity as MainActivity){
            override fun onSwipeTop() {
                presenter.top()
            }

            override fun onSwipeRight() {
                presenter.right()
            }

            override fun onSwipeLeft() {
                presenter.left()
            }

            override fun onSwipeBottom(){
                presenter.bottom()
            }
        })

        iv_game_bottom.setOnClickListener {
            presenter.bottom()
        }
        iv_game_left.setOnClickListener {
            presenter.left()
        }
        iv_game_top.setOnClickListener {
            presenter.top()
        }
        iv_game_right.setOnClickListener {
            presenter.right()
        }

        tv_game_stop_btn.setOnClickListener {
            if (isRunningGame) {
                tv_game_stop_btn.text = "start"
                sv_game_main.pause()
            } else {
                tv_game_stop_btn.text = "pause"
                sv_game_main.invalidate()
            }
            isRunningGame = !isRunningGame
        }
    }

    override fun showScores(scores: Int) {
        tv_game_result.text = "Your scores: $scores"
    }

    override fun showResult(scores: Int) {
        sv_game_main.pause()
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, ResultFragment.newInstance(scores)).commit()
    }

    override fun showMessageExit() {

    }

    override fun pauseGame() {
        sv_game_main.pause()
    }

    override fun goRight() {
        sv_game_main.setDirection(SnakesView.RIGHT_DIRECTION)
    }

    override fun goLeft() {
        sv_game_main.setDirection(SnakesView.LEFT_DIRECTION)
    }

    override fun goTop() {
        sv_game_main.setDirection(SnakesView.TOP_DIRECTION)
    }

    override fun goBottom() {
        sv_game_main.setDirection(SnakesView.BOTTOM_DIRECTION)
    }
}