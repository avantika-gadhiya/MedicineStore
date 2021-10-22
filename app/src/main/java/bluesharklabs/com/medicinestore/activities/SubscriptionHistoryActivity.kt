package bluesharklabs.com.medicinestore.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bluesharklabs.com.medicinestore.R
import bluesharklabs.com.medicinestore.adapters.SubscriptionHistoryAdapter
import bluesharklabs.com.medicinestore.interfaces.RetrofitInterface
import bluesharklabs.com.medicinestore.model.Filter
import bluesharklabs.com.medicinestore.response.StoreSubHistoryResponse
import bluesharklabs.com.medicinestore.utills.AppConstant
import kotlinx.android.synthetic.main.activity_my_subscription.*
import kotlinx.android.synthetic.main.activity_subscription_history.*
import kotlinx.android.synthetic.main.activity_subscription_history.img_back
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class SubscriptionHistoryActivity : AppCompatActivity(), View.OnClickListener {

    private var subscriptionHistoryAdapter: SubscriptionHistoryAdapter? = null
    private var recycl_subs: RecyclerView? = null
    var subsListArray: List<StoreSubHistoryResponse.Datum> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark

        setContentView(R.layout.activity_subscription_history)

        img_back.setOnClickListener(this)
        btn_view_plan.setOnClickListener(this)


        recycl_subs = findViewById(R.id.recycl_subs)
        recycl_subs!!.setHasFixedSize(true)
        recycl_subs!!.layoutManager =
                LinearLayoutManager(this)


        mySubsHistory()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                finish()
            }
            R.id.btn_view_plan -> {

                startActivity(
                        Intent(
                                this@SubscriptionHistoryActivity,
                                MySubscriptionActivity::class.java
                        )
                )
            }
        }
    }


    override fun onResume() {
        super.onResume()
        mySubsHistory()
    }


    fun mySubsHistory() {

        progressBarForMySubshistory.visibility = View.VISIBLE

        val filter = Filter()
        filter.store_id = AppConstant.getPreferenceText(AppConstant.STORE_ID)

        val apiService =
                AppConstant.getClient()!!.create<RetrofitInterface>(RetrofitInterface::class.java)
        val call = apiService.getallstoresubhistory(filter)

        call.enqueue(object : Callback<StoreSubHistoryResponse> {
            override fun onResponse(
                    call: Call<StoreSubHistoryResponse>,
                    response: retrofit2.Response<StoreSubHistoryResponse>
            ) {
                progressBarForMySubshistory.visibility = View.GONE
                if (response.isSuccessful) {


                    subsListArray = response.body()?.data!!

                    subscriptionHistoryAdapter = SubscriptionHistoryAdapter(
                            subsListArray,
                            this@SubscriptionHistoryActivity)

                    txt_last_recharges.text = "Last " + subsListArray.size + " Recharges"

                    recycl_subs!!.adapter = subscriptionHistoryAdapter

                    subscriptionHistoryAdapter!!.notifyDataSetChanged()


                } else {
                    try {
                        txt_last_recharges.visibility = View.GONE
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(
                                this@SubscriptionHistoryActivity,
                                "" + jObjError.getString("message"),
                                Toast.LENGTH_SHORT
                        ).show()


                    } catch (e: Exception) {
                        Toast.makeText(this@SubscriptionHistoryActivity, e.message, Toast.LENGTH_LONG)
                                .show()
                    }
                }
            }

            override fun onFailure(call: Call<StoreSubHistoryResponse>, t: Throwable) {
                progressBarForMySubshistory.visibility = View.GONE
                txt_last_recharges.visibility = View.GONE
            }
        })
    }
}
