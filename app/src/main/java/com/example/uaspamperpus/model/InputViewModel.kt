package com.example.uaspamperpus.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uaspamperpus.data.Sewa
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

data class UIStateSewa(
    val detailSewa : DetailSewa = DetailSewa(),
    val isEntryValid : Boolean = false
)

data class DetailSewa (
    val id : Int = 0,
    val nama : String = "",
    val alamat : String = "",
    val telpon : String = "",
    val judul_buku: String = "",
    val lama_meminjam: String = "",
    val tanggal_kembali: String = ""
)

fun DetailSewa.toSewa():Sewa = Sewa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon,
    judul_buku = judul_buku,
    lama_meminjam = lama_meminjam,
    tanggal_kembali = tanggal_kembali

)
fun Sewa.toUiStateSewa(isEntryValid: Boolean = false):UIStateSewa =UIStateSewa(
    detailSewa = this.toDetailSewa(),
    isEntryValid = isEntryValid
)

fun Sewa.toDetailSewa():DetailSewa = DetailSewa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon,
    judul_buku = judul_buku,
    lama_meminjam = lama_meminjam,
    tanggal_kembali = tanggal_kembali
)