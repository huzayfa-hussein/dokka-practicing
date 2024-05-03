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

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied in
     * [onSaveInstanceState(Bundle)].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DokkaPracticingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display the main screen
                    MainScreen(modifier = Modifier) {
                        // Open browser when item is clicked
                        openBrowser(it)
                    }
                }
            }
        }
    }
}