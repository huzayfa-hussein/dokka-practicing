package mobi.foo.dokkapracticing.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import mobi.foo.dokkapracticing.R
import mobi.foo.dokkapracticing.domain.models.main.CompanyModel
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel
import mobi.foo.dokkapracticing.domain.models.main.MainIntent
import mobi.foo.dokkapracticing.domain.models.main.MainState

/**
 * MainScreen is a Composable function responsible for displaying the main screen of the application.
 * It takes the [modifier], [mainViewModel], and [onExternalBrowserRequested] as parameters.
 * It observes the UI state from [mainViewModel] and displays the appropriate content accordingly.
 * When an external browser is requested, it invokes [onExternalBrowserRequested].
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel(),
    onExternalBrowserRequested: (url: String) -> Unit
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    MainScreenContent(
        modifier = modifier,
        uiState = uiState,
        onIntent = { intent ->
            when (intent) {
                is MainIntent.OpenLaunchArticle -> {
                    onExternalBrowserRequested.invoke(intent.article)
                }

                else -> Unit
            }
            mainViewModel.onIntent(intent)
        }
    )
}

/**
 * MainScreenContent is a Composable function responsible for displaying the content of the main screen.
 * It takes the [modifier], [uiState], and [onIntent] as parameters.
 * It displays different sections of the main screen based on the UI state.
 */
@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    uiState: MainState = MainState(),
    onIntent: (MainIntent) -> Unit = {}
) {
    Column(modifier = modifier) {
        // Displaying the top bar with refresh button
        TopBar(modifier = modifier) {
            // Triggering RefreshLaunches intent when refresh button is clicked
            onIntent(MainIntent.RefreshLaunches)
        }
        // Checking if UI is in loading state
        if (uiState.isLoading) {
            // Displaying loading indicator
            CircularProgressIndicator()
        } else {
            // Displaying About Company section
            MainSection(title = R.string.company, modifier = modifier) {
                AboutCompanyDetailText(
                    modifier = modifier,
                    companyModel = uiState.companyModel
                )
            }
            // Displaying Launches section
            MainSection(title = R.string.launches, modifier = modifier) {
                LaunchesList(
                    modifier = modifier,
                    launches = uiState.launches
                ) { article ->
                    // Triggering OpenLaunchArticle intent when a launch item is clicked
                    onIntent(MainIntent.OpenLaunchArticle(article))
                }
            }
        }
    }
}

/**
 * MainSection is a Composable function responsible for displaying a section in the main screen.
 * It takes the [title] resource ID, [modifier], and [content] as parameters.
 */
@Composable
fun MainSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        TitleView(title = title, modifier = modifier)
        content()
    }
}


/**
 * TopBar is a Composable function responsible for displaying the top bar of the application.
 * It contains a refresh button, the app title, and a search icon.
 * It takes the [modifier] and [onRefreshButtonClicked] as parameters.
 * The [onRefreshButtonClicked] callback is triggered when the refresh button is clicked.
 */
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onRefreshButtonClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
    ) {

        IconButton(onClick = onRefreshButtonClicked) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,

                )
        }

        Text(
            text = stringResource(id = R.string.spaceX),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
    }
}

/**
 * TitleView is a Composable function responsible for displaying a title view.
 * It takes the [modifier] and [title] resource ID as parameters.
 */
@Composable
fun TitleView(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colorScheme.surfaceVariant,
            style = MaterialTheme.typography.titleSmall
        )

    }
}

/**
 * LaunchesList is a Composable function responsible for displaying a list of launches.
 * It takes the [modifier], [launches], and [onLaunchSelected] callback as parameters.
 */
@Composable
fun LaunchesList(
    modifier: Modifier = Modifier,
    launches: List<LaunchModel> = listOf(),
    onLaunchSelected: (url: String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        items(launches) { launchModel ->
            LaunchView(
                launchModel = launchModel,
                modifier = modifier,
                onLaunchSelected = onLaunchSelected
            )
        }
    }
}

/**
 * LaunchView is a Composable function responsible for displaying a single launch item.
 * It takes the [modifier], [launchModel], and [onLaunchSelected] callback as parameters.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchView(
    modifier: Modifier = Modifier,
    launchModel: LaunchModel,
    onLaunchSelected: (url: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)
            .clickable(onClick = {
                onLaunchSelected.invoke(launchModel.articleLink)
            })

    ) {
        Divider(thickness = 2.dp)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                model = launchModel.image,
                contentDescription = null,
                modifier = modifier
                    .width(35.dp)
                    .height(35.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = modifier
                    .padding(start = 8.dp)
            ) {
                LaunchRow(label = "Mission", value = launchModel.missionName)
                LaunchRow(label = "Date/time", value = launchModel.date)
                LaunchRow(label = "Rocket", value = launchModel.rocketName)
                LaunchRow(label = "Days Since now", value = launchModel.days.toString())
            }

            Icon(
                imageVector =
                if (launchModel.isLaunched) Icons.Default.Check else Icons.Default.Close,
                contentDescription = null
            )
        }

    }

}

/**
 * LaunchRow is a Composable function responsible for displaying a row in the launch item.
 * It takes the [modifier], [label], and [value] as parameters.
 */
@Composable
fun LaunchRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(0.4f),
            text = "$label:",
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(0.6f),
            text = value,
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * AboutCompanyDetailText is a Composable function responsible for displaying company details.
 * It takes the [modifier] and [companyModel] as parameters.
 */
@Composable
fun AboutCompanyDetailText(
    modifier: Modifier = Modifier,
    companyModel: CompanyModel = CompanyModel()
) {
    val paragraph = stringResource(
        id = R.string.company_detail_paragraph,
        companyModel.founderName,
        companyModel.foundedYear,
        companyModel.numberOfEmployees,
        companyModel.numberOfLaunchSites,
        companyModel.companyValue.toString()
    )

    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)
            .padding(12.dp),
        text = paragraph,
        color = MaterialTheme.colorScheme.background,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun HomePreview() {
    MainScreen(modifier = Modifier) {

    }
}