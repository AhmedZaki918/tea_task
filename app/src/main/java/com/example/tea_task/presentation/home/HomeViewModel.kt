package com.example.tea_task.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tea_task.data.remote.Resource
import com.example.tea_task.data.repository.HomeRepo
import com.example.tea_task.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: HomeRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        displayCompetitions()
    }


    private fun displayCompetitions() {
        viewModelScope.launch {

            val competitionsResponse = repo.competitions()
            if (competitionsResponse is Resource.Success) {
                _uiState.update {
                    it.copy(
                        competitionsData = competitionsResponse.data,
                        competitionsState = RequestState.SUCCESS
                    )
                }

            } else if (competitionsResponse is Resource.Failure) {
                _uiState.update {
                    it.copy(
                        isNetworkError = competitionsResponse.isNetworkError,
                        competitionsState = RequestState.ERROR
                    )
                }
            }
        }
    }
}