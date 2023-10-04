package pharmlane.com.PharmLaneStore.activities

import android.Manifest
import android.annotation.TargetApi
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.interfaces.RetrofitInterface
import pharmlane.com.PharmLaneStore.model.Register
import pharmlane.com.PharmLaneStore.response.StoreDetailResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant
import pharmlane.com.PharmLaneStore.utills.InternalStorageContentProvider
import pharmlane.com.PharmLaneStore.utills.ScaleFile
import com.bumptech.glide.Glide
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_gst_number.*
import kotlinx.android.synthetic.main.activity_update_store_photo.*
import kotlinx.android.synthetic.main.activity_update_store_photo.img_upload_picture
import kotlinx.android.synthetic.main.activity_upload_photo.*
import kotlinx.android.synthetic.main.activity_upload_photo.img_back
import org.json.JSONObject
import pharmlane.com.PharmLaneStore.BuildConfig
import pharmlane.com.PharmLaneStore.utills.Utils
import retrofit2.Call
import retrofit2.Callback
import java.io.File
import java.io.IOException

class UpdateStorePhotoActivity : AppCompatActivity(), View.OnClickListener {

    private var mFileTemp: Uri? = null
    private val SELECT_FILE = 1
    var bitmap: Bitmap? = null
    var path = ""
    private val REQUEST_CAMERA = 0
    val REQUEST_CODE_TAKE_PICTURE = 2
    val REQUEST_CODE_GALLERY = 1
    val INITIAL_GALLERY = arrayOf<String>(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE

    )
    private var isDrugLic = false
    private var owner_name = ""
    private var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_store_photo)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }//  set status text dark


        btn_save.setOnClickListener(this)
        img_back.setOnClickListener(this)
        img_upload_picture.setOnClickListener(this)

        edt_store_owner_name.setText(AppConstant.getPreferenceText(AppConstant.OWNER_NAME))
        edt_number.setText(AppConstant.getPreferenceText(AppConstant.OWNER_PHONE))

        Glide.with(applicationContext)  //2
                .load(AppConstant.getPreferenceText(AppConstant.STORE_PHOTO)) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_launcher_background) //5
                .error(R.drawable.ic_launcher_background) //6
                .fallback(R.drawable.ic_launcher_background) //7
                .placeholder(R.drawable.ic_launcher_background) //5
                .into(img_upload_picture) //8

    }


    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.img_back -> {
                finish()
            }
            R.id.btn_save -> {

                owner_name = edt_store_owner_name.text.toString().trim()
                number = edt_number.text.toString().trim()

                if (owner_name.length < 1) {
                    Toast.makeText(this, "Enter Owner Number", Toast.LENGTH_LONG).show()
                } else if (number.length < 10 && number.length > 10) {
                    Toast.makeText(this, "Enter Valid Number", Toast.LENGTH_LONG).show()
                } else {
                    update_storeDetail()
                }

            }
            R.id.img_upload_picture -> {
                choice()
                selectFromGallery()
            }
        }
    }

    fun choice() {
        var fileTemporary: File? = null
        try {
            fileTemporary = Utils.createTempImageFile(this)
        } catch (exception: IOException) {
            Log.d(javaClass.simpleName + " openCamera", exception.toString())
        }
        mFileTemp = FileProvider.getUriForFile(this,
            BuildConfig.APPLICATION_ID.toString() + ".provider", fileTemporary!!)
    }

    private fun openCamera() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    mFileTemp
            )
            intent.putExtra("return-data", true)
            startActivityForResult(intent, REQUEST_CAMERA)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun selectFromGallery() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!canAccessCamera()) {
                requestPermissions(INITIAL_GALLERY, 1340)
            } else {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY)
            }
        } else {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY)
        }
    }

    private fun canAccessCamera(): Boolean {
        return hasPermission(Manifest.permission.CAMERA) && hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) && hasPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasPermission(perm: String): Boolean {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm)
    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1340) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY)
            } else {
                Toast.makeText(this, "Premission Required", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    intent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            mFileTemp
                    )
                    intent.putExtra("return-data", true)
                    startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, R.string.permission_required, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                val selectedImageUri = data?.data
                CropImage.activity(selectedImageUri)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setFixAspectRatio(true)
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setAspectRatio(3, 4)
                    .setInitialCropWindowPaddingRatio(0f)
                        .setActivityTitle(getResources().getString(R.string.my_crop))
                        .start(this)
            }
            if (requestCode == REQUEST_CAMERA) {
                val selectedImageUri = mFileTemp
                CropImage.activity(selectedImageUri)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setFixAspectRatio(true)
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setAspectRatio(3, 4)
                    .setInitialCropWindowPaddingRatio(0f)
                        .setActivityTitle(getResources().getString(R.string.my_crop))
                        .start(this)
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                val result = CropImage.getActivityResult(data)
                val resultUri = result.getUri()
                //imgProfile.setImageURI(resultUri)
                path = resultUri.getPath() ?: ""
                if (resultUri.getPath() == null) {
                    return
                } else {
//                    bitmap = BitmapFactory.decodeFile(path)
                    bitmap = ScaleFile.compressImage(path, this)
                    storePhoto = AppConstant.convert(bitmap!!)
                    img_upload_picture.setImageBitmap(bitmap)
                }
            }
        }
    }

    // Method to resize a bitmap programmatically
    private fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {

        return Bitmap.createScaledBitmap(
                bitmap,
                width,
                height,
                false
        )
    }

    fun determineScreenDensityCode(): String {
        return when (resources.displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> "ldpi"
            DisplayMetrics.DENSITY_MEDIUM -> "mdpi"
            DisplayMetrics.DENSITY_HIGH -> "hdpi"
            DisplayMetrics.DENSITY_XHIGH, DisplayMetrics.DENSITY_280 -> "xhdpi"
            DisplayMetrics.DENSITY_XXHIGH, DisplayMetrics.DENSITY_360, DisplayMetrics.DENSITY_400, DisplayMetrics.DENSITY_420 -> "xxhdpi"
            DisplayMetrics.DENSITY_XXXHIGH, DisplayMetrics.DENSITY_560 -> "xxxhdpi"
            else -> "Unknown code ${resources.displayMetrics.densityDpi}"
        }
    }


    companion object {
        var storePhoto = ""
    }


    private fun update_storeDetail() {
        progressBar_store_photo.visibility = View.VISIBLE

        val register = Register()
        register.store_id = AppConstant.getPreferenceText(AppConstant.STORE_ID)
        register.store_photo = storePhoto
        register.owner_name = owner_name
        register.phone = number

        val apiService =
                AppConstant.getClient()!!.create<RetrofitInterface>(RetrofitInterface::class.java)
        val call = apiService.update_store_detail(register)

        call.enqueue(object : Callback<StoreDetailResponse> {
            override fun onResponse(
                    call: Call<StoreDetailResponse>,
                    response: retrofit2.Response<StoreDetailResponse>
            ) {
                progressBar_store_photo.visibility = View.GONE
                if (response.isSuccessful) {

                    finish()

                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(
                                this@UpdateStorePhotoActivity,
                                "" + jObjError.getString("message"),
                                Toast.LENGTH_SHORT
                        ).show()


                    } catch (e: Exception) {
                        Toast.makeText(this@UpdateStorePhotoActivity, e.message, Toast.LENGTH_LONG)
                                .show()
                    }

                }
            }


            override fun onFailure(call: Call<StoreDetailResponse>, t: Throwable) {
                // progressBarHandler.hide();
                progressBar_store_photo.visibility = View.GONE
            }
        })
    }
}