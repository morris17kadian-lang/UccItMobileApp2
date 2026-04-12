package com.ucc.itmobileapp

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ucc.itmobileapp.data.Course
import com.ucc.itmobileapp.data.DefaultCourses
import com.ucc.itmobileapp.ui.theme.UCCITMobileAppTheme
import com.ucc.itmobileapp.ui.theme.UCCYellow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import uccitmobileapp.app.generated.resources.Res
import uccitmobileapp.app.generated.resources.otis_osbourne
import uccitmobileapp.app.generated.resources.peter_ndajah
import uccitmobileapp.app.generated.resources.ucc_logo
import uccitmobileapp.app.generated.resources.ucc_students

private data class WebNavItem(val title: String, val icon: ImageVector)

private data class StaffContact(
    val name: String,
    val role: String,
    val phone: String,
    val email: String,
    val image: DrawableResource? = null,
)

private val staffDirectory = listOf(
    StaffContact("Peter Ndajah", "Head - School of Mathematics, Science and Technology", "+1-876-906-3000", "headofschoolsmathit@ucc.edu.jm", Res.drawable.peter_ndajah),
    StaffContact("Otis Osbourne", "Head of IT Department/Lecturer", "+1-876-000-0001", "otis.osbourne@ucc.edu.jm", Res.drawable.otis_osbourne),
    StaffContact("Craig Wilmot", "IT Programme Officer", "+1-876-236-1895", "itprogofficer4@ucc.edu.jm"),
    StaffContact("Sherida Levy", "IT Programme Officer", "+1-876-906-3000", "itprogrammeofficer2@ucc.edu.jm"),
    StaffContact("UCC Online", "Help Desk", "+1-876-802-5891", "ucconline@ucc.edu.jm"),
    StaffContact("Romero Williams", "Online Technical Officer", "+1-876-322-5920", "ucconlineofficer3@ucc.edu.jm"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UccSharedApp() {
    UCCITMobileAppTheme {
        val uriHandler = LocalUriHandler.current
        var selectedItem by remember { mutableStateOf(0) }

        val navItems = listOf(
            WebNavItem("Home", Icons.Filled.Home),
            WebNavItem("Directory", Icons.Filled.Person),
            WebNavItem("Courses", Icons.Filled.List),
            WebNavItem("Admission", Icons.Filled.Info),
            WebNavItem("Social", Icons.Filled.Share),
        )

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Image(
                            painter = painterResource(Res.drawable.ucc_logo),
                            contentDescription = "UCC Logo",
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(32.dp)
                                .background(Color.White, CircleShape)
                                .padding(4.dp),
                        )
                    },
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "UCC IT Department",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = "Innovation \u2022 Leadership \u2022 Technology",
                                color = UCCYellow,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                )
            },
            bottomBar = {
                NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
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
                                indicatorColor = Color.Transparent,
                            ),
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = UCCYellow,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        uriHandler.openUri("mailto:ithod@ucc.edu.jm?subject=UCC%20IT%20Department%20Inquiry")
                    },
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email HOD")
                }
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F7FB))
                    .padding(padding),
            ) {
                when (selectedItem) {
                    0 -> WebHomeContent()
                    1 -> WebDirectoryContent(
                        onEmail = { uriHandler.openUri("mailto:$it") },
                        onCall = { uriHandler.openUri("tel:$it") },
                    )
                    2 -> WebCoursesContent()
                    3 -> WebAdmissionsContent(onApply = { uriHandler.openUri("https://ucc.edu.jm/apply") })
                    4 -> WebSocialContent(onOpen = { uriHandler.openUri(it) })
                }
            }
        }
    }
}

@Composable
private fun WebHomeContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(bottom = 88.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(5.dp))
            Card(
                modifier = Modifier.fillMaxWidth().height(160.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(Res.drawable.ucc_students),
                        contentDescription = "UCC IT Students",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                ),
                            ),
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp),
                    ) {
                        Text(
                            "Welcome to the Future",
                            color = UCCYellow,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            "Explore the UCC IT Hub",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
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
                color = MaterialTheme.colorScheme.primary,
            )
            Divider(
                modifier = Modifier.width(60.dp).padding(vertical = 8.dp),
                thickness = 4.dp,
                color = UCCYellow,
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "Building Jamaica's Tech Future",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Empowering UCC students to lead the global digital landscape " +
                            "through innovation, hands-on expertise and professional excellence.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray,
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                WebStatItem("500+", "Students", Modifier.weight(1f))
                WebStatItem("15+", "Faculty", Modifier.weight(1f))
                WebStatItem("98%", "Placement", Modifier.weight(1f))
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Upcoming Events",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(12.dp))
                WebEventItem("Final Project Demos", "April 12, 2026", "Online via Zoom")
            }
        }
    }
}

