package com.example.rustorecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AppInfo(
    val id: String,
    val name: String,
    val description: String,
    val category: String
)

val mockApps = listOf(
    AppInfo("1", "RuStore", "Магазин приложений", "Инструменты"),
    AppInfo("2", "Гильдия Героев", "Легендарный рейд героев в Фэнтези РПГ", "Игры"),
    AppInfo("3", "VK", "Социальная сеть", "Социальные"),
    AppInfo("4", "Музыка", "Слушайте музыку и подкасты", "Медиа"),
    AppInfo("5", "Заметки", "Быстрые и удобные заметки", "Продуктивность")
)

val AccentColor = Color(0xFF0077FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppListScreen(apps = mockApps)
            }
        }
    }
}

@Composable
fun AppListScreen(apps: List<AppInfo>) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolbar()
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(apps) { app ->
                AppListItemRow(app = app)
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
fun AppListItemRow(app: AppInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Иконка ${app.name}",
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0E0E0))
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = app.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = app.category,
                fontSize = 12.sp,
                color = AccentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppListScreen() {
    MaterialTheme {
        AppListScreen(apps = mockApps)
    }
}