package com.example.uaspamperpus.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspamperpus.R
import com.example.uaspamperpus.ui.halaman.DestinasiHome
import com.example.uaspamperpus.ui.halaman.DestinasiInput
import com.example.uaspamperpus.ui.halaman.DestinasiSewa
import com.example.uaspamperpus.ui.halaman.DetailDestination
import com.example.uaspamperpus.ui.halaman.DetailsScreen
import com.example.uaspamperpus.ui.halaman.HomeScreen
import com.example.uaspamperpus.ui.halaman.InputSewaScreen
import com.example.uaspamperpus.ui.halaman.ItemEditDestination
import com.example.uaspamperpus.ui.halaman.ItemEditScreen
import com.example.uaspamperpus.ui.halaman.SewaScreen

@Composable
fun PerpusApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SewaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(navigateToRent = { navController.navigate(DestinasiSewa.route) })

        }
        composable(DestinasiSewa.route) {
            SewaScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInput.route) },
                navigateBack = { navController.popBackStack() },
                onDetailClick = {
                    navController.navigate("${DetailDestination.route}/$it")
                })
        }
        composable(DestinasiInput.route) {
            InputSewaScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.sewaIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigasiBack = { navController.popBackStack() }
            )
        }
        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
