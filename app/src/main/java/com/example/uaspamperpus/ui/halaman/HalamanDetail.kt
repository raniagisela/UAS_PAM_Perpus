package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.model.DetailsViewModel
import com.example.uaspamperpus.model.PenyediaViewModel
import com.example.uaspamperpus.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object  DetailDestination : DestinasiNavigasi {
    override val route= "item_details"
    override val titleRes= R.string.detail_sewa
    const val sewaIdArg = "itemId"
    val routeWithArgs = "$route/{$sewaIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navigateToEditItem : (Int) -> Unit,
    navigasiBack : ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            SewaTopAppBar(title = stringResource(DetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigasiBack
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = {navigateToEditItem(uiState.value.detailSewa.id)},
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_sewa)
                )
            }
        }, modifier = modifier
    ){innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigasiBack()
                }
            },
            modifier= Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}