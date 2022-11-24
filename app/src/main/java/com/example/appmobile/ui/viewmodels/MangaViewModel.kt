package com.example.appmobile.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appmobile.MobileApplication
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
            val randomManga=mangaRepository.GetRandomManga()
            _uiState.emit(MangaState(randomManga.name,randomManga.tome))
        }catch (e:Exception){

            }
        }

    }

}