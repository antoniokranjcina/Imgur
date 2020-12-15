package com.antoniokranjcina.imgur.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniokranjcina.imgur.data.local.entities.PostEntity
import com.antoniokranjcina.imgur.repository.Repository
import com.antoniokranjcina.imgur.util.Constants.NO_INTERNET
import com.antoniokranjcina.imgur.util.Constants.UNEXPECTED_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val repository: Repository) : ViewModel() {

    val readPosts: LiveData<List<PostEntity>> = repository.posts
    val error: MutableLiveData<String> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            try {
                repository.getPosts()
                loading.postValue(false)
            } catch (e: IOException) {
                Log.d(TAG, "getPosts: ${e.localizedMessage}")
                error.postValue(NO_INTERNET)
                loading.postValue(false)
            } catch (e: Exception) {
                Log.d(TAG, "getPosts: ${e.localizedMessage}")
                error.postValue(UNEXPECTED_ERROR)
                loading.postValue(false)
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}