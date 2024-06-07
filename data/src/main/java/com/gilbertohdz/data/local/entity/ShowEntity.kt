package com.gilbertohdz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey
    val id: Int? = null,
    val showId: Int,
    val name: String
)