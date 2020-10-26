package com.snake.eat.finish.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(@PrimaryKey(autoGenerate = true) var id: Int?, val name: String, val scores: Int) {
}