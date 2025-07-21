package com.github.adnanaslamdev.stickrshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.adnanaslamdev.stickr.Stickr
import com.github.adnanaslamdev.stickr.StickrContent
import com.github.adnanaslamdev.stickr.StickrData
import com.github.adnanaslamdev.stickr.StickrDefaults
import com.github.adnanaslamdev.stickr.Utility
import com.github.adnanaslamdev.stickr.Utility.generateUniqueId
import com.github.adnanaslamdev.stickrshowcase.ui.theme.StickrShowcaseTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rememberModalBottomSheetState(skipPartiallyExpanded = true)
            rememberCoroutineScope()
            var showBottomSheet by remember { mutableStateOf(false) }
            val stickers = remember { mutableStateListOf<StickrData>() }
            var selectedId by remember { mutableStateOf<Int?>(null) }

            StickrShowcaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            showBottomSheet = !showBottomSheet
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Sticker"
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {

                        stickers.forEach { stickrData ->
                            key(stickrData.id) {
                                Stickr(
                                    content = stickrData.content,
                                    showControls = true,
                                    borderColor = Color.White,
                                    controls = StickrDefaults.defaultControls(),
                                    isSelected = selectedId == stickrData.id,
                                    onSelect = {
                                        selectedId = stickrData.id
                                    },
                                    onDelete = {
                                        stickers.removeIf { it.id == stickrData.id }
                                    },
                                    onDuplicate = {
                                        val newId =
                                            Utility.generateUniqueId() // Create a function to generate unique IDs
                                        stickers.add(
                                            StickrData(
                                                id = newId,
                                                content = stickrData.content
                                            )
                                        )
                                        selectedId = newId
                                    },
                                    onFlip = {
                                        // Optional: Add flip logic to StickrData if needed
                                    }
                                )
                            }
                        }

                    }

                    if (showBottomSheet) {
                        StickerSheet(
                            modifier = Modifier.fillMaxWidth(),
                            onDismiss = {
                                showBottomSheet = false
                            },
                            onClick = { id ->
                                val newId = Utility.generateUniqueId()
                                stickers.add(
                                    StickrData(
                                        id = newId,
                                        content = StickrContent.DrawableRes(id)
                                    )
                                )
                                selectedId = newId
                                showBottomSheet = false
                            }
                        )
                    }
                }
            }
        }
    }
}