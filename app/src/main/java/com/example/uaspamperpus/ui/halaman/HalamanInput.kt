package com.example.uaspamperpus.ui.halaman

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.model.InputViewModel
import com.example.uaspamperpus.model.PenyediaViewModel
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
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SewaTopAppBar(
                title = stringResource(DestinasiInput.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) {}
}