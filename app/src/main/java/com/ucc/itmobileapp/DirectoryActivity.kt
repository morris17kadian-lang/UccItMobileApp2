package com.ucc.itmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity

// This activity is no longer used as its content has been moved to MainActivity.
// Keeping it as a stub to avoid build issues if referenced elsewhere.
class DirectoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
    }
}
