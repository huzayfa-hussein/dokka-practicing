package mobi.foo.dokkapracticing.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mobi.foo.dokkapracticing.domain.models.main.MainIntent
import mobi.foo.dokkapracticing.domain.models.main.MainState
import mobi.foo.dokkapracticing.domain.usecases.GetLaunchesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetLaunchesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState(isLoading = false))
    val uiState = _uiState.asStateFlow()

    init {
        fetchAllLaunches()
    }

    fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.RefreshLaunches -> {
                fetchAllLaunches()
            }

            else -> Unit
        }
    }

    private fun fetchAllLaunches() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
        }
        useCase.invoke().onEach { launchList ->

            _uiState.update {
                it.copy(
                    isLoading = false,
                    launches = launchList
                )
            }
        }.launchIn(viewModelScope)
    }
}