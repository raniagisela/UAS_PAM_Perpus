package com.example.uaspamperpus.repositori

import com.example.uaspamperpus.data.Sewa
import com.example.uaspamperpus.data.SewaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriSewa(private val sewaDao: SewaDao): RepositoriSewa {
    override fun getAllSewaStream(): Flow<List<Sewa>> =sewaDao.getAllSewa()

    override fun getSewaStream(id: Int): Flow<Sewa?> =sewaDao.getSewa(id)

}