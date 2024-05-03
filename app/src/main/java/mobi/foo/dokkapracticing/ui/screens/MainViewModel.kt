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

/**
 * Main view model for managing UI state and business logic related to displaying launches.
 *
 * @property useCase
 * @constructor Create empty Main view model
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetLaunchesUseCase
) : ViewModel() {

    // Mutable state flow for UI state
    private val _uiState = MutableStateFlow(MainState(isLoading = false))
    val uiState = _uiState.asStateFlow()

    init {
        // Fetches all launches when ViewModel is initialized
        fetchAllLaunches()
    }

    /**
     * On intent
     * Handles incoming intents from the UI.
     *
     * @param intent The intent to be handled.
     */
    fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.RefreshLaunches -> {
                // Fetches all launches when a refresh intent is received
                fetchAllLaunches()
            }

            else -> Unit
        }
    }

    /**
     * Fetches all launches from the [useCase].
     */
    private fun fetchAllLaunches() {
        // Launches a coroutine within the viewModelScope
        viewModelScope.launch {
            // Updates UI state to indicate loading state
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
        }

        // Invokes the use case to get launch data and updates UI state accordingly
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