package com.hitech.pickit.media.presentation.screens.detail_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import androidx.xr.compose.testing.toDp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.hitech.pickit.R
import com.hitech.pickit.media.data.datasource.remote.mappers.asActor
import com.hitech.pickit.media.domain.model.Genre
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.domain.model.TMDbImage
import com.hitech.pickit.media.domain.model.TMDbItemDetails
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.models.Content
import com.hitech.pickit.media.presentation.models.Credit
import com.hitech.pickit.media.presentation.models.TMDbItem
import com.hitech.pickit.media.presentation.models.toDisplayableNumber
import com.hitech.pickit.media.presentation.screens.detail_screen.utils.openInChromeCustomTab
import com.hitech.pickit.media.presentation.utili.Spacing
import com.hitech.pickit.media.presentation.utili.components.PersonCard
import com.hitech.pickit.media.presentation.utili.components.TMDbCard
import com.hitech.pickit.media.presentation.utili.dpToPx
import com.hitech.pickit.navigation.MainDestinations
import com.hitech.pickit.navigation.MainDestinations.TMDB_IMAGES_ROUTE

@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel
) {
    DetailScreen(
        viewModel = viewModel,
        navController = navController,
        onTMDbItemSelected = { navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        onAllSimilarSelected = { navController.navigate("${MainDestinations.TMDB_SIMILAR_MOVIES_ROUTE}/$it") },
    ) { details ->
        Movie(
            id = details.id,
            overview = details.overview,
            releaseDate = details.releaseDate,
            backdropUrl = details.backdropPath,
            posterUrl = details.posterPath,
            name = details.title,
            voteAverage = details.voteAverage,
            voteCount = details.voteCount,
            genreIds = details.genres.map { it.id },
        )
    }
}


@Composable
fun TVShowDetailScreen(
    navController: NavController,
    viewModel: TVShowDetailViewModel
) {
    DetailScreen(
        viewModel = viewModel,
        navController = navController,
        onTMDbItemSelected = { navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        onAllSimilarSelected = { navController.navigate("${MainDestinations.TMDB_SIMILAR_TV_SHOW_ROUTE}/$it") },
    ) { details ->
        TVShow(
            id = details.id,
            overview = details.overview,
            releaseDate = details.releaseDate,
            backdropUrl = details.backdropPath,
            posterUrl = details.posterPath,
            name = details.title,
            voteAverage = details.voteAverage,
            voteCount = details.voteCount,
            genreIds = details.genres.map { it.id }
        )
    }
}

@Composable
private fun <T : TMDbItemDetails, E : TMDbItem> DetailScreen(
    viewModel: BaseDetailViewModel<T, E>,
    navController: NavController,
    onTMDbItemSelected: (TMDbItem) -> Unit,
    onAllSimilarSelected: (Int) -> Unit,
    getBookmarkedItem: (TMDbItemDetails) -> E,
) {
    DetailScreen(
        viewModel = viewModel,
        navController = navController,
        onImagesSelected = { images, index ->
            navController.navigate(
                "${TMDB_IMAGES_ROUTE}/$index"
            )
        },
        onTMDbItemSelected = onTMDbItemSelected,
        onAllSimilarSelected = onAllSimilarSelected,
        navigateToPerson = { person -> navController.navigate("${MainDestinations.TMDB_PERSON_ROUTE}/${person.id}") },

        fab = { isFabVisible, isBookmark, details ->
            ToggleBookmarkFab(isBookmark = isBookmark, isVisible = isFabVisible) {
                if (isBookmark) {
                    viewModel.removeBookmark(details.id)
                } else {
                    viewModel.addBookmark(getBookmarkedItem.invoke(details))
                }
            }
        },
    )
}

private val localVibrantColor =
    compositionLocalOf<Animatable<Color, AnimationVector4D>> { error("No vibrant color defined") }

@SuppressLint("RestrictedApi")
@Composable
fun <T : TMDbItemDetails, E : TMDbItem> DetailScreen(
    viewModel: BaseDetailViewModel<T, E>,
    navController: NavController,
    onImagesSelected: (List<TMDbImage>, Int) -> Unit,
    onTMDbItemSelected: (TMDbItem) -> Unit,
    onAllSimilarSelected: (Int) -> Unit,
    navigateToPerson: (person: Credit) -> Unit,
    fab: @Composable (MutableState<Boolean>, Boolean, TMDbItemDetails) -> Unit,
) {
    val isFabVisible = rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToastMessage -> {
                    Toast.makeText(
                        context,
                        context.getString(event.messageResId),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    val defaultTextColor = MaterialTheme.colorScheme.onSurface
    val vibrantColor = remember { Animatable(defaultTextColor) }

    Content(viewModel = viewModel) {
        LaunchedEffect(key1 = it.details.id) {
            viewModel.isBookmarked(it.details.id)
        }

        val nestedScrollConnection =
            remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        if (available.y < -1) {
                            isFabVisible.value = false
                        }
                        if (available.y > 1) {
                            isFabVisible.value = true
                        }
                        return Offset.Zero
                    }
                }
            }
        CompositionLocalProvider(
            localVibrantColor provides vibrantColor,
        ) {
            Scaffold(
                Modifier.nestedScroll(nestedScrollConnection),
                floatingActionButton = {
                    fab.invoke(
                        isFabVisible,
                        viewModel.isBookmarked.collectAsState().value,
                        it.details,
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
            ) { contentPadding ->
                ConstraintLayout(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                        .verticalScroll(rememberScrollState())
                        .padding(contentPadding),
                ) {


                    val (
                        appbar, backdrop, poster, title, originalTitle, genres, specs,
                        rateStars, tagline, overview,
                    ) = createRefs()
                    val (castSection, crewSection, imagesSection, similarSection, space) = createRefs()
                    val startGuideline = createGuidelineFromStart(Spacing.large_16)
                    val endGuideline = createGuidelineFromEnd(Spacing.large_16)

                    it.details.backdropPath?.let { backdropPath ->
                        Backdrop(
                            backdropUrl = backdropPath,
                            it.details.title,
                            Modifier.constrainAs(backdrop) {
                                top.linkTo(parent.top)
                            },
                        )
                    } ?: run {
                        val density = LocalDensity.current
                        val insets = WindowInsets.statusBars.getTop(density).toDp()
                        Spacer(
                            modifier =
                                Modifier.constrainAs(backdrop) {
                                    top.linkTo(parent.top, insets)
                                },
                        )
                    }

                    val posterWidth = 160.dp
                    AppBar(
                        homepage = it.details.homepage,
                        upPress = navController::navigateUp,
                        modifier =
                            Modifier
                                .requiredWidth(posterWidth * 2.2f)
                                .constrainAs(appbar) { centerTo(poster) }
                                .offset(y = Spacing.extraLarge_24),
                    )

                    it.details.posterPath?.let { posterPath ->
                        GetVibrantColorFromPoster(
                            posterPath,
                            localVibrantColor.current,
                        )
                        Poster(
                            it.details.posterPath,
                            it.details.title,
                            Modifier
                                .zIndex(17f)
                                .width(posterWidth)
                                .height(240.dp)
                                .constrainAs(poster) {
                                    it.details.backdropPath?.let {
                                        centerAround(backdrop.bottom)
                                    } ?: run {
                                        top.linkTo(backdrop.bottom)
                                    }
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } ?: run {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(poster) {
                                    top.linkTo(backdrop.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    Text(
                        text = it.details.title,
                        style =
                            MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 26.sp,
                                letterSpacing = 3.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                            ),
                        modifier =
                            Modifier
                                .padding(horizontal = Spacing.large_16)
                                .constrainAs(title) {
                                    top.linkTo(poster.bottom, 8.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                    )
                    if (it.details.title != it.details.originalTitle) {
                        Text(
                            text = "(${it.details.originalTitle})",
                            style =
                                MaterialTheme.typography.bodyMedium.copy(
                                    fontStyle = FontStyle.Italic,
                                    letterSpacing = 2.sp,
                                ),
                            modifier =
                                Modifier
                                    .padding(horizontal = Spacing.large_16)
                                    .constrainAs(originalTitle) {
                                        top.linkTo(title.bottom)
                                        linkTo(startGuideline, endGuideline)
                                    },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(originalTitle) {
                                    top.linkTo(title.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.details.genres.isNotEmpty()) {
                        GenreChips(
                            it.details.genres.take(4),
                            modifier =
                                Modifier.constrainAs(genres) {
                                    top.linkTo(originalTitle.bottom, 16.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(genres) {
                                    top.linkTo(originalTitle.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    TMDbItemFields(
                        it.details,
                        modifier =
                            Modifier.constrainAs(specs) {
                                top.linkTo(genres.bottom, 12.dp)
                                linkTo(startGuideline, endGuideline)
                            },
                    )

                    RateStars(
                        it.details.voteAverage,
                        modifier =
                            Modifier.constrainAs(rateStars) {
                                top.linkTo(specs.bottom, 12.dp)
                                linkTo(startGuideline, endGuideline)
                            },
                    )

                    if (it.details.tagline.isNotEmpty()) {
                        Text(
                            text = it.details.tagline,
                            color = localVibrantColor.current.value,
                            style =
                                MaterialTheme.typography.bodyLarge.copy(
                                    letterSpacing = 2.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                ),
                            modifier =
                                Modifier
                                    .padding(horizontal = Spacing.large_16)
                                    .constrainAs(tagline) {
                                        top.linkTo(rateStars.bottom, 12.dp)
                                    },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(tagline) {
                                    top.linkTo(rateStars.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.details.overview.isNotEmpty()) {
                        Text(
                            text = it.details.overview,
                            style =
                                MaterialTheme.typography.bodyMedium.copy(
                                    letterSpacing = 2.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = FontFamily.SansSerif,
                                ),
                            modifier =
                                Modifier
                                    .padding(horizontal = Spacing.large_16)
                                    .constrainAs(overview) {
                                        top.linkTo(tagline.bottom, 8.dp)
                                        linkTo(startGuideline, endGuideline)
                                    },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(overview) {
                                    top.linkTo(tagline.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.cast.isNotEmpty()) {
                        TMDbDetailItemSection(
                            items = it.cast,
                            headerResId = R.string.cast,
                            itemContent = { item, _ ->
                                PersonCard(
                                    item.asActor(),
                                    navigateToPerson,
                                    Modifier.width(140.dp),
                                )
                            },
                            onSeeAllClicked = { cast ->
                                navController.navigate(MainDestinations.TMDB_CAST_ROUTE)
                            },
                            modifier =
                                Modifier.constrainAs(castSection) {
                                    top.linkTo(overview.bottom, 16.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(castSection) {
                                    top.linkTo(overview.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.crew.isNotEmpty()) {
                        TMDbDetailItemSection(
                            items = it.crew,
                            headerResId = R.string.crew,
                            itemContent = { item, _ ->
                                PersonCard(
                                    item.asActor(),
                                    navigateToPerson,
                                    Modifier.width(140.dp),
                                )
                            },
                            onSeeAllClicked = { crew ->
                                navController.navigate(MainDestinations.TMDB_CREW_ROUTE)
                            },
                            modifier =
                                Modifier.constrainAs(crewSection) {
                                    top.linkTo(castSection.bottom, 16.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(crewSection) {
                                    top.linkTo(castSection.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.images.isNotEmpty()) {
                        TMDbDetailItemSection(
                            items = it.images,
                            headerResId = R.string.images,
                            onSeeAllClicked = { images -> onImagesSelected.invoke(images, 0) },
                            itemContent = { item, index ->
                                ImageSection(
                                    item,
                                ) { onImagesSelected.invoke(it.images, index) }
                            },
                            modifier =
                                Modifier.constrainAs(imagesSection) {
                                    top.linkTo(crewSection.bottom, 16.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(imagesSection) {
                                    top.linkTo(crewSection.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    if (it.similarItems.isNotEmpty()) {
                        TMDbDetailItemSection(
                            items = it.similarItems,
                            headerResId = R.string.similar,
                            onSeeAllClicked = { _ -> onAllSimilarSelected.invoke(it.details.id) },
                            itemContent = { item, _ ->
                                TMDbCard(item, onTMDbItemSelected)
                            },
                            headerText = stringResource(R.string.see_all_items),
                            modifier =
                                Modifier.constrainAs(similarSection) {
                                    top.linkTo(imagesSection.bottom, 16.dp)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    } else {
                        Spacer(
                            modifier =
                                Modifier.constrainAs(similarSection) {
                                    top.linkTo(imagesSection.bottom)
                                    linkTo(startGuideline, endGuideline)
                                },
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier
                                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                                .constrainAs(space) {
                                    top.linkTo(similarSection.bottom)
                                },
                    )
                }
            }
        }
    }
}

@Composable
fun GetVibrantColorFromPoster(posterUrl: String, color: Animatable<Color, AnimationVector4D>) {
    val context = LocalContext.current
    LaunchedEffect(posterUrl) {
        val loader = ImageLoader(context)
        val request =
            ImageRequest
                .Builder(context)
                .data(posterUrl)
                .size(128, 128)
                .allowHardware(false)
                .build()

        val bitmap =
            (loader.execute(request) as? SuccessResult)?.drawable?.toBitmap()
                ?: return@LaunchedEffect
        val vibrantColor = Palette.from(bitmap).generate().getVibrantColor(color.value.toArgb())
        color.animateTo(Color(vibrantColor), tween(400))
    }
}

@Composable
private fun Backdrop(backdropUrl: String, tmdbItemName: String, modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(Spacing.large_16),
        shape = BottomArcShape(arcHeight = 120.dpToPx()),
        modifier = modifier
            .height(360.dp)
            .background(
                //localVibrantColor.current.value.copy(alpha = 0.1f)
                MaterialTheme.colorScheme.surface
            ),
    ) {
        AsyncImage(
            model =
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = backdropUrl)
                    .crossfade(1500)
                    .build(),
            contentScale = ContentScale.FillHeight,
            contentDescription = tmdbItemName,
            modifier = modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun AppBar(modifier: Modifier, homepage: String?, upPress: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
    ) {
        val vibrantColor = localVibrantColor.current.value
        val scaleModifier = Modifier.scale(1.1f)
        IconButton(onClick = { upPress.invoke() }) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = vibrantColor,
                modifier = scaleModifier,
            )
        }
        if (!homepage.isNullOrBlank()) {
            val context = LocalContext.current
            IconButton(onClick = { homepage.openInChromeCustomTab(context, vibrantColor) }) {
                Icon(
                    Icons.AutoMirrored.Rounded.OpenInNew,
                    contentDescription = stringResource(id = R.string.open),
                    tint = vibrantColor,
                    modifier = scaleModifier,
                )
            }
        }
    }
}

private val springAnimation =
    spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow,
        visibilityThreshold = 0.001f,
    )

@Composable
fun Poster(posterUrl: String?, tmdbItemName: String, modifier: Modifier = Modifier) {
    val isScaled = remember { mutableStateOf(false) }
    val scale =
        animateFloatAsState(
            targetValue = if (isScaled.value) 2.2f else 1f,
            animationSpec = springAnimation,
            label = "PosterScale",
        ).value

    Card(
        elevation = CardDefaults.cardElevation(Spacing.extraLarge_24),
        shape = RoundedCornerShape(Spacing.medium_8),
        modifier = modifier
            .scale(scale)
            .clickable { isScaled.value = !isScaled.value },

        ) {
        AsyncImage(
            model = posterUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = tmdbItemName,
            contentScale = ContentScale.FillHeight,
            placeholder = rememberVectorPainter(Icons.Default.Image),
            error = rememberVectorPainter(Icons.Default.BrokenImage)
        )
    }
}

@Composable
private fun GenreChips(genres: List<Genre>, modifier: Modifier) {
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Spacing.large_16),
    ) {
        genres.forEachIndexed { index, genre ->
            Text(
                text = genre.name.orEmpty(),
                style = MaterialTheme.typography.bodyMedium.copy(letterSpacing = 2.sp),
                modifier =
                    Modifier
                        .border(1.25.dp, localVibrantColor.current.value, RoundedCornerShape(50))
                        .padding(horizontal = Spacing.smallMedium_6, vertical = 3.dp),
            )

            if (index != genres.lastIndex) {
                Spacer(modifier = Modifier.width(Spacing.medium_8))
            }
        }
    }
}

@Composable
fun TMDbItemFields(tmdbItemDetails: TMDbItemDetails, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier) {
        val context = LocalContext.current
        tmdbItemDetails.releaseDate?.let {
            TMDbItemField(
                context.getString(R.string.release_date),
                it,
            )
        }
        TMDbItemField(
            context.getString(R.string.vote_average),
            tmdbItemDetails.voteAverage.toDisplayableNumber().formatted.toString(),
        )
        TMDbItemField(context.getString(R.string.votes), tmdbItemDetails.voteCount.toString())
    }
}

@Composable
fun TMDbItemField(name: String, value: String) {
    Column {
        Text(
            text = name,
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 13.sp,
                    letterSpacing = 1.sp,
                ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Spacing.small_4),
        )
    }
}

@Composable
private fun RateStars(voteAverage: Double, modifier: Modifier) {
    Row(modifier.padding(start = Spacing.small_4)) {
        val maxVote = 10
        val starCount = 5
        repeat(starCount) { starIndex ->
            val voteStarCount = voteAverage / (maxVote / starCount)
            val asset =
                when {
                    voteStarCount >= starIndex + 1 -> Icons.Filled.Star
                    voteStarCount in starIndex.toDouble()..(starIndex + 1).toDouble() ->
                        Icons.AutoMirrored.Filled.StarHalf

                    else -> Icons.Filled.StarOutline
                }
            Icon(
                imageVector = asset,
                contentDescription = null,
                tint = localVibrantColor.current.value,
            )
            Spacer(modifier = Modifier.width(Spacing.small_4))
        }
    }
}

@Composable
private fun <T : Any> TMDbDetailItemSection(
    items: List<T>,
    @StringRes headerResId: Int,
    onSeeAllClicked: (List<T>) -> Unit,
    itemContent: @Composable (T, Int) -> Unit,
    headerText: String? = null,
    modifier: Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (items.isNotEmpty()) {
            SectionHeader(headerResId, items, onSeeAllClicked, headerText)
            LazyRow(
                modifier = Modifier.testTag(LocalContext.current.getString(headerResId)),
                contentPadding = PaddingValues(Spacing.large_16),
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        itemContent(items[index], index)
                        Spacer(modifier = Modifier.width(Spacing.large_16))
                    },
                )
            }
        }
    }
}

@Composable
private fun <T : Any> SectionHeader(
    @StringRes headerResId: Int,
    items: List<T>,
    onAllSelected: (List<T>) -> Unit,
    headerText: String?,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.large_16),
    ) {
        Text(
            text = stringResource(headerResId),
            color = localVibrantColor.current.value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.padding(Spacing.small_4)
                    .clickable {
                        onAllSelected.invoke(items)
                    },
        ) {
            Text(
                text = headerText ?: stringResource(R.string.see_all, items.size),
                color = localVibrantColor.current.value,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(end = Spacing.small_4),
            )
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = headerText,
                tint = localVibrantColor.current.value,
            )
        }
    }
}

@Composable
fun ImageSection(image: TMDbImage, onImageSelected: () -> Unit) {
    Card(
        Modifier
            .width(240.dp)
            .height(160.dp)
            .clickable { onImageSelected.invoke() },
        shape = RoundedCornerShape(Spacing.mediumLarge_12),
        elevation = CardDefaults.cardElevation(Spacing.medium_8),
    ) {
        val request =
            ImageRequest
                .Builder(LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build()
        val painter =
            rememberAsyncImagePainter(
                model = request,
                placeholder = rememberVectorPainter(Icons.Default.Image),
                error = rememberVectorPainter(Icons.Default.BrokenImage),
            )
        val (colorFilter, contentScale) =
            when (painter.state) {
                is AsyncImagePainter.State.Error,
                is AsyncImagePainter.State.Loading ->
                    ColorFilter.tint(MaterialTheme.colorScheme.surface) to ContentScale.Fit

                else -> null to ContentScale.Crop
            }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            colorFilter = colorFilter,
            contentDescription = stringResource(id = R.string.poster_content_description),
            contentScale = contentScale,
        )
    }
}

@Composable
fun ToggleBookmarkFab(isBookmark: Boolean, isVisible: MutableState<Boolean>, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = { it * 2 }),
        exit = slideOutVertically(targetOffsetY = { it * 2 }),
    ) {

        FloatingActionButton(
            shape = CircleShape,
            onClick = onClick,
        ) {
            Icon(
                imageVector = if (isBookmark) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },

                tint = if (isBookmark) {
                    Color.White
                } else {
                    Color.White
                },

                contentDescription =
                    if (isBookmark) {
                        stringResource(R.string.favorite)
                    } else {
                        stringResource(
                            R.string.un_favorite,
                        )
                    },
            )
        }
    }
}

class BottomArcShape(private val arcHeight: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path =
            Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width, size.height)
                val arcOffset = arcHeight / 10
                val rect =
                    Rect(
                        left = 0f - arcOffset,
                        top = size.height - arcHeight,
                        right = size.width + arcOffset,
                        bottom = size.height,
                    )
                arcTo(rect, 0f, 180f, false)
                lineTo(0f, 0f)
                close()
            }
        return Outline.Generic(path)
    }
}