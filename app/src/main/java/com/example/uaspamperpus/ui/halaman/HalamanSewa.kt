package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.data.Sewa
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
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SewaTopAppBar(
                title = stringResource(DestinasiSewa.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.input)
                )
            }
        },
    ) {
            innerPadding ->
        val uiStateSewa by viewModel.sewaUiState.collectAsState()
        BodySewa(
            itemSewa = uiStateSewa.listSewa,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            onsewaClick = onDetailClick
        )
    }
}

@Composable
fun BodySewa(
    itemSewa : List<Sewa>,
    modifier: Modifier = Modifier,
    onsewaClick: (Int)->Unit = {}
){