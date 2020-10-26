package com.snake.eat.finish.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IRecordsContract
import com.snake.eat.finish.model.RecordsModel
import kotlinx.android.synthetic.main.item_leader.view.*

class RecordsPresenter(private val view: IRecordsContract.View) : IRecordsContract.Presenter {

    private val model = RecordsModel()

    override fun goBack() {
        view.showHome()
    }

    override fun initLeadersRv(recyclerView: RecyclerView, context: Context) {
        val mLeaders = model.getLeaders()
        if (mLeaders.isEmpty())
            view.showEmptyMessage()
        else {
            recyclerView.layoutManager =
                LinearLayoutManager(context)
            recyclerView.adapter = object : RecyclerView.Adapter<LeaderHolder>() {
                override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LeaderHolder {
                    return LeaderHolder(LayoutInflater.from(context).inflate(R.layout.item_leader, p0, false))
                }

                override fun getItemCount(): Int {
                    return mLeaders.size
                }

                override fun onBindViewHolder(p0: LeaderHolder, p1: Int) {
                    p0.setNumber(p1 + 1)
                    p0.setName(mLeaders[p1].name)
                    p0.setScores(mLeaders[p1].scores)
                }
            }
        }
    }

    private inner class LeaderHolder(iteview: View) : RecyclerView.ViewHolder(iteview) {
        private val imageNumber = iteview.iv_leader_number_image
        private val textNumber = iteview.tv_leader_number_text
        private val name = iteview.tv_leader_name
        private val scores = iteview.tv_leader_scores

        fun setNumber(number: Int) {
            when (number) {
                1 -> {
                    textNumber.visibility = View.GONE
                    imageNumber.setImageResource(R.drawable.group_92)
                }
                2 -> {
                    textNumber.visibility = View.GONE
                    imageNumber.setImageResource(R.drawable.group_93)
                }
                3 -> {
                    textNumber.visibility = View.GONE
                    imageNumber.setImageResource(R.drawable.group_94)
                }
                else -> {
                    imageNumber.visibility = View.GONE
                    textNumber.text = number.toString()

                }
            }
        }

        fun setName(name: String) {
            this.name.text = name
        }

        fun setScores(scores: Int) {
            this.scores.text = scores.toString()
        }
    }
}
