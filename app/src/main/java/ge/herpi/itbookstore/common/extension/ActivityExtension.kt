package ge.herpi.itbookstore.common.extension

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Activity.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    if (!message.isNullOrEmpty())
        Toast.makeText(this, message ?: "", length).show()
}

fun Activity.color(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun Activity.drawable(resId: Int): Drawable? {
    return ContextCompat.getDrawable(this, resId)
}