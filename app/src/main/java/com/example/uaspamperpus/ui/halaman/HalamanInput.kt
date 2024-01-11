package com.example.uaspamperpus.ui.halaman

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uaspamperpus.R
import com.example.uaspamperpus.navigasi.DestinasiNavigasi

object DestinasiInput : DestinasiNavigasi{
    override val route = "Input"
    override val titleRes = R.string.input_data
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSewaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InputViewModel = viewModel(factory = PenyediaViewModel.Factory )
){}