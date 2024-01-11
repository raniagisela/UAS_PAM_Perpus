package com.example.uaspamperpus.ui.halaman

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.model.PenyediaViewModel
import com.example.uaspamperpus.model.SewaViewModel
import com.example.uaspamperpus.navigasi.DestinasiNavigasi

object DestinasiSewa: DestinasiNavigasi {
    override val route = "sewa"
    override val titleRes = R.string.sewa
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SewaScreen(
    navigateToItemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int)->Unit={},
    viewModel: SewaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){}