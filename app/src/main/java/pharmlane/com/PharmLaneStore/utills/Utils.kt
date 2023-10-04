package pharmlane.com.PharmLaneStore.utills

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import java.io.File
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {

        fun startNewActivity(context: Context, packageName: String) {
            var intent = context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent == null) {
                // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=$packageName")
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        fun GettingMiliSeconds(Date: String?): Long? {
            var timeInMilliseconds: Long = 0
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
            try {
                val mDate = sdf.parse(Date)
                timeInMilliseconds = mDate.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeInMilliseconds
        }


        fun changeBitmapColor(sourceBitmap: Bitmap, image: ImageView, color: Int) {

            val paint = Paint()
            paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            val bitmapResult =
                    Bitmap.createBitmap(
                            sourceBitmap.width,
                            sourceBitmap.height,
                            Bitmap.Config.ARGB_8888
                    )

            val canvas = Canvas(bitmapResult)
            canvas.drawBitmap(sourceBitmap, 0F, 0F, paint)

            image.setImageBitmap(bitmapResult)
        }

        fun downloadImageNew(filename: String, downloadUrlOfImage: String, mActivity: Context): Boolean {
            try {
                val dm: DownloadManager = mActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val downloadUri = Uri.parse(downloadUrlOfImage)
                val request: DownloadManager.Request = DownloadManager.Request(downloadUri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle(filename)
                        .setMimeType("image/*") // Your file type. You can use this code to download other file types also.
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator.toString() + filename)
                dm.enqueue(request)
                return true
//                Toast.makeText(mActivity, msg + " saved in your Pictures folder.", Toast.LENGTH_SHORT).show()
            } catch (e: java.lang.Exception) {
//                Toast.makeText(mActivity, msg + " download failed.", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        @Throws(IOException::class)
        fun createTempImageFile(mContext: Context): File? {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            val storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",  /* suffix */
                storageDir /* directory */
            )
        }
    }
}