package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspamperpus.R
import com.example.uaspamperpus.model.DetailSewa
import com.example.uaspamperpus.model.InputViewModel
import com.example.uaspamperpus.model.PenyediaViewModel
import com.example.uaspamperpus.model.UIStateSewa
import com.example.uaspamperpus.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

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
    ) {
            innerPadding ->
        EntrySewaBody(
            uiStateSewa = viewModel.uiStateSewa,
            onSewaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveSewa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntrySewaBody(
    uiStateSewa: UIStateSewa,
    onSewaValueChange: (DetailSewa) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(16.dp)
    ){
        FormInputSewa(
            detailSewa = uiStateSewa.detailSewa,
            onValueChange = onSewaValueChange,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateSewa.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputSewa(
    detailSewa: DetailSewa,
    modifier: Modifier,
    onValueChange : (DetailSewa) ->Unit = {},
    enabled:Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value = detailSewa.nama,
            onValueChange ={onValueChange(detailSewa.copy(nama = it))},
            label = { Text(stringResource(id = R.string.nama)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailSewa.alamat,
            onValueChange ={onValueChange(detailSewa.copy(alamat = it))},
            label = { Text(stringResource(R.string.alamat)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailSewa.telpon,
            onValueChange ={onValueChange(detailSewa.copy(telpon = it))},
            label = { Text(stringResource(R.string.telpon)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailSewa.judul_buku,
            onValueChange ={onValueChange(detailSewa.copy(judul_buku = it))},
            label = { Text(stringResource(R.string.jdl)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailSewa.lama_meminjam,
            onValueChange ={onValueChange(detailSewa.copy(lama_meminjam = it))},
            label = { Text(stringResource(R.string.lama)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailSewa.tanggal_kembali,
            onValueChange ={onValueChange(detailSewa.copy(tanggal_kembali = it))},
            label = { Text(stringResource(R.string.kembali)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        if (enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(bottom = 16.dp)

        )
    }
}