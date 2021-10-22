package bluesharklabs.com.medicinestore.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import bluesharklabs.com.medicinestore.R
import bluesharklabs.com.medicinestore.adapters.NotificationAdapter
import bluesharklabs.com.medicinestore.interfaces.RetrofitInterface
import bluesharklabs.com.medicinestore.model.NotiFications
import bluesharklabs.com.medicinestore.response.NotificationResponse
import bluesharklabs.com.medicinestore.response.TandCResponse
import bluesharklabs.com.medicinestore.utills.AppConstant
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.activity_terms_conditions.*
import kotlinx.android.synthetic.main.activity_terms_conditions.img_back
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class TermsConditionsActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark

        setContentView(R.layout.activity_terms_conditions)

        img_back.setOnClickListener(this)

        getTandC()


//        webview.loadUrl("https://mindorks.com/")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                finish()
            }
        }
    }





    fun getTandC() {

        progressBarTandC.visibility = View.VISIBLE
        val apiService =
            AppConstant.getClient()!!.create<RetrofitInterface>(RetrofitInterface::class.java)
        val call = apiService.getTandC()

        call.enqueue(object : Callback<TandCResponse> {
            override fun onResponse(
                call: Call<TandCResponse>,
                response: retrofit2.Response<TandCResponse>
            ) {
                progressBarTandC.visibility = View.GONE
                if (response.isSuccessful) {

                    val storeTandC = response.body()!!.getData()?.get(0)?.storeTermsConditions
                    txt_TandC.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Html.fromHtml(storeTandC, Html.FROM_HTML_MODE_COMPACT)
                    } else {
                        Html.fromHtml(storeTandC)
                    }

                } else {

                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(
                            this@TermsConditionsActivity,
                            "" + jObjError.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()


                    } catch (e: Exception) {
                        Toast.makeText(this@TermsConditionsActivity, e.message, Toast.LENGTH_LONG)
                            .show()
                    }

                }
            }


            override fun onFailure(call: Call<TandCResponse>, t: Throwable) {
                progressBarTandC.visibility = View.GONE


            }
        })
    }
}
