package com.antoniokranjcina.imgur.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.repository.Repository
import com.antoniokranjcina.imgur.util.Constants.LOADING
import com.antoniokranjcina.imgur.util.Constants.NOT_LOADING
import com.antoniokranjcina.imgur.util.Constants.NO_INTERNET
import com.antoniokranjcina.imgur.util.Constants.UNEXPECTED_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val repository: Repository) : ViewModel() {

    val readPosts: MutableLiveData<List<Post>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val loading: MutableLiveData<String> = MutableLiveData()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(LOADING)
            try {
                val response = repository.getPosts()
                readPosts.postValue(response)
                loading.postValue(NOT_LOADING)
            } catch (e: IOException) {
                error.postValue(NO_INTERNET)
                loading.postValue(NOT_LOADING)
            } catch (e: Exception) {
                error.postValue(UNEXPECTED_ERROR)
                loading.postValue(NOT_LOADING)
            }
        }
    }
}