package com.example.tea_task.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.tea_task.data.model.competition.Competition
import com.example.tea_task.data.remote.Resource
import com.example.tea_task.data.repository.HomeRepo
import com.example.tea_task.util.BaseViewModel
import com.example.tea_task.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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
                it.copy(competitionDetails = intent.competition)
            }
        } else if (intent is HomeIntent.LoadOfflineContent) {
            displayCachedCompetitions()
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
                // Cache the data to display it if there's no internet connection.
                cacheData(competitionsResponse.data.competitions)

            } else if (competitionsResponse is Resource.Failure) {
                val cachedData = repo.getCompetitions().stateIn(viewModelScope).value
                _uiState.update {
                    it.copy(
                        isCachedDataExist = competitionsResponse.isNetworkError && cachedData.isNotEmpty(),
                        competitionsState = RequestState.ERROR,
                        isNetworkError = competitionsResponse.isNetworkError
                    )
                }
            }
        }
    }

    fun cacheData(competitions: List<Competition>) {
        viewModelScope.launch {
            if (competitions.isNotEmpty()) {
                repo.saveCompetitions(competitions)
            }
        }
    }


    fun displayCachedCompetitions() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    cachedCompetitions = repo.getCompetitions().stateIn(
                        viewModelScope
                    ),
                    competitionsState = RequestState.SUCCESS
                )
            }

//            // Test cached data are exist or not
//            val data = uiState.value.cachedCompetitions?.value?.size
//            Log.d(TAG, "displayCachedCompetitions: $data")
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