package com.example.appconvidados2.UI.allGuests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Olá Mundo"
    }
    val text: LiveData<String> = _text
}