package com.ucc.itmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.ui.graphics.Brush
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucc.itmobileapp.data.AppDatabase
import com.ucc.itmobileapp.data.CourseEntity
import com.ucc.itmobileapp.ui.theme.UCCITMobileAppTheme
import com.ucc.itmobileapp.ui.theme.UCCYellow
/*package com.ucc.itmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucc.itmobileapp.data.AppDatabase
import com.ucc.itmobileapp.data.CourseEntity
import com.ucc.itmobileapp.ui.theme.UCCITMobileAppTheme
import com.ucc.itmobileapp.ui.theme.UCCYellow*/

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
private fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val selectedItem = viewModel.selectedItem

    val navItems = listOf(
        NavItem("Home", Icons.Filled.Home),
        NavItem("Directory", Icons.Filled.Person),
        NavItem("Courses", Icons.Filled.List),
        NavItem("Admission", Icons.Filled.Info),
        NavItem("Social", Icons.Filled.Share)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ucc_logo),
                        contentDescription = "UCC Logo",
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(32.dp)
                            .background(Color.White, CircleShape)
                            .padding(4.dp)
                    )
                },
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "UCC IT Department",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Innovation • Leadership • Technology",
                            color = UCCYellow,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        //Updates the ViewModel state, triggering a recomposition of the screen content
                        onClick = { viewModel.updateSelectedItem(index) },
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
            FloatingActionButton(
                containerColor = UCCYellow,
                contentColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:ithod@ucc.edu.jm")
                        putExtra(Intent.EXTRA_SUBJECT, "UCC IT Department Inquiry")
                    }
                    context.startActivity(Intent.createChooser(intent, "Email HOD"))
                }
            ) {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email HOD")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedItem) {
                0 -> HomeContent()
                1 -> DirectoryContent(PaddingValues(0.dp))
                2 -> CoursesContent(PaddingValues(0.dp))
                3 -> AdmissionsContent(PaddingValues(0.dp))
                4 -> SocialMediaContent(PaddingValues(0.dp))
            }
        }
    }
}

@Composable
private fun HomeContent() {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(bottom = 88.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(5.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.ucc_students),
                        contentDescription = "UCC IT Students",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    //Dark Gradient Overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            "Welcome to the Future",
                            color = UCCYellow,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Explore the UCC IT Hub",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Department of Information Technology",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Divider(modifier = Modifier.width(60.dp).padding(vertical = 8.dp), thickness = 4.dp, color = UCCYellow)
        }

        //WELCOME CARD
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Building Jamaica's Tech Future", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Empowering UCC students to lead the global digital landscape " +
                                "through innovation, hands-on expertise and professional excellence.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }

        //STATS GRID
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatItem("500+", "Students", Modifier.weight(1f))
                StatItem("15+", "Faculty", Modifier.weight(1f))
                StatItem("98%", "Placement", Modifier.weight(1f))
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Upcoming Events",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(12.dp))

                EventItem("Final Project Demos", "April 12, 2026", "Online via Zoom")
               }
        }
    }
}

