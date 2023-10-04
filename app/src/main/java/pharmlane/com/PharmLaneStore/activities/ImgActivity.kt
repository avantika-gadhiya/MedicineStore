package pharmlane.com.PharmLaneStore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.MakeOfferActivity.Companion.bmp
import kotlinx.android.synthetic.main.activity_img.*

class ImgActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark
        setContentView(R.layout.activity_img)

        if (bmp != null) {
            imageView.setImageBitmap(bmp)
        }
        img_back.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_back -> {
                finish()
            }}
    }
}
