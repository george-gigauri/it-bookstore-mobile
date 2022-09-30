package ge.herpi.itbookstore.common.extension

import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(message ?: "", length)
}

fun Fragment.color(resId: Int): Int {
    return requireActivity().color(resId)
}

fun Fragment.drawable(resId: Int): Drawable? {
    return requireActivity().drawable(resId)
}