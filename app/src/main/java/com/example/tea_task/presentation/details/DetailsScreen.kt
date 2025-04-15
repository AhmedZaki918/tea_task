package com.example.tea_task.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tea_task.R
import com.example.tea_task.ui.theme.BIG_MARGIN
import com.example.tea_task.ui.theme.Black
import com.example.tea_task.ui.theme.BlackLight
import com.example.tea_task.ui.theme.Blue
import com.example.tea_task.ui.theme.CUSTOM_MARGIN
import com.example.tea_task.ui.theme.LARGE_MARGIN
import com.example.tea_task.ui.theme.MEDIUM_MARGIN
import com.example.tea_task.ui.theme.SMALL_MARGIN
import com.example.tea_task.ui.theme.VERY_SMALL_MARGIN
import com.example.tea_task.ui.theme.White
import com.example.tea_task.util.SubTitle
import com.example.tea_task.util.Title

@Preview(showSystemUi = true)
@Composable
fun DetailsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        DetailsHeader()
        CurrentSeason()
    }
}

@Composable
fun CurrentSeason() {

    Title(
        modifier = Modifier.padding(
            start = CUSTOM_MARGIN,
            top = LARGE_MARGIN
        ),
        fontFamily = FontFamily.Serif,
        description = stringResource(R.string.current_season)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = MEDIUM_MARGIN),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BlackLight)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = MEDIUM_MARGIN)
        ) {
            val (textParentStartDate, textChildStartDate, textParentEndDate,
                textChildEndDate, textParentMatchday, textChildMatchday,
                textParentSeason, textChildSeason
            ) = createRefs()

            // Parent start date
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentStartDate) {
                    start.linkTo(parent.start, MEDIUM_MARGIN)
                    top.linkTo(parent.top, MEDIUM_MARGIN)
                },
                description = "2025-12-21"
            )

            // Child start date
            SubTitle(
                modifier = Modifier.constrainAs(textChildStartDate) {
                    start.linkTo(textParentStartDate.start)
                    top.linkTo(textParentStartDate.bottom, SMALL_MARGIN)
                },
                description = "Start date"
            )


            // Parent end date
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentEndDate) {
                    start.linkTo(textParentStartDate.end, BIG_MARGIN)
                    top.linkTo(parent.top, MEDIUM_MARGIN)
                },
                description = "2026-01-18"
            )


            // Child end date
            SubTitle(
                modifier = Modifier.constrainAs(textChildEndDate) {
                    start.linkTo(textParentEndDate.start)
                    top.linkTo(textParentEndDate.bottom, SMALL_MARGIN)
                },
                description = "End date"
            )

            // Parent current matchday
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentMatchday) {
                    start.linkTo(textParentStartDate.start)
                    top.linkTo(textChildStartDate.bottom, CUSTOM_MARGIN)
                },
                description = "22"
            )

            // Child current matchday
            SubTitle(
                modifier = Modifier.constrainAs(textChildMatchday) {
                    start.linkTo(textParentMatchday.start)
                    top.linkTo(textParentMatchday.bottom, SMALL_MARGIN)
                },
                description = "Current matchday"
            )


            // Parent seasons left
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentSeason) {
                    start.linkTo(textParentEndDate.start)
                    top.linkTo(textChildEndDate.bottom, CUSTOM_MARGIN)
                },
                description = "3"
            )

            // Child seasons left
            SubTitle(
                modifier = Modifier.constrainAs(textChildSeason) {
                    start.linkTo(textParentSeason.start)
                    top.linkTo(textParentSeason.bottom, SMALL_MARGIN)
                },
                description = "Seasons left"
            )
        }
    }
}

@Composable
fun DetailsHeader() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Blue)
    ) {
        val (textChampionship, imageTeamLogo, textChampionshipType) = createRefs()

        Title(
            modifier = Modifier.constrainAs(textChampionship) {
                start.linkTo(parent.start, MEDIUM_MARGIN)
                bottom.linkTo(textChampionshipType.top, VERY_SMALL_MARGIN)
            },
            description = "AFC Champions League",
            fontSize = 20.sp,
            color = White,
            fontFamily = FontFamily.Serif
        )

        SubTitle(
            modifier = Modifier.constrainAs(textChampionshipType) {
                bottom.linkTo(parent.bottom, SMALL_MARGIN)
                start.linkTo(textChampionship.start)
            },
            description = "France"
        )

        Image(
            modifier = Modifier
                .constrainAs(imageTeamLogo) {
                    end.linkTo(parent.end, SMALL_MARGIN)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(150.dp),
            contentDescription = "",
            painter = painterResource(id = R.drawable.abl)
        )
    }
}