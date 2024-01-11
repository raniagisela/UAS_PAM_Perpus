package com.example.uaspamperpus.ui.halaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspamperpus.R
import com.example.uaspamperpus.navigasi.DestinasiNavigasi
import com.example.uaspamperpus.navigasi.SewaTopAppBar

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToRent: () -> Unit,
    modifier: Modifier = Modifier,
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SewaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToRent,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.note),
                    contentDescription = stringResource(id = R.string.sewa)
                )
            }
        },
    ) {
            innerPadding ->
        BodyHome(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        CardElevation(judul = "Fur Immer Dein Ian – Valerie", rating = "4.5", desc = "Novel Romansa", gambar = R.drawable.buku1)
        CardElevation(judul = "Majnun – Anton Kurnia", rating = "4.0", desc = "Majnun adalah kisah tentang cinta dan persahabatan", gambar = R.drawable.buku2)
        CardElevation(judul = "Where Stories Begin – Wacaku", rating = "4.3", desc = " antologi cerpen hasil kurasi Redaksi Novel Elex Media dari perlombaan yang diadakan oleh Wacaku", gambar = R.drawable.buku3)
        CardElevation(judul = "Ramai Yang Dulu Kita Bawa Pergi – Suci Berliana", rating = "4.3", desc = "Cerita ini hanya kilas balik pertemuan kita. Cerita yang hanya mampu kujabarkan lewat rentetan kata.", gambar = R.drawable.buku4)
        CardElevation(judul = "Before the Coffee Gets Cold – Toshikazu Kawaguchi", rating = "4.5", desc = "Di sebuah gang kecil di Tokyo, ada kafe tua yang bisa membawa pengunjungnya menjelajahi waktu.", gambar = R.drawable.buku5)
        CardElevation(judul = "Terpikat – Ghefira Zakhira", rating = "4.7", desc = "Kisah ini dimulai sejak Aruna yang tidak sengaja melihat Abian dan seketika ia langsung jatuh cinta pada pandangan pertama kepada laki-laki itu.", gambar = R.drawable.buku6)
    }
}

@Composable
fun CardElevation(judul: String, rating: String, desc:String, gambar:Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFF126299),
        modifier = Modifier
            .height(300.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFF070808)
                ) {
                    Text(
                        text = "New release",
                        fontSize =  12.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = judul,
                    fontSize =  24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))


                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = rating,
                        fontSize =  14.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = desc,
                    fontSize =  11.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(width = 100.dp, height = 140.dp)
            ) {
                Image(
                    painter = painterResource(id = gambar),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}
