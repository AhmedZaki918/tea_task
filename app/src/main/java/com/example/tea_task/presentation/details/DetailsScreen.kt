package com.example.tea_task.presentation.details

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.tea_task.R
import com.example.tea_task.data.model.competition.Competition
import com.example.tea_task.presentation.home.HomeViewModel
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

@Composable
fun DetailsScreen(
    viewModel: HomeViewModel
) {
    val competition = viewModel.uiState.collectAsState().value.competitionDetails

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        DetailsHeader(competition)
        CurrentSeason(competition)
    }
}

@Composable
fun CurrentSeason(competition: Competition) {

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
                description = competition.currentSeason.startDate
            )

            // Child start date
            SubTitle(
                modifier = Modifier.constrainAs(textChildStartDate) {
                    start.linkTo(textParentStartDate.start)
                    top.linkTo(textParentStartDate.bottom, SMALL_MARGIN)
                },
                description = stringResource(R.string.start_date)
            )


            // Parent end date
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentEndDate) {
                    start.linkTo(textParentStartDate.end, BIG_MARGIN)
                    top.linkTo(parent.top, MEDIUM_MARGIN)
                },
                description = competition.currentSeason.endDate
            )


            // Child end date
            SubTitle(
                modifier = Modifier.constrainAs(textChildEndDate) {
                    start.linkTo(textParentEndDate.start)
                    top.linkTo(textParentEndDate.bottom, SMALL_MARGIN)
                },
                description = stringResource(R.string.end_date)
            )

            // Parent current matchday
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentMatchday) {
                    start.linkTo(textParentStartDate.start)
                    top.linkTo(textChildStartDate.bottom, CUSTOM_MARGIN)
                },
                description = competition.currentSeason.currentMatchday.toString()
            )

            // Child current matchday
            SubTitle(
                modifier = Modifier.constrainAs(textChildMatchday) {
                    start.linkTo(textParentMatchday.start)
                    top.linkTo(textParentMatchday.bottom, SMALL_MARGIN)
                },
                description = stringResource(R.string.current_matchday)
            )


            // Parent seasons left
            Title(
                fontFamily = FontFamily.Serif,
                color = White,
                modifier = Modifier.constrainAs(textParentSeason) {
                    start.linkTo(textParentEndDate.start)
                    top.linkTo(textChildEndDate.bottom, CUSTOM_MARGIN)
                },
                description = competition.numberOfAvailableSeasons.toString()
            )

            // Child seasons left
            SubTitle(
                modifier = Modifier.constrainAs(textChildSeason) {
                    start.linkTo(textParentSeason.start)
                    top.linkTo(textParentSeason.bottom, SMALL_MARGIN)
                },
                description = stringResource(R.string.seasons_left)
            )
        }
    }
}

@Composable
fun DetailsHeader(competition: Competition) {
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
            description = competition.name,
            fontSize = 20.sp,
            color = White,
            fontFamily = FontFamily.Serif
        )

        SubTitle(
            modifier = Modifier.constrainAs(textChampionshipType) {
                bottom.linkTo(parent.bottom, SMALL_MARGIN)
                start.linkTo(textChampionship.start)
            },
            description = competition.area.name
        )


        AsyncImage(
            model = competition.emblem,
            contentDescription = "Team logo",
            modifier = Modifier
                .constrainAs(imageTeamLogo) {
                    end.linkTo(parent.end, MEDIUM_MARGIN)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(130.dp)
                .padding(bottom = MEDIUM_MARGIN),
            contentScale = ContentScale.Fit
        )
    }
}