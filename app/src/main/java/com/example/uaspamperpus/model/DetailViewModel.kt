package com.example.uaspamperpus.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspamperpus.repositori.RepositoriSewa
import com.example.uaspamperpus.ui.halaman.DetailDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock : Boolean = true,
    val detailSewa: DetailSewa = DetailSewa(),
)