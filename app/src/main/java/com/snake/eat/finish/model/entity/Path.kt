package com.snake.eat.finish.model.entity

import com.snake.eat.finish.view.SnakesView

data class Path(var x: Int, var y: Int, var mDirection: Int, var isStartPath: Boolean ) {
    constructor(x: Int, y: Int): this(x, y, SnakesView.RIGHT_DIRECTION, false)
}