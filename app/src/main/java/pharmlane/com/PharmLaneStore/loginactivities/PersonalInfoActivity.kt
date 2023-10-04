package pharmlane.com.PharmLaneStore.loginactivities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import pharmlane.com.PharmLaneStore.R
import kotlinx.android.synthetic.main.activity_personal_info.*
import android.graphics.Color
import android.widget.Toast
import pharmlane.com.PharmLaneStore.utills.AppConstant.CONSTANT_NUMBER

class PersonalInfoActivity : AppCompatActivity(), View.OnClickListener {

    private var ownr_name: String = ""
    private var ownr_mb_no: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
        /*getWindow().setFlags(
             WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
             WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
         )*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }//  set status text dark
        setContentView(R.layout.activity_personal_info)

        btn_next.setOnClickListener(this)
        img_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                startActivity(Intent(this@PersonalInfoActivity, LoginActivity::class.java))
                finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            R.id.btn_next -> {

                ownerName = edt_owner_name.text.toString().trim()
                ownerMobile = edt_mobil_no.text.toString().trim()
                emponeName = employee_1_nm.text.toString().trim()
                emponeMobile = employee_1_mo.text.toString().trim()
                emptwoName = employee_2_nm.text.toString().trim()
                emptwoMobile = employee_2_mo.text.toString().trim()

                if (ownerName.equals("")) {
                    Toast.makeText(this, getString(R.string.enter_your_name), Toast.LENGTH_SHORT)
                        .show()
                } else if (ownerMobile.equals("")) {
                    Toast.makeText(this, getString(R.string.enter_mobilenumber), Toast.LENGTH_SHORT)
                        .show()
                } else if (ownerMobile.length < 10) {
                    Toast.makeText(this, getString(R.string.enter_valid_number), Toast.LENGTH_SHORT)
                        .show()

                } else {
                    startActivity(
                        Intent(this, Verify1Activity::class.java).putExtra(
                            CONSTANT_NUMBER,
                            ownerMobile
                        )
                    )
                    // finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }

    companion object {

        var ownerName = ""
        var ownerMobile = ""
        var emponeName = ""
        var emponeMobile = ""
        var emptwoName = ""
        var emptwoMobile = ""

    }
}