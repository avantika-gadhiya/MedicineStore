package pharmlane.com.PharmLaneStore.loginactivities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import pharmlane.com.PharmLaneStore.R
import kotlinx.android.synthetic.main.activity_thank_you.*

class ThankYouActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //  window.statusBarColor = Color.TRANSPARENT
        }
        /* getWindow().setFlags(
             WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
             WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
         )*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }//  set status text dark


        setContentView(R.layout.activity_thank_you)

        img_back.setOnClickListener(this)
        btn_ok.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_ok -> {
                startActivity(Intent(this@ThankYouActivity, LoginActivity::class.java))
                // finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            R.id.img_back -> {
             //   finish()
            }
        }
    }
}
