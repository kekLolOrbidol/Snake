package com.snake.eat.finish.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import com.snake.eat.finish.R
import com.snake.eat.finish.contract.IPlayContract
import com.snake.eat.finish.model.entity.Apple
import com.snake.eat.finish.model.entity.Path

class SnakesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint()
    private var mSnakePaths = ArrayList<Path>()
    private var countPaths = 5
    private var scores = 0
    private var mFood = Apple()
    private val mHandler = Handler()
    private val mSnakeRunnable = SnakeRunnable()
    private lateinit var gameView: IPlayContract.View

    companion object {
        const val RIGHT_DIRECTION = -1
        const val BOTTOM_DIRECTION = -2
        const val LEFT_DIRECTION = -3
        const val TOP_DIRECTION = -4
    }

    fun initSnake(mView: IPlayContract.View){
        gameView = mView
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mSnakePaths.isEmpty()) {
            for (i in 0 until countPaths) {
                mSnakePaths.add(Path(getStartX() + (countPaths - 1 - i) * getSize(), getStartY()))
                if (i == 0)
                    mSnakePaths.last().isStartPath = true
            }
        }

        if (mFood.x == -1)
            mFood = generateFood()

        for (i in 0 until mSnakePaths.size) {
            if (mSnakePaths[i].isStartPath)
                canvas!!.drawBitmap(
                    convertToBitmap(R.drawable.ic_start_path_snake),
                    mSnakePaths[i].x.toFloat(), mSnakePaths[i].y.toFloat(), mPaint
                )
            else
                canvas!!.drawBitmap(
                    convertToBitmap(R.drawable.ic_path_snake),
                    mSnakePaths[i].x.toFloat(), mSnakePaths[i].y.toFloat(), mPaint
                )
        }

        canvas!!.drawBitmap(
            convertToBitmap(R.drawable.ic_start_path_snake),
            mFood.x.toFloat(),
            mFood.y.toFloat(),
            mPaint
        )

        mHandler.postDelayed(mSnakeRunnable, 50L)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mHandler.removeCallbacks(mSnakeRunnable)
    }

    fun pause() {
        mHandler.removeCallbacks(mSnakeRunnable)
    }

    private fun moveSnake() {
        if (mSnakePaths[0].x == mFood.x && mSnakePaths[0].y == mFood.y) {
            scores += 50
            gameView.showScores(scores)
            addPathSnake()
            mFood = Apple()
        }

        var previousDirection = mSnakePaths[0].mDirection

        for (i in 0 until mSnakePaths.size) {
            when (mSnakePaths[i].mDirection) {
                RIGHT_DIRECTION -> {
                    mSnakePaths[i].x += getSize()
                    if (mSnakePaths[i].x + getSize() > width) {
                        mSnakePaths[i].x = getStartX()
                    }

                }
                LEFT_DIRECTION -> {
                    mSnakePaths[i].x -= getSize()
                    if (mSnakePaths[i].x < 0) {
                        mSnakePaths[i].x = width - getStartX() - getSize()
                    }

                }
                TOP_DIRECTION -> {
                    mSnakePaths[i].y -= getSize()
                    if (mSnakePaths[i].y < 0) {
                        mSnakePaths[i].y = height - getStartY() - getSize()
                        gameView.showResult(scores)
                    }

                }
                BOTTOM_DIRECTION -> {
                    mSnakePaths[i].y += getSize()
                    if (mSnakePaths[i].y + getSize() > height) {
                        mSnakePaths[i].y = getStartX()
                        gameView.showResult(scores)
                    }

                }
            }
            if (i > 0 && mSnakePaths[i].mDirection != previousDirection) {
                val directionSwap = previousDirection
                previousDirection = mSnakePaths[i].mDirection
                mSnakePaths[i].mDirection = directionSwap
            }
        }
        if (isSnake())
            gameView.showResult(scores)
        else
            invalidate()
    }

    private fun isSnake(): Boolean {
        for (i in 1 until mSnakePaths.size) {
            if (mSnakePaths[0].x == mSnakePaths[i].x && mSnakePaths[0].y == mSnakePaths[i].y)
                return true
        }
        return false
    }

    private fun addPathSnake() {
        val lastPath = mSnakePaths.last()
        when (lastPath.mDirection) {
            RIGHT_DIRECTION -> {
                val mPath = Path(lastPath.x - getSize(), lastPath.y, lastPath.mDirection, false)
                if (mPath.x < getStartX())
                    mPath.x = getStartX() + getCountByWidth() * getSize()
                mSnakePaths.add(mPath)
            }
            LEFT_DIRECTION -> {
                val mPath = Path(lastPath.x + getSize(), lastPath.y, lastPath.mDirection, false)
                if (mPath.x > width - getStartX())
                    mPath.x = getStartX()
                mSnakePaths.add(mPath)
            }
            BOTTOM_DIRECTION -> {
                val mPath = Path(lastPath.x, lastPath.y - getSize(), lastPath.mDirection, false)
                if (mPath.y < getStartY())
                    mPath.y = getStartY() + getCountByHeight() * getSize()
                mSnakePaths.add(mPath)
            }
            TOP_DIRECTION -> {
                val mPath = Path(lastPath.x, lastPath.y + getSize(), lastPath.mDirection, false)
                if (mPath.y > height - getStartY())
                    mPath.y = getStartY()
                mSnakePaths.add(mPath)
            }
        }
    }

    private fun generateFood(): Apple {
        val resFood = Apple(
            getStartX() + (Math.random() * getCountByWidth()).toInt() * getSize(),
            getStartY() + (Math.random() * getCountByHeight()).toInt() * getSize()
        )
        for (i in 0 until mSnakePaths.size) {
            if (mSnakePaths[i].x == resFood.x && mSnakePaths[i].y == resFood.y)
                return generateFood()
        }
        println("Food: x = ${resFood.x}, y = ${resFood.y}")
        return resFood
    }

    fun setDirection(direction: Int) {
        when (direction) {
            RIGHT_DIRECTION -> {
                if (mSnakePaths[0].mDirection != LEFT_DIRECTION)
                    mSnakePaths[0].mDirection = direction
            }
            BOTTOM_DIRECTION -> {
                if (mSnakePaths[0].mDirection != TOP_DIRECTION)
                    mSnakePaths[0].mDirection = direction
            }
            LEFT_DIRECTION -> {
                if (mSnakePaths[0].mDirection != RIGHT_DIRECTION)
                    mSnakePaths[0].mDirection = direction
            }
            TOP_DIRECTION -> {
                if (mSnakePaths[0].mDirection != BOTTOM_DIRECTION)
                    mSnakePaths[0].mDirection = direction
            }
        }
        mHandler.removeCallbacks(mSnakeRunnable)
        invalidate()
    }

    private fun getStartX(): Int {
        return (width - getCountByWidth() * getSize()) / 2
    }

    private fun getStartY(): Int {
        return (height - getCountByHeight() * getSize()) / 2
    }

    private fun getCountByWidth(): Int {
        return width / getSize()
    }

    private fun getCountByHeight(): Int {
        return height / getSize()
    }

    private fun getSize() = 28

    private fun convertToBitmap(image: Int): Bitmap {
        val resBitmap = Bitmap.createBitmap(getSize(), getSize(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resBitmap)
        val drawable = context.resources.getDrawable(image)
        drawable.setBounds(0, 0, getSize(), getSize())
        drawable.draw(canvas)
        return resBitmap
    }

    private inner class SnakeRunnable : Runnable {

        override fun run() {
            moveSnake()
        }
    }
}