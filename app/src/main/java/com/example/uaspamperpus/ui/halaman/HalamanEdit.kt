package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.model.EditViewModel
import com.example.uaspamperpus.model.PenyediaViewModel
import com.example.uaspamperpus.navigasi.DestinasiNavigasi
import com.example.uaspamperpus.navigasi.SewaTopAppBar
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi {
    override val route= "item_edit"
    override val titleRes= R.string.edit_sewa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SewaTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntrySewaBody(
            uiStateSewa = viewModel.sewaUiState,
            onSewaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
