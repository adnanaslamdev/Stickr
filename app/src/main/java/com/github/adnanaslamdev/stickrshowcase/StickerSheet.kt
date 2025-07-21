package com.github.adnanaslamdev.stickrshowcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickerSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onClick: (Int) -> Unit = {}
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(horizontal = 10.dp),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
        ) {
            items(stickers.shuffled()) { sticker ->
                Image(
                    painter = painterResource(sticker),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(.8f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF202020))
                        .clickable {
                            onClick(sticker)
                        }
                )
            }
        }
    }

}

private val stickers = listOf(
    R.drawable.cute_1,
    R.drawable.sticker_1,
    R.drawable.sticker_2,
    R.drawable.sticker_3,
    R.drawable.sticker_4,
    R.drawable.sticker_5,
    R.drawable.sticker_6,
)