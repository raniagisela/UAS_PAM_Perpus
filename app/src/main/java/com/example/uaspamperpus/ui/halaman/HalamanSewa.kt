package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if(itemSewa.isEmpty()){
            Text(
                text = stringResource(id = R.string.desc),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        }else{
            ListSewa(
                itemSewa = itemSewa,
                modifier = Modifier.padding(horizontal = 8.dp),
                onItemClick = {onsewaClick(it.id)}
            )
        }
    }
}

@Composable
fun ListSewa(
    itemSewa : List<Sewa>,
    modifier: Modifier = Modifier,
    onItemClick:(Sewa) ->Unit
){
    LazyColumn(modifier = Modifier){
        items(items = itemSewa, key = {it.id}){
                rent ->
            DataSewa(
                sewa = rent,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(rent) }
            )
        }
    }
}

@Composable
fun DataSewa(
    sewa: Sewa,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = sewa.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = sewa.telpon,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Text(
                text = "Alamat : "+sewa.alamat,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "Judul Buku : "+sewa.judul_buku,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
