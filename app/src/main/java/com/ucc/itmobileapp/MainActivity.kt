package com.ucc.itmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ucc.itmobileapp.data.AppDatabase
import com.ucc.itmobileapp.data.CourseEntity
import com.ucc.itmobileapp.ui.theme.UCCITMobileAppTheme
import com.ucc.itmobileapp.ui.theme.UCCYellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UCCITMobileAppTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen() {
    val context = LocalContext.current
    var selectedItem by remember { mutableIntStateOf(0) }
    
    val navItems = listOf(
        NavItem("Home", Icons.Filled.Home),
        NavItem("Directory", Icons.Filled.Person),
        NavItem("Courses", Icons.Filled.List),
        NavItem("Admissions", Icons.Filled.Info),
        NavItem("Social", Icons.Filled.Share)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Welcome to UCC IT", 
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        ) 
                        Text(
                            "Innovation • Leadership • Technology",
                            color = UCCYellow,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = UCCYellow,
                            selectedTextColor = UCCYellow,
                            unselectedIconColor = Color.White.copy(alpha = 0.7f),
                            unselectedTextColor = Color.White.copy(alpha = 0.7f),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            if (selectedItem == 0) {
                FloatingActionButton(
                    containerColor = UCCYellow,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:hod.it@ucc.edu.jm")
                            putExtra(Intent.EXTRA_SUBJECT, "UCC IT Department Inquiry")
                            putExtra(Intent.EXTRA_TEXT, "Hello HOD,\n\n")
                        }
                        val chooser = Intent.createChooser(intent, "Email HOD")
                        if (chooser.resolveActivity(context.packageManager) != null) {
                            context.startActivity(chooser)
                        } else {
                            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email HOD")
                }
            }
        }
    ) { padding ->
        when (selectedItem) {
            0 -> HomeContent(padding)
            1 -> DirectoryContent(padding)
            2 -> CoursesContent(padding)
            3 -> AdmissionsContent(padding)
            4 -> SocialMediaContent(padding)
        }
    }
}

@Composable
private fun HomeContent(padding: PaddingValues) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Department Dashboard",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select a tab from the bottom menu to explore our services.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickAction(
                    icon = Icons.Default.Call,
                    label = "Call Us",
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:8769063000")
                        }
                        context.startActivity(intent)
                    }
                )
                QuickAction(
                    icon = Icons.Default.Email,
                    label = "Email Us",
                    onClick = {
                         val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:direct@ucc.edu.jm")
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun QuickAction(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(icon, contentDescription = label)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun DirectoryContent(padding: PaddingValues) {
    val context = LocalContext.current
    val staff = remember {
        listOf(
            StaffMember("Otis Osbourne", "Lecturer", "+1-876-000-0001", "otis.osbourne@ucc.edu.jm"),
            StaffMember("Head of Department", "HOD, Information Technology", "+1-876-000-0002", "hod.it@ucc.edu.jm"),
            StaffMember("Admissions Office", "Department Support", "+1-876-000-0003", "admissions@ucc.edu.jm"),
            StaffMember("Lab Technician", "Technical Support", "+1-876-000-0004", "it.labs@ucc.edu.jm"),
            StaffMember("Programme Coordinator", "Academic Support", "+1-876-000-0005", "it.programme@ucc.edu.jm"),
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Faculty/Staff Directory", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text("Tap the phone or email icons to contact staff.", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(staff) { member ->
            Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(2.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AccountCircle, contentDescription = null, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.size(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(member.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(member.role, style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${member.phone}"))) }) {
                        Icon(Icons.Outlined.Phone, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = { 
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${member.email}"))
                        context.startActivity(Intent.createChooser(intent, "Email"))
                    }) {
                        Icon(Icons.Outlined.Email, contentDescription = "Email", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Composable
private fun CoursesContent(padding: PaddingValues) {
    val context = LocalContext.current
    val dao = remember { AppDatabase.getInstance(context).courseDao() }
    var courses by remember { mutableStateOf<List<CourseEntity>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    val expanded = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(Unit) {
        courses = dao.getAll()
        loading = false
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Text("IT Courses", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Text("Tap a course to view details.", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(courses) { course ->
                val isExpanded = expanded[course.code] == true
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { expanded[course.code] = !isExpanded },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("${course.code} — ${course.name}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Credits: ${course.credits}", style = MaterialTheme.typography.bodySmall)
                        if (isExpanded) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Pre-requisites: ${course.prerequisites}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                            Text(course.description, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdmissionsContent(padding: PaddingValues) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
        Text("Admissions", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Typical requirements include:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        val requirements = listOf("Completed application form", "Academic transcripts / certificates", "Valid identification", "Programme-specific prerequisites")
        requirements.forEach { req ->
            Text("• $req", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 4.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://ucc.edu.jm/apply"))) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Apply Online", color = Color.White)
        }
    }
}

@Composable
private fun SocialMediaContent(padding: PaddingValues) {
    var selectedUrl by remember { mutableStateOf("https://www.facebook.com/uccjamaica") }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    BackHandler(enabled = webViewRef?.canGoBack() == true) {
        webViewRef?.goBack()
    }

    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Facebook", "X/Twitter", "Instagram").forEach { platform ->
                val url = when(platform) {
                    "Facebook" -> "https://www.facebook.com/uccjamaica"
                    "X/Twitter" -> "https://twitter.com/uccjamaica"
                    else -> "https://www.instagram.com/uccjamaica"
                }
                Button(
                    onClick = { selectedUrl = url },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedUrl == url) UCCYellow else MaterialTheme.colorScheme.primary,
                        contentColor = if (selectedUrl == url) MaterialTheme.colorScheme.primary else Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(platform, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(selectedUrl)
                    webViewRef = this
                }
            },
            update = { view -> if (view.url != selectedUrl) view.loadUrl(selectedUrl) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

private data class NavItem(val title: String, val icon: ImageVector)
private data class StaffMember(val name: String, val role: String, val phone: String, val email: String)
