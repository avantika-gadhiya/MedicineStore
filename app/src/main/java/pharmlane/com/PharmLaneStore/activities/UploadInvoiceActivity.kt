package pharmlane.com.PharmLaneStore.activities

import android.Manifest
import android.annotation.TargetApi
import androidx.appcompat.app.AppCompatActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.CustomOrderDetailActivity.Companion.customOrderdata
import pharmlane.com.PharmLaneStore.interfaces.RetrofitInterface
import pharmlane.com.PharmLaneStore.model.AddInvoiceOffer
import pharmlane.com.PharmLaneStore.response.AddInvoiceOfferResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant
import pharmlane.com.PharmLaneStore.utills.InternalStorageContentProvider
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_upload_invoice.*
import retrofit2.Call
import org.json.JSONObject
import pharmlane.com.PharmLaneStore.BuildConfig
import pharmlane.com.PharmLaneStore.utills.Utils
import retrofit2.Callback
import java.io.File
import java.io.IOException

class UploadInvoiceActivity : AppCompatActivity(), View.OnClickListener {

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

    var isCustom = ""
    var image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark
        setContentView(R.layout.activity_upload_invoice)


        img_back.setOnClickListener(this)
        btn_upload_invoice.setOnClickListener(this)
        btn_handover_on_delivry.setOnClickListener(this)
        img_camera.setOnClickListener(this)
        img_gallery.setOnClickListener(this)
        img_cancel.setOnClickListener(this)

        if (intent != null) {
            if (intent.getStringExtra("custom") != null) {
                if (intent.getStringExtra("custom").equals("Custom", ignoreCase = true)) {
                    isCustom = "Custom"
                }

            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                finish()
            }
            R.id.btn_upload_invoice -> {

                uploadInvoiceApi()

            }
            R.id.btn_handover_on_delivry -> {

                image = ""
                img_add.visibility = View.GONE
                img_cancel.visibility = View.GONE

                btn_upload_invoice.setEnabled(false)
                uploadInvoiceApi()

            }
            R.id.img_camera -> {
                choice()
                openCamera()
            }
            R.id.img_gallery -> {
                choice()
                selectFromGallery()
            }
            R.id.img_cancel -> {

                img_add.visibility = View.GONE
                img_cancel.visibility = View.GONE

                btn_upload_invoice.setEnabled(false)
                //  btn_handover_on_delivry.setEnabled(false)
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
                    .setGuidelines(CropImageView.Guidelines.OFF)
                    .setAspectRatio(3, 4)
                    .setInitialCropWindowPaddingRatio(0F)
                    .setFixAspectRatio(true)
                    .setActivityTitle(resources.getString(R.string.my_crop))
                    .start(this)
            }
            if (requestCode == REQUEST_CAMERA) {
                val selectedImageUri = mFileTemp

                CropImage.activity(selectedImageUri)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setGuidelines(CropImageView.Guidelines.OFF)
                    .setAspectRatio(3, 4)
                    .setInitialCropWindowPaddingRatio(0F)
                    .setFixAspectRatio(true)
                    .setActivityTitle(resources.getString(R.string.my_crop))
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

                    img_add.visibility = View.VISIBLE
                    img_cancel.visibility = View.VISIBLE

                    bitmap = BitmapFactory.decodeFile(path)
                    //bitmap =  ScaleFile.compressImage(path,this)
                    img_add?.setImageBitmap(bitmap)

                    image = AppConstant.convert(bitmap!!)

                    btn_upload_invoice?.setEnabled(true)
                    // btn_handover_on_delivry?.setEnabled(true)
                }
            }
        }
    }

    fun uploadInvoiceApi() {

        progressBar.visibility = View.VISIBLE
        val addInvoiceOffer = AddInvoiceOffer()
        addInvoiceOffer.order_id = customOrderdata!!.orderId
        addInvoiceOffer.offer_id = customOrderdata!!.offerId
        addInvoiceOffer.invoice_image = image

        val apiService =
                AppConstant.getClient()!!.create<RetrofitInterface>(RetrofitInterface::class.java)
        val call = apiService.addinvoiceoffer(addInvoiceOffer)

        call.enqueue(object : Callback<AddInvoiceOfferResponse> {
            override fun onResponse(
                    call: Call<AddInvoiceOfferResponse>,
                    response: retrofit2.Response<AddInvoiceOfferResponse>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {

                    if (image.equals("")) {
                        startActivity(
                                Intent(this@UploadInvoiceActivity, CustomViewOfferActivity::class.java)
                                        .putExtra("handoverinvoice", "1")
                                        .putExtra("custom", isCustom)
                                        .putExtra("invoiceStatus", response.body()?.getData()))
                        finish()
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                    } else {
                        startActivity(
                                Intent(this@UploadInvoiceActivity, CustomViewOfferActivity::class.java)
                                        .putExtra("uploadinvoice", "1")
                                        .putExtra("custom", isCustom)
                                        .putExtra("invoiceStatus", response.body()?.getData())
                        )
                        finish()
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    }


                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(
                                this@UploadInvoiceActivity,
                                "" + jObjError.getString("message"),
                                Toast.LENGTH_SHORT
                        ).show()


                    } catch (e: Exception) {
                        Toast.makeText(this@UploadInvoiceActivity, e.message, Toast.LENGTH_LONG)
                                .show()
                    }

                }
            }


            override fun onFailure(call: Call<AddInvoiceOfferResponse>, t: Throwable) {
                // progressBarHandler.hide();
                progressBar.visibility = View.GONE
            }
        })
    }
}