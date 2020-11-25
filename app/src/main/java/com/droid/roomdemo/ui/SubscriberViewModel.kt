package com.droid.roomdemo.ui

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droid.roomdemo.db.Subscriber
import com.droid.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.launch


class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(),Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber


    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
      get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
        } else if (Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Please enter correct email address")
        } else {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(Subscriber(0, name, email))
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            clearAll()
        }

    }

    fun insert(subscriber: Subscriber)= viewModelScope.launch {
        val newRowId: Long = repository.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        } else
            statusMessage.value = Event("Error occured")
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        val noOfRows = repository.update(subscriber)
        if (noOfRows > 0) {
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event(" $noOfRows Row Updated Successfully")
        } else
            statusMessage.value = Event("Error occurred")

    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        val noOfRows = repository.delete(subscriber)
        if (noOfRows > 0) {
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event(" $noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
        statusMessage.value = Event("All Subscribers Deleted Successfully")
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
         inputName.value = subscriber.name
         inputEmail.value = subscriber.email
         isUpdateOrDelete = true
         subscriberToUpdateOrDelete = subscriber
         saveOrUpdateButtonText.value = "Update"
         clearAllOrDeleteButtonText.value = "Delete"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}