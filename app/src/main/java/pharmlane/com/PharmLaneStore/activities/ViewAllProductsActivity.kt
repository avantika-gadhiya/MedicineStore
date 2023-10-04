package pharmlane.com.PharmLaneStore.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.CustomOrderDetailActivity.Companion.customOrderdata
import pharmlane.com.PharmLaneStore.activities.MakeOfferActivity.Companion.bmp
import pharmlane.com.PharmLaneStore.adapters.CustomViewOfferAdapter
import pharmlane.com.PharmLaneStore.adapters.OrderOfferAdapter
import pharmlane.com.PharmLaneStore.adapters.ProductsOrederedAdapter
import pharmlane.com.PharmLaneStore.response.ViewAllStoreOrderResponse
import pharmlane.com.PharmLaneStore.utills.Utils.Companion.changeBitmapColor
import pharmlane.com.PharmLaneStore.utills.afterMeasured
import kotlinx.android.synthetic.main.activity_view_all_products.*
import kotlinx.android.synthetic.main.activity_view_all_products.img_back
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.roundToInt

class ViewAllProductsActivity : AppCompatActivity(), View.OnClickListener {

    private var productsOrederedAdapter: ProductsOrederedAdapter? = null
    private var customViewOfferAdapter: CustomViewOfferAdapter? = null
    private var orderOfferAdapter: OrderOfferAdapter? = null
    private var recycler: RecyclerView? = null
    private var img_order: AppCompatImageView? = null
    var Orderdata: List<ViewAllStoreOrderResponse.Datum> = arrayListOf()

    var statusViewall = ""
    var isCustom = ""
    var isViewall = "1"

    var hashMap: HashMap<Int, Int> = HashMap<Int, Int>()

    var viewImgWidth = 0
    var viewImgHeight = 0
    var imgAttchmentWidth = 0
    var imgAttchmentHeight = 0
    var imgXPosi = 0
    var imgYPosi = 0
    var iv: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark
        setContentView(R.layout.activity_view_all_products)

        recycler = findViewById(R.id.recycl)
        img_order = findViewById(R.id.img_order) as AppCompatImageView
        img_order!!.setImageBitmap(bmp)

        img_back.setOnClickListener(this)
        img_point_view_all.setOnClickListener(this)

        if (intent != null) {
            if (intent.getStringExtra("custom") != null) {
                if (intent.getStringExtra("custom").equals("Custom", ignoreCase = true)) {
                    isCustom = "Custom"
                }
            }

            if (intent.getStringExtra("statusviewall") != null) {
                statusViewall = intent.getStringExtra("statusviewall") ?: ""
            }
            if (intent.getStringExtra("youroffer") != null) {
                txt.text = resources.getString(R.string.your_offer)

                recycler!!.setHasFixedSize(true)
                recycler!!.layoutManager =
                        LinearLayoutManager(this) as RecyclerView.LayoutManager?

                if (isCustom.equals("Custom")) {

                    Log.e("HELLOCLICKS","Adapater Custom")
                    customViewOfferAdapter = CustomViewOfferAdapter(
                            this,
                            customOrderdata!!.products,
                            isViewall
                    )
                    recycler!!.adapter = customViewOfferAdapter

                } else {
                    Log.e("HELLOCLICKS","Adapater Not Custom")
                    orderOfferAdapter = OrderOfferAdapter(
                            this,
                            customOrderdata!!.products, isViewall
                    )
                    recycler!!.adapter = orderOfferAdapter
                }

            } else {
                Log.e("HELLOCLICKS","Adapater ELSE")
                recycler!!.setHasFixedSize(true)
                recycler!!.layoutManager =
                        LinearLayoutManager(this) as RecyclerView.LayoutManager?
                productsOrederedAdapter = ProductsOrederedAdapter(
                        this, customOrderdata!!.products, isViewall)
                recycler!!.adapter = productsOrederedAdapter
            }
        }

