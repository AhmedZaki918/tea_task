package com.example.tea_task.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.tea_task.data.model.competition.Competition
import com.example.tea_task.ui.theme.BlackLight
import com.example.tea_task.ui.theme.MEDIUM_MARGIN
import com.example.tea_task.ui.theme.SMALL_MARGIN
import com.example.tea_task.util.SubTitle
import com.example.tea_task.util.Title

@Composable
fun ListItemHome(currentItem: Competition) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = SMALL_MARGIN),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BlackLight)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (textAreaName, textAreaCode, imageEmblem, textName, textType) = createRefs()

            // Area name
            Title(
                description = currentItem.area.name,
                modifier = Modifier.constrainAs(textAreaName) {
                    top.linkTo(parent.top, MEDIUM_MARGIN)
                    start.linkTo(parent.start, MEDIUM_MARGIN)
                }
            )

            // Area code
            SubTitle(
                description = currentItem.area.code,
                modifier = Modifier.constrainAs(textAreaCode) {
                    top.linkTo(textAreaName.top)
                    bottom.linkTo(textAreaName.bottom)
                    end.linkTo(parent.end, MEDIUM_MARGIN)
                }
            )

            AsyncImage(
                model = currentItem.emblem,
                contentDescription = "Team logo",
                modifier = Modifier
                    .constrainAs(imageEmblem) {
                        top.linkTo(textAreaName.bottom, MEDIUM_MARGIN)
                        start.linkTo(textAreaName.start)
                        end.linkTo(textAreaName.end)
                    }
                    .size(70.dp)
                    .padding(bottom = MEDIUM_MARGIN),
                contentScale = ContentScale.Fit
            )


            // Sub Name
            Title(
                description = currentItem.name,
                modifier = Modifier.constrainAs(textName) {
                    top.linkTo(imageEmblem.top)
                    start.linkTo(imageEmblem.end, MEDIUM_MARGIN)
                }
            )

            // Type
            SubTitle(
                modifier = Modifier.constrainAs(textType) {
                    top.linkTo(textName.bottom)
                    start.linkTo(textName.start)
                },
                description = currentItem.type
            )
        }
    }
}

