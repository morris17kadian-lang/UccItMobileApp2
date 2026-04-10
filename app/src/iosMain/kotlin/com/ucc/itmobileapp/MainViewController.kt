package com.ucc.itmobileapp

import androidx.compose.ui.window.ComposeUIViewController

// Lowercase 'm' to satisfy the naming warning
fun mainViewController() = ComposeUIViewController {
    UccWebApp()
}