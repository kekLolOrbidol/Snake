package com.snake.eat.finish.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.snake.eat.finish.model.entity.Result

@Dao
interface IResultDao {

    @Insert
    fun insert(result: Result)

    @Query("SELECT * FROM Result ORDER BY scores DESC")
    fun getResults(): List<Result>
}