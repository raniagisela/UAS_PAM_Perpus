package com.example.uaspamperpus.repositori

import android.content.Context

interface ContainerApp{
    val repositoriSewa : RepositoriSewa
}
class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriSewa: RepositoriSewa by lazy {
        OfflineRepositoriSewa(DatabaseSewa.getDatabase(context).sewaDao())
    }
}