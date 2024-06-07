package com.gilbertohdz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gilbertohdz.data.local.entity.ShowEntity

@Database(
    entities = [ShowEntity::class],
    version = 1
)
abstract class TvMazeDatabase : RoomDatabase() {

    abstract val dao: TvMazeDao
}