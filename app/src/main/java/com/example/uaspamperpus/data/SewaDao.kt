package com.example.uaspamperpus.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface SewaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa: Sewa)

    @Update
    suspend fun update(siswa: Sewa)

    @Delete
    suspend fun delete(siswa: Sewa)

    @Query("SELECT * from tblSewa WHERE id = :id")
    fun getSewa(id: Int): Flow<Sewa>

    @Query("SELECT * from tblSewa ORDER BY nama ASC")
    fun getAllSewa(): Flow<List<Sewa>>
}
