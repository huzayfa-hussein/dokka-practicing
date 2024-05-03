package mobi.foo.dokkapracticing.common.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openBrowser(url: String?) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}