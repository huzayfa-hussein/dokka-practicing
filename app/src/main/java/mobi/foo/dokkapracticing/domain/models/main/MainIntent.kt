package mobi.foo.dokkapracticing.domain.models.main

/**
 * This intent class is responsible to specify
 * our events intentions
 *
 * @author Huzayfa El Hussein
 */

sealed interface MainIntent {
    data object RefreshLaunches : MainIntent
    data class OpenLaunchArticle(val article: String) : MainIntent
}
/**
 * implements the intent part of the MVI, bundle this  specific intention the user could have in a single screen
 */