@Composable
fun EventItem(title: String, date: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Little Date Box
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(UCCYellow.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.List, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Row {
                    Text(date, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    Text(" • ", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    Text(location, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun StatItem(number: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(number, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.labelSmall)
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
            StaffMember(
                name = "Peter Ndajah",
                role = "Head - School of Mathematics, Science and Technology",
                phone = "+1-876-906-3000",
                email = "headofschoolsmathit@ucc.edu.jm",
                imageRes = R.drawable.peter_ndajah
            ),
            StaffMember(
                name = "Otis Osbourne",
                role = "Head of IT Department/Lecturer",
                phone = "+1-876-000-0001",
                email = "otis.osbourne@ucc.edu.jm",
                imageRes = R.drawable.otis_osbourne
            ),
            StaffMember("Craig Wilmot", "IT Programme Officer", "+1-876-236-1895", "itprogofficer4@ucc.edu.jm"),
            StaffMember("Sherida Levy", "IT Programme Officer", "+1-876-906-3000", "itprogrammeofficer2@ucc.edu.jm"),
            StaffMember("UCC Online", "Help Desk", "+1-876-802-5891", "ucconline@ucc.edu.jm"),
            StaffMember("Romero Williams", "Online Technical Officer", "+1-876-322-5920", "ucconlineofficer3@ucc.edu.jm")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 88.dp)
    ) {
        item {
            Text(
                text = "Faculty/Staff Directory",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tap the phone or email icons to contact staff.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(staff) { member ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Show Image if available, else show default icon
                    if (member.imageRes != null) {
                        Image(
                            painter = painterResource(id = member.imageRes),
                            contentDescription = member.name,
                            modifier = Modifier
                                .size(52.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        //Fallback: Account Circle icon
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(52.dp),
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = member.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = member.role,
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = androidx.compose.ui.unit.TextUnit.Unspecified
                        )
                    }

                    // Action Buttons
                    Row {
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${member.phone}"))
                            context.startActivity(intent)
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = "Call",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${member.email}"))
                            context.startActivity(Intent.createChooser(intent, "Send Email"))
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Email",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
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
    var selectedCourse by remember { mutableStateOf<CourseEntity?>(null) }
    var selectedTrack by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        courses = dao.getAll()
        loading = false
    }

    val filteredCourses = remember(courses, selectedTrack, searchQuery) {
        courses
            .filter { if (selectedTrack == "All") true else courseFilterCategory(it) == selectedTrack }
            .filter { if (searchQuery.isBlank()) true else it.name.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery, ignoreCase = true) }
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "IT Course Selection",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF131313)
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search courses...") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf("All", "Core", "Advanced")) { track ->
                        CourseTrackChip(
                            label = track,
                            isSelected = selectedTrack == track,
                            onClick = { selectedTrack = track }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text("Code", modifier = Modifier.weight(0.18f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
                Text("Name", modifier = Modifier.weight(0.42f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
                Text("Credits", modifier = Modifier.weight(0.18f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
                Text("Lecturer", modifier = Modifier.weight(0.22f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
            }
            Divider(color = Color(0xFFDDDDDD))
            LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                items(filteredCourses) { course ->
                    Card(
                        onClick = { selectedCourse = course },
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = course.code,
                                modifier = Modifier.weight(0.18f),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1A237E)
                            )
                            Text(
                                text = course.name,
                                modifier = Modifier.weight(0.42f),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF202020)
                            )
                            Text(
                                text = "${course.credits}",
                                modifier = Modifier.weight(0.18f),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF202020)
                            )
                            Text(
                                text = "TBD",
                                modifier = Modifier.weight(0.22f),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF555555)
                            )
                        }
                    }
                    Divider(color = Color(0xFFEEEEEE))
                }
            }
        }
        selectedCourse?.let { course ->
            CourseDetailsOverlay(course = course, onDismiss = { selectedCourse = null })
        }
    }
}

@Composable
private fun CourseTrackChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF1A237E) else Color(0xFFEBEBEB)
        ),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else Color(0xFF333333)
        )
    }
}

@Composable
private fun CourseDetailsOverlay(course: CourseEntity, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = course.code,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xFF6A6A6A),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = course.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF121212),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close course details",
                            tint = Color(0xFF121212)
                        )
                    }
                }

                Divider(color = Color(0xFFE7E7E7))

                Text(
                    text = "${course.credits} credits",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF111111)
                )

                Text(
                    text = if (course.prerequisites.isBlank()) "Pre-requisites: None" else "Pre-requisites: ${course.prerequisites}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3A3A3A)
                )

                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF3A3A3A)
                )
            }
        }
    }
}

private fun courseTrackLabel(course: CourseEntity): String {
    val digits = course.code.filter(Char::isDigit)
    return when (digits.firstOrNull()) {
        '1' -> "Level 1"
        '2' -> "Level 2"
        '3' -> "Level 3"
        '4' -> "Level 4"
        else -> "Core"
    }
}

private fun courseFilterCategory(course: CourseEntity): String {
    val digit = course.code.filter(Char::isDigit).firstOrNull()
    return if (digit == '3' || digit == '4') "Advanced" else "Core"
}

private fun String.coursePreview(limit: Int = 52): String {
    val normalized = trim().replace("\n", " ")
    return if (normalized.length <= limit) normalized else normalized.take(limit).trimEnd() + "..."
}

@Composable
private fun AdmissionsContent(padding: PaddingValues) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Admissions", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Start your journey with the UCC IT Department.", style = MaterialTheme.typography.bodySmall)
        }

        // Requirements Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Standard Entry Requirements", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    val bulletPoints = listOf(
                        "5 CSEC/GCE subjects (Grades 1-3/I-III)",
                        "Mandatory: English Language & Mathematics",
                        "Certified Birth Certificate & TRN",
                        "Passport-sized photograph"
                    )

                    bulletPoints.forEach { point ->
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text("• ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                            Text(point, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        // Application Link Button
        item {
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ucc.edu.jm/apply"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = UCCYellow)
                Spacer(Modifier.width(8.dp))
                Text("Apply Online Now", fontWeight = FontWeight.Bold)
            }
        }

        // Status Tracker Note
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = UCCYellow.copy(alpha = 0.1f))
            ) {
                Text(
                    text = "Already applied? Visit the UCC website to use the Application Status Tracker.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
private data class StaffMember(val name: String, val role: String, val phone: String, val email: String, val imageRes: Int? = null)
