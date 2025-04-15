package com.example.tea_task.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.tea_task.data.remote.Resource
import com.example.tea_task.data.repository.HomeRepo
import com.example.tea_task.util.BaseViewModel
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
) : BaseViewModel<HomeIntent>() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    override fun onIntent(intent: HomeIntent) {
        if (intent is HomeIntent.RetryApi) {
            displayCompetitions()
        } else if (intent is HomeIntent.OnCompetitionClicked) {
            // Save a competition when clicked to pass it to details screen via shared view model
            _uiState.update {
                it.copy(savedCompetition = intent.competition)
            }
        }
    }

    init {
        displayCompetitions()
    }


    private fun displayCompetitions() {
        viewModelScope.launch {
            enableLoadingState()

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

    fun enableLoadingState() {
        _uiState.update {
            it.copy(
                competitionsState = RequestState.LOADING
            )
        }
    }
}