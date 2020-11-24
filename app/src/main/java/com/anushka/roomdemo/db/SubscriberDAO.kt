package com.anushka.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface SubscriberDAO {
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun  deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_table_name")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_table_name")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}