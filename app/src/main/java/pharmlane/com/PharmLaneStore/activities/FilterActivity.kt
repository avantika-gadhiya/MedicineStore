package pharmlane.com.PharmLaneStore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.fragments.*
import pharmlane.com.PharmLaneStore.interfaces.FiltersInterFace
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity(), View.OnClickListener, FiltersInterFace {


    private var byType = ""
    private var byStatus = ""
    private var byCustomerName = ""
    private var byCity = ""
    private var byArea = ""
    private var byZipcode = ""
    private lateinit var mBroadcastManager: LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark

        setContentView(R.layout.activity_filter)


        txt_by_custmr_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        txt_by_area.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        txt_by_zip_code.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        txt_by_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)

        img_close.setOnClickListener(this)
        btn_reset.setOnClickListener(this)
        btn_apply.setOnClickListener(this)
        txt_by_type.setOnClickListener(this)
        txt_by_status.setOnClickListener(this)
        txt_by_custmr_name.setOnClickListener(this)
        txt_by_city.setOnClickListener(this)
        txt_by_area.setOnClickListener(this)
        txt_by_zip_code.setOnClickListener(this)
        mBroadcastManager = LocalBroadcastManager.getInstance(this)

        clickType()

    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_apply -> {
                mBroadcastManager.sendBroadcast(
                        Intent(resources.getString(R.string.broadcastFilterResult))
                                .putExtra(
                                        resources.getString(R.string.filterType),
                                        byType
                                )
                                .putExtra(
                                        resources.getString(R.string.filterStatus),
                                        byStatus
                                )
                                .putExtra(
                                        resources.getString(R.string.filterCustomerName),
                                        byCustomerName
                                )
                                .putExtra(
                                        resources.getString(R.string.filterCity),
                                        byCity
                                )
                                .putExtra(
                                        resources.getString(R.string.filterArea),
                                        byArea
                                )
                                .putExtra(
                                        resources.getString(R.string.filterZipcode),
                                        byZipcode
                                )
                )
                finish()
            }
            R.id.btn_reset -> {
                byType = ""
                byStatus = ""
                byCustomerName = ""
                byCity = ""
                byArea = ""
                byZipcode = ""

                mBroadcastManager.sendBroadcast(
                        Intent(resources.getString(R.string.broadcastFilterResult))
                                .putExtra(
                                        resources.getString(R.string.filterType),
                                        byType
                                )
                                .putExtra(
                                        resources.getString(R.string.filterStatus),
                                        byStatus
                                )
                                .putExtra(
                                        resources.getString(R.string.filterCustomerName),
                                        byCustomerName
                                )
                                .putExtra(
                                        resources.getString(R.string.filterCity),
                                        byCity
                                )
                                .putExtra(
                                        resources.getString(R.string.filterArea),
                                        byArea
                                )
                                .putExtra(
                                        resources.getString(R.string.filterZipcode),
                                        byZipcode
                                )
                )

                finish()

            }
            R.id.img_close -> {
                finish()
            }
            R.id.txt_by_type -> {
                clickType()
            }
            R.id.txt_by_custmr_name -> {
                clickStoreName()
            }
            R.id.txt_by_area -> {
                clickArea()
            }
            R.id.txt_by_city -> {
                clickCity()
            }
            R.id.txt_by_zip_code -> {

                clickZipCode()
            }
            R.id.txt_by_status -> {
                txt_by_status.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                clickStatus()
//                startActivity(
//                    Intent(this@SliderFilterActivity, SliderFilterActivity::class.java)
//                )
//                //finish()
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    fun clickZipCode() {
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByZipCodeFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    fun clickStatus() {
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByStatusFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clickCity() {
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByCityFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clickArea() {
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByAreaFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clickStoreName() {
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByCustomerNameFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clickType() {
        txt_by_type.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGray))
        txt_by_city.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_custmr_name.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_area.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        txt_by_zip_code.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        val newFragment = ByTypeFragment(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onClickType(type: String) {
        byType = type
    }

    override fun onClickStatus(status: String) {
        byStatus = status
    }

    override fun onClickCustomerName(customerName: String) {
        byCustomerName = customerName
    }

    override fun onClickCity(city: String) {
        byCity = city
    }

    override fun onClickArea(area: String) {
        byArea = area
    }

    override fun onClickZipcode(zipcode: String) {
        byZipcode = zipcode
    }
}
