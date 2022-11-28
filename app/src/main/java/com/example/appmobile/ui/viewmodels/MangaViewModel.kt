package com.example.appmobile.ui.viewmodels


import android.util.Log

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope

import com.example.appmobile.data.repositories.MangaRepository
import com.example.appmobile.data.states.MangaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(private val mangaRepository:MangaRepository) : ViewModel() {
    private val _uiState= MutableStateFlow(MangaState())
    val uiState : StateFlow<MangaState> = _uiState.asStateFlow()

    private fun getManga(){
        viewModelScope.launch {
        try {

            val randomManga = mangaRepository.GetRandomManga()
            _uiState.emit(MangaState(randomManga.name,randomManga.tome))
        }catch (e:Exception){
            e.message?.let { Log.e("MangaViewModel", it) }
            }
        }

    }

}