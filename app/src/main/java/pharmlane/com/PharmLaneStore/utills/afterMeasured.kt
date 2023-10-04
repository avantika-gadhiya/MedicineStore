package pharmlane.com.PharmLaneStore.utills

import android.util.Log
import android.view.View
import android.view.ViewTreeObserver

inline fun  View.afterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            Log.e("HelloTags", "onGlobalLayout")
            if (measuredWidth > 0 && measuredHeight > 0) {
                Log.e("HelloTags", "onGlobalLayout IFF")
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}