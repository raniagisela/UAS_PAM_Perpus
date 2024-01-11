package com.example.uaspamperpus.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspamperpus.repositori.RepositoriSewa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSewa: RepositoriSewa
): ViewModel() {
    var sewaUiState by mutableStateOf(UIStateSewa())
        private set

    private val itemId : Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            sewaUiState = repositoriSewa.getSewaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSewa(true)
        }
    }
    suspend fun updateSiswa(){
        if (validasiInput(sewaUiState.detailSewa)){
            repositoriSewa.updateSewa(sewaUiState.detailSewa.toSewa())
        }
        else{
            println("Data tidak valid")
        }
    }
    fun updateUiState (detailSewa : DetailSewa){
        sewaUiState =
            UIStateSewa(detailSewa = detailSewa, isEntryValid = validasiInput(detailSewa))
    }
    private fun validasiInput(uiState: DetailSewa = sewaUiState.detailSewa):Boolean{
        return with(uiState){
            nama.isNotBlank()&&alamat.isNotBlank()&&telpon.isNotBlank()&&judul_buku.isNotBlank()&&lama_meminjam.isNotBlank()&&tanggal_kembali.isNotBlank()
        }
    }
}

