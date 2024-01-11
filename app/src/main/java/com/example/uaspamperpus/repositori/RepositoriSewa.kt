package com.example.uaspamperpus.repositori

import com.example.uaspamperpus.data.Sewa
import kotlinx.coroutines.flow.Flow

interface RepositoriSewa {
    fun getAllSewaStream(): Flow<List<Sewa>>

    fun getSewaStream(id: Int): Flow<Sewa?>

    suspend fun insertSewa(sewa: Sewa)
    suspend fun deleteSewa(sewa: Sewa)
    suspend fun updateSewa(sewa: Sewa)
}