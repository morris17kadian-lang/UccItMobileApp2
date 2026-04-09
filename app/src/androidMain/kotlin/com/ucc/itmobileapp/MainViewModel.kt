package com.ucc.itmobileapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var selectedItem by mutableIntStateOf(0)
        private set // Only the ViewModel should change this directly

    fun updateSelectedItem(index: Int) {
        selectedItem = index
    }
}
