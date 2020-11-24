package com.anushka.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_table_name")
class Subscriber (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="subscriber_id" )
    val id: Int,

    @ColumnInfo(name ="subscriber_name")
    val  name: String,

    @ColumnInfo(name ="subscriber_email")
    val  email : String
)