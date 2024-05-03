package mobi.foo.dokkapracticing.domain.models.main

/**
 * This intent class is responsible to specify
 * our events intentions
 *
 * @author Huzayfa El Hussein
 */

sealed interface MainIntent {

    /**
     * RefreshLaunches object represents an intent to refresh the list of launches.
     */
    data object RefreshLaunches : MainIntent

    /**
     * OpenLaunchArticle data class represents an intent to open a specific launch article.
     * @param article The URL of the launch article to be opened.
     */
    data class OpenLaunchArticle(val article: String) : MainIntent
}
/**
 * implements the intent part of the MVI, bundle this  specific intention the user could have in a single screen
 */