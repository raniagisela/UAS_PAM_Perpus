package com.example.uaspamperpus

import com.example.uaspamperpus.data.Sewa
import kotlinx.coroutines.flow.Flow

interface RepositoriSewa {
    fun getAllSewaStream(): Flow<List<Sewa>>

    fun getSewaStream(id: Int): Flow<Sewa?>
}