@Composable
private fun WebEventItem(title: String, date: String, location: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(UCCYellow.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Row {
                    Text(date, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    Text(" \u2022 ", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    Text(location, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
private fun WebStatItem(number: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(number, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun WebDirectoryContent(onEmail: (String) -> Unit, onCall: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 88.dp),
    ) {
        item {
            Text(
                text = "Faculty/Staff Directory",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Tap the phone or email icons to contact staff.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(staffDirectory) { member ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (member.image != null) {
                        Image(
                            painter = painterResource(member.image),
                            contentDescription = member.name,
                            modifier = Modifier.size(52.dp),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(52.dp),
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = member.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = member.role,
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = TextUnit.Unspecified,
                        )
                    }
                    Row {
                        IconButton(onClick = { onCall(member.phone) }) {
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = "Call",
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                        IconButton(onClick = { onEmail(member.email) }) {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Email",
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WebCoursesContent() {
    val courses = remember { DefaultCourses.all() }
    var selectedCourse by remember { mutableStateOf<Course?>(null) }
    var selectedTrack by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val filteredCourses = remember(courses, selectedTrack, searchQuery) {
        courses
            .filter { if (selectedTrack == "All") true else webCourseFilterCategory(it) == selectedTrack }
            .filter {
                if (searchQuery.isBlank()) true
                else it.name.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery, ignoreCase = true)
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                text = "IT Courses",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Select a course to view more details.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search courses...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("All", "Core", "Advanced")) { track ->
                    WebCourseTrackChip(
                        label = track,
                        isSelected = selectedTrack == track,
                        onClick = { selectedTrack = track },
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 10.dp),
        ) {
            Text("Code", modifier = Modifier.weight(0.18f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
            Text("Name", modifier = Modifier.weight(0.42f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
            Text("Credits", modifier = Modifier.weight(0.18f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
            Text("Lecturer", modifier = Modifier.weight(0.22f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
        }
        Divider(color = Color(0xFFDDDDDD))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 88.dp),
        ) {
            items(filteredCourses) { course ->
                Card(
                    onClick = { selectedCourse = course },
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = RoundedCornerShape(0.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(course.code, modifier = Modifier.weight(0.18f), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A237E))
                        Text(course.name, modifier = Modifier.weight(0.42f), style = MaterialTheme.typography.bodyMedium, color = Color(0xFF202020))
                        Text("${course.credits}", modifier = Modifier.weight(0.18f), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center, color = Color(0xFF202020))
                        Text("TBD", modifier = Modifier.weight(0.22f), style = MaterialTheme.typography.bodyMedium, color = Color(0xFF555555))
                    }
                }
                Divider(color = Color(0xFFEEEEEE))
            }
        }
    }

    selectedCourse?.let { course ->
        WebCourseDetailsOverlay(course = course, onDismiss = { selectedCourse = null })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WebCourseTrackChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF1A237E) else Color(0xFFEBEBEB),
        ),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else Color(0xFF333333),
        )
    }
}

@Composable
private fun WebCourseDetailsOverlay(course: Course, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(30.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(course.code, style = MaterialTheme.typography.labelLarge, color = Color(0xFF6A6A6A), fontWeight = FontWeight.Bold)
                        Text(course.name, style = MaterialTheme.typography.headlineSmall, color = Color(0xFF121212), fontWeight = FontWeight.ExtraBold)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close", tint = Color(0xFF121212))
                    }
                }
                Divider(color = Color(0xFFE7E7E7))
                Text("${course.credits} credits", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = Color(0xFF111111))
                Text(
                    if (course.prerequisites.isBlank()) "Pre-requisites: None" else "Pre-requisites: ${course.prerequisites}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3A3A3A),
                )
                Text(course.description, style = MaterialTheme.typography.bodyLarge, color = Color(0xFF3A3A3A))
            }
        }
    }
}

@Composable
private fun WebAdmissionsContent(onApply: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text(
                "Admissions",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "Start your journey with the UCC IT Department.",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Standard Entry Requirements",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    listOf(
                        "5 CSEC/GCE subjects (Grades 1-3/I-III)",
                        "Mandatory: English Language & Mathematics",
                        "Certified Birth Certificate & TRN",
                        "Passport-sized photograph",
                    ).forEach { point ->
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text("\u2022 ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                            Text(point, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        item {
            Button(
                onClick = onApply,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp),
            ) {
                Icon(Icons.Filled.Info, contentDescription = null, tint = UCCYellow)
                Spacer(Modifier.width(8.dp))
                Text("Apply Online Now", fontWeight = FontWeight.Bold)
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = UCCYellow.copy(alpha = 0.1f)),
            ) {
                Text(
                    text = "Already applied? Visit the UCC website to use the Application Status Tracker.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun WebSocialContent(onOpen: (String) -> Unit) {
    var selectedUrl by remember { mutableStateOf("https://www.facebook.com/uccjamaica") }
    val selectedLabel = when (selectedUrl) {
        "https://www.facebook.com/uccjamaica" -> "Facebook"
        "https://twitter.com/uccjamaica" -> "X/Twitter"
        else -> "Instagram"
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(
                "Facebook" to "https://www.facebook.com/uccjamaica",
                "X/Twitter" to "https://twitter.com/uccjamaica",
                "Instagram" to "https://www.instagram.com/uccjamaica",
            ).forEach { (platform, url) ->
                Button(
                    onClick = { selectedUrl = url },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedUrl == url) UCCYellow else MaterialTheme.colorScheme.primary,
                        contentColor = if (selectedUrl == url) MaterialTheme.colorScheme.primary else Color.White,
                    ),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(platform, style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9FC)),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    )
                    Text(
                        text = "${selectedLabel} is selected.",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = "Android shows this inside an embedded WebView. On the web target, the closest stable behavior is opening the official page in a new browser tab.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                    )
                    Button(
                        onClick = { onOpen(selectedUrl) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    ) {
                        Text("Open ${selectedLabel}", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

private fun webCourseFilterCategory(course: Course): String {
    val digit = course.code.filter(Char::isDigit).firstOrNull()
    return if (digit == '3' || digit == '4') "Advanced" else "Core"
}
