package com.example.kotlin_coroutines_demo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "avatar")
    val avatar: String?
)
