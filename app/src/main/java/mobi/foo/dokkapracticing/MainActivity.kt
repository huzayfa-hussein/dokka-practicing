package mobi.foo.dokkapracticing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import mobi.foo.dokkapracticing.common.extensions.openBrowser
import mobi.foo.dokkapracticing.ui.screens.MainScreen
import mobi.foo.dokkapracticing.ui.theme.DokkaPracticingTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DokkaPracticingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(modifier = Modifier) {
                        openBrowser(it)
                    }
                }
            }
        }
    }
}