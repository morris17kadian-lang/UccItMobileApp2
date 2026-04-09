package com.ucc.itmobileapp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ucc.itmobileapp.data.Course
import com.ucc.itmobileapp.data.DefaultCourses
import com.ucc.itmobileapp.ui.theme.UCCITMobileAppTheme
import com.ucc.itmobileapp.ui.theme.UCCYellow

private enum class WebSection(val label: String) {
    Home("Home"),
    Directory("Directory"),
    Courses("Courses"),
    Admissions("Admissions"),
    Social("Social")
}

private data class StaffContact(
    val name: String,
    val role: String,
    val phone: String,
    val email: String,
)

private val webDirectory = listOf(
    StaffContact(
        name = "Peter Ndajah",
        role = "Head - School of Mathematics, Science and Technology",
        phone = "+1-876-906-3000",
        email = "headofschoolsmathit@ucc.edu.jm",
    ),
    StaffContact(
        name = "Otis Osbourne",
        role = "Head of IT Department/Lecturer",
        phone = "+1-876-000-0001",
        email = "otis.osbourne@ucc.edu.jm",
    ),
    StaffContact(
        name = "Craig Wilmot",
        role = "IT Programme Officer",
        phone = "+1-876-236-1895",
        email = "itprogofficer4@ucc.edu.jm",
    ),
    StaffContact(
        name = "Sherida Levy",
        role = "IT Programme Officer",
        phone = "+1-876-906-3000",
        email = "itprogrammeofficer2@ucc.edu.jm",
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UccWebApp() {
    UCCITMobileAppTheme {
        val uriHandler = LocalUriHandler.current
        val courses = remember { DefaultCourses.all() }
        var selectedSection by remember { mutableStateOf(WebSection.Home) }
        var searchQuery by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = "UCC IT Department",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Multiplatform preview for web",
                                style = MaterialTheme.typography.bodySmall,
                                color = UCCYellow,
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F7FB))
                    .padding(padding)
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(WebSection.entries) { section ->
                        Button(
                            onClick = { selectedSection = section },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedSection == section) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.White
                                },
                                contentColor = if (selectedSection == section) {
                                    Color.White
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                            )
                        ) {
                            Text(section.label)
                        }
                    }
                }

                when (selectedSection) {
                    WebSection.Home -> WebHomeContent()
                    WebSection.Directory -> WebDirectoryContent(
                        onEmail = { email -> uriHandler.openUri("mailto:$email") },
                        onCall = { phone -> uriHandler.openUri("tel:$phone") },
                    )
                    WebSection.Courses -> WebCoursesContent(
                        courses = courses,
                        searchQuery = searchQuery,
                        onSearchChange = { searchQuery = it },
                    )
                    WebSection.Admissions -> WebAdmissionsContent(
                        onApply = { uriHandler.openUri("https://ucc.edu.jm/apply") }
                    )
                    WebSection.Social -> WebSocialContent(
                        onOpen = { uriHandler.openUri(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun WebHomeContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Building Jamaica's Tech Future",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "This web version shares the UCC IT content layer and gives the project a usable multiplatform entry point.",
                        color = Color.White.copy(alpha = 0.9f),
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WebStatCard("500+", "Students", Modifier.weight(1f))
                WebStatCard("15+", "Faculty", Modifier.weight(1f))
                WebStatCard("98%", "Placement", Modifier.weight(1f))
            }
        }

        item {
            WebSectionCard(
                title = "What works on the web now",
                body = "Home, directory, course browsing, admissions links, and social links render in Compose for Wasm. Android-only features remain isolated in the Android source set."
            )
        }

        item {
            WebSectionCard(
                title = "Upcoming Event",
                body = "Final Project Demos • April 12, 2026 • Online via Zoom"
            )
        }
    }
}

@Composable
private fun WebDirectoryContent(
    onEmail: (String) -> Unit,
    onCall: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Faculty and Staff Directory",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
        }

        items(webDirectory) { contact ->
            Card(shape = RoundedCornerShape(18.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(contact.name, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(contact.role, color = Color(0xFF555555))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { onEmail(contact.email) }) {
                            Text("Email")
                        }
                        Button(onClick = { onCall(contact.phone) }) {
                            Text("Call")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WebCoursesContent(
    courses: List<Course>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
) {
    val filteredCourses = remember(courses, searchQuery) {
        courses.filter { course ->
            searchQuery.isBlank() ||
                course.name.contains(searchQuery, ignoreCase = true) ||
                course.code.contains(searchQuery, ignoreCase = true)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Courses",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search by code or title") },
                singleLine = true,
            )
        }

        items(filteredCourses) { course ->
            Card(shape = RoundedCornerShape(18.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(course.code, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(course.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Credits: ${course.credits}")
                    Text("Prerequisites: ${course.prerequisites}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(course.description, color = Color(0xFF555555))
                }
            }
        }
    }
}

@Composable
private fun WebAdmissionsContent(onApply: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WebSectionCard(
                title = "Admissions",
                body = "Start your journey with the UCC IT Department. Standard entry requirements include English Language, Mathematics, supporting identification, and an application photo."
            )
        }

        item {
            Button(onClick = onApply) {
                Text("Apply Online")
            }
        }
    }
}

@Composable
private fun WebSocialContent(onOpen: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Social Channels",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onOpen("https://www.facebook.com/uccjamaica") }) {
                    Text("Facebook")
                }
                Button(onClick = { onOpen("https://twitter.com/uccjamaica") }) {
                    Text("X")
                }
                Button(onClick = { onOpen("https://www.instagram.com/uccjamaica") }) {
                    Text("Instagram")
                }
            }
        }
    }
}

@Composable
private fun WebStatCard(number: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(number, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, color = Color(0xFF555555))
        }
    }
}

@Composable
private fun WebSectionCard(title: String, body: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(body, color = Color(0xFF555555))
        }
    }
}