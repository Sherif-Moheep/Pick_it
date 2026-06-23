package com.hitech.pickit.media.presentation.screens.credit

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hitech.pickit.R
import com.hitech.pickit.media.domain.model.Person
import com.hitech.pickit.core.presentation.theme.PickItTheme
import com.hitech.pickit.media.presentation.models.Content
import com.hitech.pickit.media.presentation.utili.Spacing
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 36.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = Spacing.huge_56
private val MinImageOffset = 12.dp
private val MediumTitleOffset = ImageOverlap + MinTitleOffset
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun PersonScreen(
    upPress: () -> Unit,
    viewModel: PersonViewModel
) {
    val titleHeight = remember { mutableStateOf(0.dp) }
    Content(viewModel = viewModel) { person ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Log.d("IMAGE_PERSON_DEBUG", "Trying to load: ${person.profilePath}")
            val scroll = rememberScrollState(0)
            Box(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            ) {

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(person.profilePath)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(radius = 12.dp),

                    error = {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                )
            }
            Body(person.biography, titleHeight, scroll)
            Title(person, titleHeight) { scroll.value }
            ActorImage(person.profilePath) { scroll.value }
            Up(upPress)
        }
    }
}

@Composable
fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier =
            Modifier
                .statusBarsPadding()
                .padding(horizontal = Spacing.large_16, vertical = 10.dp)
                .size(36.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.32f),
                    shape = CircleShape,
                ),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = stringResource(R.string.back),
        )
    }
}

@Composable
fun Body(biography: String, titleHeight: MutableState<Dp>, scroll: ScrollState) {
    Column {
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(MinTitleOffset),
        )
        Column(
            modifier = Modifier.verticalScroll(scroll),
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(96.dp))
                    Spacer(Modifier.height(titleHeight.value))

                    if (biography.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.biography),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = HzPadding.padding(top = Spacing.extraLarge_24),
                        )
                        Spacer(modifier = Modifier.height(Spacing.large_16))
                        Text(
                            text = biography,
                            style =
                                MaterialTheme.typography.bodyMedium.copy(
                                    letterSpacing = 2.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = FontFamily.Default,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            modifier = HzPadding,
                        )
                    }
                    Spacer(
                        modifier =
                            Modifier
                                .padding(bottom = BottomBarHeight)
                                .navigationBarsPadding()
                                .height(Spacing.medium_8),
                    )
                }
            }
        }
    }
}

@Composable
fun Title(person: Person, titleHeight: MutableState<Dp>, scrollProvider: () -> Int) {
    val localDestiny = LocalDensity.current
    val maxOffset =
        with(localDestiny) {
            (if (person.profilePath == null) MediumTitleOffset else MaxTitleOffset).toPx()
        }
    val minOffset = with(localDestiny) { MinTitleOffset.toPx() }
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier =
            Modifier
                .onGloballyPositioned { coordinates ->
                    with(localDestiny) { titleHeight.value = coordinates.size.height.toDp() }
                }
                .heightIn(min = titleHeight.value)
                .statusBarsPadding()
                .offset {
                    val scroll = scrollProvider()
                    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                    IntOffset(x = 0, y = offset.toInt())
                }
                .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Spacer(modifier = Modifier.height(Spacing.large_16))
        Text(
            text = person.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = HzPadding,
        )
        Spacer(modifier = Modifier.height(Spacing.extraSmall_2))
        person.placeOfBirth?.let {
            Text(
                text = stringResource(id = R.string.from, it),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = HzPadding,
            )
            Spacer(modifier = Modifier.height(Spacing.small_4))
        }
        person.birthDay?.let {
            Text(
                text = stringResource(id = R.string.born, it),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = HzPadding,
            )
            Spacer(modifier = Modifier.height(Spacing.small_4))
        }
        person.deathDay?.let {
            Text(
                text = stringResource(id = R.string.death, it),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = HzPadding,
            )
            Spacer(modifier = Modifier.height(Spacing.small_4))
        }
        Spacer(modifier = Modifier.height(Spacing.mediumLarge_12))
        TMDbDivider()
    }
}

@Composable
fun TMDbDivider() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ActorImage(
    imageUrl: String?,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),

                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),

                loading = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        LoadingIndicator(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(250.dp)
                        )
                    }
                },

                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Placeholder",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(CollapsedImageSize / 2)
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth).toDp()
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth).toDp()
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction).roundToPx()
        val imagePlaceable =
            measurables[0].measure(
                Constraints.fixed(
                    imageWidth,
                    imageWidth,
                ),
            )

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX =
            lerp(
                ((constraints.maxWidth - imageWidth) / 2).toDp(),
                (constraints.maxWidth - imageWidth).toDp(),
                collapseFraction,
            ).roundToPx()
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth,
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}


val dummyPerson = Person(
    name = "Tom Hanks",
    profilePath = "https://image.tmdb.org/t/p/w500/dBOrm29cr7NUrjiDQMTtrTyDpfy.jpg",
    placeOfBirth = "Concord, California",
    birthDay = "July 9, 1956",
    deathDay = null,
    biography = "Thomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural iThomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon.Thomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon.Thomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon.Thomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is widely regarded as an American cultural icon.con.",
    id = 1
)

@PreviewLightDark
@Composable
fun PersonScreenPreview() {
    PickItTheme {

        val titleHeight = remember { mutableStateOf(0.dp) }
        val person = dummyPerson

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            val scroll = rememberScrollState(0)

            Box(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            ) {

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(person.profilePath)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(radius = 12.dp),

                    error = {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                )
            }


                Body(person.biography, titleHeight, scroll)
                Title(person, titleHeight) { scroll.value }

                ActorImage(person.profilePath) { scroll.value }

                Up(upPress = {})


        }
    }
}
