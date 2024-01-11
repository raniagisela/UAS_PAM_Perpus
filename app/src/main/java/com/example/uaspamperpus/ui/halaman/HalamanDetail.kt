package com.example.uaspamperpus.ui.halaman

import com.example.uaspamperpus.R
import com.example.uaspamperpus.navigasi.DestinasiNavigasi

object  DetailDestination : DestinasiNavigasi {
    override val route= "item_details"
    override val titleRes= R.string.detail_sewa
    const val sewaIdArg = "itemId"
    val routeWithArgs = "$route/{$sewaIdArg}"
}