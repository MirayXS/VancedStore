package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.domain.manager.BrowseLayoutMode
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.ui.screen.BrowseState
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val browseRepository: BrowseRepository,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {

    var state by mutableStateOf<BrowseState>(BrowseState.Loading)
        private set

    fun loadApps() {
        viewModelScope.launch {
            try {
                state = BrowseState.Loading
                val pinned = browseRepository.getPinnedApps()
                val repo = browseRepository.getApps()
                state = BrowseState.Success(
                    pinnedApps = pinned,
                    repoApps = repo
                )
            } catch (e: Exception) {
                state = BrowseState.Error
                e.printStackTrace()
            }
        }
    }

    fun switchLayoutMode(newMode: BrowseLayoutMode) {
        preferenceManager.browseLayoutMode = newMode
    }

    init {
        loadApps()
    }
}