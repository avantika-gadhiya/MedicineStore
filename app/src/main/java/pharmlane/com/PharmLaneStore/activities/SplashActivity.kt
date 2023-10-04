package pharmlane.com.PharmLaneStore.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.loginactivities.LoginActivity
import pharmlane.com.PharmLaneStore.utills.AppConstant
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 28) {
            val w = window
            w.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(R.layout.activity_splash)
        generateToken()

        //throw RuntimeException("Test Crash")


        Handler().postDelayed({

            when {
                AppConstant.getPreferenceBoolean(AppConstant.IS_LOGIN) -> {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                else -> {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

        }, 2000)


    }

    private fun generateToken() {


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result


            Log.e("HElloDeToken", "Hiii   " + token)
            AppConstant.setPreferenceText(AppConstant.PREF_FCM_TOKEN, token.toString())
        })
    }
}