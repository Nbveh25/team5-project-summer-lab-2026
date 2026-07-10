package ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.DrawerMenuContent
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.DrawerMenuItem
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.data.datasource.LocalRacerDataSource
import ru.kpfu.itis.summerlab.team5.racersapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.bigtiz.ui.screen.purchase_success.ViewModel.PurchaseSuccessViewModel
import com.example.bigtiz.ui.screen.purchase_success.screen.PurchaseSuccessScreen
import com.example.bigtiz.ui.screen.schedule_of_races.data.ScheduleOfRacesRepositoryImpl
import com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase.GetRacesUseCase
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.presentation.PilotDetailsScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.route.RaceInfoRoute
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races.ScheduleOfRacesScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races.presentation.viewmodel.ScheduleOfRacesViewModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.data.FileTicketRepositoryFactory
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.TicketRepositoryFactory
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.PurchaseTicketsUseCase
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation.TicketViewModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.route.TicketSelectionRoute

@Composable
fun AppRoot(navHostController: NavHostController) {

    val context = LocalContext.current
    val purchaseSuccessViewModel = remember { PurchaseSuccessViewModel() }
    val scheduleRepository = remember { ScheduleOfRacesRepositoryImpl() }
    val getRacesUseCase = remember { GetRacesUseCase(scheduleRepository) }
    val scheduleViewModel = remember { ScheduleOfRacesViewModel(getRacesUseCase) }

    val ticketRepositoryFactory: TicketRepositoryFactory = remember {
        FileTicketRepositoryFactory(context)
    }

    val drawerMenuItems = remember {
        val racers = LocalRacerDataSource().getAllRacers()
        buildList {
            add(DrawerMenuItem("home", "Главная", R.drawable.navigation_menu_home))
            racers.forEach { racer ->
                add(DrawerMenuItem(racer.id.toString(), racer.name, racer.imageResId))
            }
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var currentScreen by remember { mutableStateOf("home") }
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = { scope.launch { drawerState.open() } }

    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch { drawerState.close() }
    }

    BackHandler(enabled = !drawerState.isOpen && currentScreen == "home") {
        showExitDialog = true
    }

    if (showExitDialog) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                surface = Color(0xFF1C1B1F),
                onSurface = Color(0xFFE6E1E5),
                onSurfaceVariant = Color(0xFFCAC4D0),
                primary = Color(0xFFD0BCFF),
                surfaceContainerHighest = Color(0xFF2B2930)
            )
        ) {
            AlertDialog(
                onDismissRequest = { showExitDialog = false },
                title = { Text("Выход", color = Color(0xFFE6E1E5)) },
                text = { Text("Вы уверены, что хотите выйти из приложения?", color = Color(0xFFCAC4D0)) },
                confirmButton = {
                    TextButton(onClick = { (context as? android.app.Activity)?.finish() }) {
                        Text("Да", color = Color(0xFFEFB8C8))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showExitDialog = false }) {
                        Text("Нет", color = Color(0xFFD0BCFF))
                    }
                },
                containerColor = Color(0xFF1C1B1F)
            )
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerMenuContent(
                items = drawerMenuItems,
                onItemClick = { item ->
                    when (item.key) {
                        "home" -> {
                            if (currentScreen != "home") {
                                navHostController.navigate(NavigationScreen.RaceInfo) {
                                    popUpTo(NavigationScreen.RaceInfo) { inclusive = true }
                                }
                            }
                            scope.launch { drawerState.close() }
                        }
                        else -> {
                            scope.launch { drawerState.close() }
                            navHostController.navigate(NavigationScreen.PilotDetails(item.key)) {
                                popUpTo(NavigationScreen.RaceInfo) { inclusive = false }
                            }
                        }
                    }
                }
            )
        }
    ) {

    NavHost(
        navController = navHostController,
        startDestination = NavigationScreen.RaceInfo,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable<NavigationScreen.RaceInfo> {
            LaunchedEffect(Unit) { currentScreen = "home" }
            RaceInfoRoute(
                onMenuClick = openDrawer,
                onBuyTicketClick = {
                    navHostController.navigate(NavigationScreen.ScheduleOfRacesViewModel)
                }
            )
        }

        composable<NavigationScreen.TicketSelection> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationScreen.TicketSelection>()

            TicketSelectionRoute(
                route = route,
                ticketRepositoryFactory = ticketRepositoryFactory,
                purchaseSuccessViewModel = purchaseSuccessViewModel,
                onMenuClick = openDrawer,
                onPurchaseSuccess = {
                    navHostController.navigate(NavigationScreen.PurchaseSuccess)
                }
            )
        }

        composable<NavigationScreen.PurchaseSuccess> {
            PurchaseSuccessScreen(
                viewModel = purchaseSuccessViewModel,
                onMenuClick = openDrawer,
                onBackClick = {
                    navHostController.popBackStack(NavigationScreen.ScheduleOfRacesViewModel, inclusive = false)
                }
            )
        }

        composable<NavigationScreen.ScheduleOfRacesViewModel> {
            LaunchedEffect(Unit) { currentScreen = "schedule" }
            ScheduleOfRacesScreen(
                    viewModel = scheduleViewModel,
            onMenuClick = openDrawer,
            onTicketClick = { city ->
                navHostController.navigate(NavigationScreen.TicketSelection(city))
            }
            )
        }

        composable<NavigationScreen.PilotDetails> { backStackEntry ->
            LaunchedEffect(Unit) { currentScreen = "pilot" }
            val route = backStackEntry.toRoute<NavigationScreen.PilotDetails>()
            PilotDetailsScreen(
                route.pilotId.toInt(),
                onMenuClick = openDrawer,
                onNavigateToHome = {
                    navHostController.navigate(NavigationScreen.RaceInfo) {
                        popUpTo(NavigationScreen.RaceInfo) { inclusive = true }
                    }
                }
            )
        }
    }

    } // ModalNavigationDrawer
}