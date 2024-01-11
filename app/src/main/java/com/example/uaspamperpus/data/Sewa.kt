package com.example.uaspamperpus.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSewa")
data class Sewa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val alamat: String,
    val telpon: String,
    val judul_buku: String,
    val lama_meminjam: String,
    val tanggal_kembali: String
)
