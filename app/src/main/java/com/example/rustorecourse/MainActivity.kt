package com.example.rustorecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class AppInfo(
    val id: String,
    val name: String,
    val description: String,
    val category: String
)

val mockApps = listOf(
    AppInfo("1", "RuStore", "Магазин приложений", "Инструменты"),
    AppInfo("2", "Гильдия Героев", "Легендарный рейд героев", "Игры"),
    AppInfo("3", "VK", "Социальная сеть", "Социальные")
)

val storefrontApps = listOf(
    AppInfo("s1", "App 1", "Описание 1", "Категория"),
    AppInfo("s2", "App 2", "Описание 2", "Категория"),
    AppInfo("s3", "App 3", "Описание 3", "Категория"),
    AppInfo("s4", "App 4", "Описание 4", "Категория"),
    AppInfo("s5", "App 5", "Описание 5", "Категория"),
    AppInfo("s6", "App 6", "Описание 6", "Категория"),
    AppInfo("s7", "App 7", "Описание 7", "Категория"),
    AppInfo("s8", "App 8", "Описание 8", "Категория"),
    AppInfo("s9", "App 9", "Описание 9", "Категория")
)

val AccentColor = Color(0xFF0077FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "app_list") {
                    composable("app_list") {
                        AppListScreen(
                            apps = mockApps,
                            onAppClick = { navController.navigate("app_details") }
                        )
                    }
                    composable("app_details") {
                        AppDetailsScreen(
                            onBackClick = { navController.popBackStack() },
                            onStorefrontAppClick = { navController.navigate("app_list") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppListScreen(apps: List<AppInfo>, onAppClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolbar()
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(apps) { app ->
                AppListItemRow(app = app, onClick = onAppClick)
            }
        }
    }
}

@Composable
fun AppToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AccentColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Icon",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun AppDetailsScreen(onBackClick: () -> Unit, onStorefrontAppClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "← Назад",
                modifier = Modifier.clickable { onBackClick() },
                color = AccentColor,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Гильдия Героев", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Легендарный рейд героев в Фэнтези РПГ", color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Витрина",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val pagerState = rememberPagerState(pageCount = { 3 })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val startIndex = page * 3
            val pageApps = storefrontApps.subList(startIndex, startIndex + 3)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pageApps.forEach { app ->
                    Box(modifier = Modifier.weight(1f)) {
                        AppListItemRow(
                            app = app,
                            onClick = onStorefrontAppClick,
                            isVertical = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppListItemRow(
    app: AppInfo,
    onClick: () -> Unit,
    isVertical: Boolean = false
) {
    if (isVertical) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0E0E0))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE0E0E0))
                    .padding(12.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = app.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = app.description, fontSize = 14.sp, color = Color.Gray, maxLines = 2)
                Text(text = app.category, fontSize = 12.sp, color = AccentColor)
            }
        }
    }
}