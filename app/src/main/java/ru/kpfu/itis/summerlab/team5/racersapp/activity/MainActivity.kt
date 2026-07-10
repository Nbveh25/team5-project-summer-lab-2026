package ru.kpfu.itis.summerlab.team5.racersapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.navigation.AppRoot
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.theme.SummerPractiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SummerPractiseTheme {
                val navHostController = rememberNavController()
                AppRoot(navHostController = navHostController)
            }
        }
    }
}
