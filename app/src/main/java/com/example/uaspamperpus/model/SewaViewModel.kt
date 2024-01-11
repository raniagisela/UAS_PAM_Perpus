package com.example.uaspamperpus.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspamperpus.data.Sewa
import com.example.uaspamperpus.repositori.RepositoriSewa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SewaViewModel(private val repositoriSewa: RepositoriSewa): ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val sewaUiState : StateFlow<SewaUiState> = repositoriSewa.getAllSewaStream().filterNotNull()
        .map { SewaUiState(listSewa = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = SewaUiState()
        )

    data class SewaUiState(
        val listSewa: List<Sewa> = listOf()
    )
}