        if (customOrderdata?.prescriptionImage != null) {
            Log.e("Hellobros","Hii   "+customOrderdata?.prescriptionImage)
            if (!customOrderdata?.prescriptionImage.equals("")) {

                LoadImageWithDots(customOrderdata?.prescriptionImage!!).execute()

            } else {
                rel_image.visibility = View.GONE
                img_point_view_all .visibility = View.GONE
            }
        }
    }

    fun urlToBitmap(prescriptionImage: String): Bitmap {
        val url = URL(prescriptionImage)
        val connection: HttpURLConnection
        connection = url.openConnection() as HttpURLConnection
        connection.setDoInput(true)
        connection.connect()
        val input = connection.getInputStream()
        val myBitmap = BitmapFactory.decodeStream(input)
        return myBitmap
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoadImageWithDots(var imageSrc: String) : AsyncTask<Bitmap?, Bitmap?, Bitmap>() {


        override fun onPreExecute() {
            super.onPreExecute()
            progressBar_image_all.visibility = View.VISIBLE
        }

        override fun onPostExecute(result: Bitmap) {
            Log.e("HelloTags", "onPostExecute")
            progressBar_image_all.visibility = View.GONE
            SetBits(result)

        }

        override fun doInBackground(vararg params: Bitmap?): Bitmap? {
            val btmp = urlToBitmap(imageSrc)

            return btmp
        }
    }

    fun SetBits(result: Bitmap) {
        if (result != null) {
            bmp = scaleBitmap(result)
            img_order!!.setImageDrawable(BitmapDrawable(resources, bmp))

            Log.e("HelloTags", "SetBits")

            rel_image.afterMeasured {
                Log.e("HelloTags", "hii")
                viewImgWidth = rel_image.width
                viewImgHeight = rel_image.height
                if (viewImgWidth != 0) {
                    addTags()
                }
            }
        } else {
            rel_image.visibility = View.GONE
        }
    }

    private fun scaleBitmap(bmp: Bitmap): Bitmap {
        var bm = bmp
        var width = bm.width
        var height = bm.height
        imgAttchmentWidth = width
        imgAttchmentHeight = height

        val rectangle = Rect()
        val window = getWindow()
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle)
        val statusBarHeight = rectangle.top

        val maxWidth = Resources.getSystem().getDisplayMetrics().widthPixels
        val maxHeight = Resources.getSystem().getDisplayMetrics().heightPixels - con_toolbars.height - txt.height
        if (maxWidth <= width) {
            if (width > height) {
                // landscape
                val ratio = width.toFloat() / maxWidth
                width = maxWidth
                height = (height / ratio).toInt()
            } else if (height > width) {
                // portrait
                val ratio = height.toFloat() / maxHeight
                height = maxHeight
                width = (width / ratio).toInt()
            } else {
                // square
                height = maxHeight
                width = maxWidth
            }

            bm = Bitmap.createScaledBitmap(bm, width, height, true)
        }
        return bm
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                finish()
            }  R.id.img_point_view_all -> {
            if (bmp != null) {

/*

                    val mBuilder: AlertDialog.Builder = AlertDialog.Builder(this@MakeOfferActivity)
                    val mView: View = layoutInflater.inflate(R.layout.dialog_custom_layout, null)
                    val photoView: PhotoView = mView.findViewById(R.id.imageView)
                    photoView.setImageResource(R.drawable.nature)
                    mBuilder.setView(mView)
                    val mDialog: AlertDialog = mBuilder.create()
                    mDialog.show()
*/


                startActivity(
                        Intent(this, ImgActivity::class.java)
                )
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            }
        }
    }

    fun addTags() {

        for (i in 0 until customOrderdata!!.products!!.size) {
            iv = ImageView(this)


            val bm: Bitmap?

            bm = BitmapFactory.decodeResource(resources, R.drawable.pin)

            if (bm == null) {
                Toast.makeText(this, "Select Image", Toast.LENGTH_SHORT).show()
            } else {
                iv!!.setImageBitmap(bm)

                val params = RelativeLayout.LayoutParams(bm.width, bm.height)

                imgXPosi =
                        customOrderdata!!.products!!.get(i).productXPosition!!.toFloat().toInt()
                imgYPosi =
                        customOrderdata!!.products!!.get(i).productYPosition!!.toFloat().toInt()


                params.leftMargin = (viewImgWidth * imgXPosi) / imgAttchmentWidth
                params.topMargin = (viewImgHeight * imgYPosi) / imgAttchmentHeight

                hashMap.put(
                        customOrderdata!!.products!!.get(i).productXPosition!!.toFloat().roundToInt(),
                        customOrderdata!!.products!!.get(i).productYPosition!!.toFloat().roundToInt()
                )

                rel_image.addView(iv, params)

                // for (i in 0 until childcount) {
                val v = rel_image.getChildAt(i)
                changeBitmapColor(
                        bm,
                        iv!!,
                        Color.parseColor("#" + customOrderdata!!.products!!.get(i).color!!)
                )
                // }

//                iv!!.setOnClickListener {
//
//                    if (direct.equals("1")) {
//                        showdialog(
//                                Color.parseColor("#" + customOrderdata!!.products!!.get(i).color!!),
//                                i
//                        )
//                    } else {
//                        showdialogOne(
//                                Color.parseColor("#" + customOrderdata!!.products!!.get(i).color!!),
//                                i
//                        )
//                    }
//
//                }
            }
        }

//        progressBar_image_all.visibility = View.GONE
    }



}
