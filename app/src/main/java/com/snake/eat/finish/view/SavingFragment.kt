package com.snake.eat.finish.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.ISavingContract
import com.snake.eat.finish.presenter.SavingPresenter
import kotlinx.android.synthetic.main.fragment_save.*

class SavingFragment: Fragment(), ISavingContract.View {

    private lateinit var mPresenter: ISavingContract.Presenter

    companion object {
        fun newInstance(scores: Int): SavingFragment{
            val mBundle = Bundle()
            mBundle.putInt("scores_count", scores)
            val mFragment = SavingFragment()
            mFragment.arguments = mBundle
            return mFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = SavingPresenter(this)
        mPresenter.frOnCreated()

        iv_save_btn.setOnClickListener {
            mPresenter.saveResult()
        }
    }

    override fun getScores(): Int {
        return arguments!!.getInt("scores_count")
    }

    override fun getName(): String {
        return et_save_name.text.toString()
    }

    override fun showLeaders() {
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_main_frames, RecordsFragment()).commit()
    }

    override fun showScores(scores: Int) {
        tv_save_scores.text = scores.toString()
    }

    override fun showInputError() {
        Toast.makeText(context, "Введите имя", Toast.LENGTH_LONG).show()
    }
}