package com.example.uaspamperpus.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspamperpus.repositori.RepositoriSewa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSewa: RepositoriSewa
) : ViewModel(){
    private val sewaId: Int = checkNotNull(savedStateHandle[DetailDestination.sewaIdArg])
    val uiState : StateFlow<ItemDetailsUiState> =
        repositoriSewa.getSewaStream(sewaId).filterNotNull().map{
            ItemDetailsUiState(detailSewa = it.toDetailSewa())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    suspend fun deleteItem(){
        repositoriSewa.deleteSewa(uiState.value.detailSewa.toSewa())
    }
}