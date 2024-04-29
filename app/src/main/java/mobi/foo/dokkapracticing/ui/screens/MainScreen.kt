package mobi.foo.dokkapracticing.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import mobi.foo.dokkapracticing.R
import mobi.foo.dokkapracticing.domain.models.main.CompanyModel
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    Column(modifier = modifier) {
        TopBar()
        MainSection(title = R.string.company, modifier = modifier) {
            AboutCompanyDetailText()
        }
        MainSection(title = R.string.launches, modifier = modifier) {
            LaunchesList(
                modifier = modifier,
                launches = getLaunches()
            )
            LaunchView(
                modifier = modifier,
                launchModel = LaunchModel(
                    image = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
                    missionName = "FalconSat",
                    date = "25 Mar 2007 at 06:23 am",
                    rocketName = "Falcon 1 / Merlin A",
                    days = 6428,
                    isLaunched = true
                )
            )
        }
    }
}

fun getLaunches(): List<LaunchModel> {
    val launchData = LaunchModel(
        image = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
        missionName = "FalconSat",
        date = "25 Mar 2007 at 06:23 am",
        rocketName = "Falcon 1 / Merlin A",
        days = 6428,
        isLaunched = true
    )
    return listOf(launchData, launchData, launchData, launchData)
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
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

        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.spaceX),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
        )
    }
}

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

@Composable
fun LaunchesList(
    modifier: Modifier = Modifier,
    launches: List<LaunchModel> = listOf()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        items(launches) { launchModel ->
            LaunchView(launchModel = launchModel, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchView(
    modifier: Modifier = Modifier,
    launchModel: LaunchModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)

    ) {
        Divider(thickness = 2.dp)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            GlideImage(
//                model = launchModel.image,
//                contentDescription = null,
//                modifier = modifier
//                    .width(50.dp)
//                    .height(50.dp),
//                contentScale = ContentScale.Crop,
//            )

            Image(
                painter = painterResource(id = R.drawable.falcon),
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

@Preview
@Composable
fun HomePreview() {
    MainScreen(modifier = Modifier)
}