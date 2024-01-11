package com.example.uaspamperpus.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uaspamperpus.repositori.RepositoriSewa

class InputViewModel(private val repositoriSewa: RepositoriSewa): ViewModel() {
    var uiStateSewa by mutableStateOf(UIStateSewa())
        private set

    private fun validasiInput (uiState:DetailSewa = uiStateSewa.detailSewa):Boolean{
        return  with(uiState){
            nama.isNotBlank()&&alamat.isNotBlank()&&telpon.isNotBlank()&&judul_buku.isNotBlank()&&lama_meminjam.isNotBlank()&&tanggal_kembali.isNotBlank()
        }
    }
    fun updateUiState(detailSewa: DetailSewa){
        uiStateSewa =
            UIStateSewa(detailSewa = detailSewa, isEntryValid = validasiInput(detailSewa))
    }

    suspend fun saveSewa(){
        if (validasiInput()){
            repositoriSewa.insertSewa(uiStateSewa.detailSewa.toSewa())
        }
    }
}