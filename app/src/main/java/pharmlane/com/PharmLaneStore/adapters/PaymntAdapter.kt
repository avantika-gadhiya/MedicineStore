package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.model.Register
import pharmlane.com.PharmLaneStore.response.GetAllDropdownResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant

class PaymntAdapter(
    var context: Context,
    var paymentArray: List<GetAllDropdownResponse.PaymentMethods>,
    var arrapaymnt: arrayPayment, var qrImg: addQRCode
) : RecyclerView.Adapter<PaymntAdapter.MyHolder>() {

    var array = ArrayList<Register.payment>()
    var payMents = Register.payment()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PaymntAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_paymnt, parent, false)
        return PaymntAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return paymentArray.size
    }

    override fun onBindViewHolder(holder: PaymntAdapter.MyHolder, pos: Int) {

        holder.txtPaymntName.text = paymentArray[pos].name
        holder.btnDownloadQR.visibility = View.GONE
        holder.btnEditQR.visibility = View.GONE
        holder.txtNote.text = "Check to Save " + paymentArray[pos].name

        if (paymentArray[pos].name.equals("On Credit")) {
            holder.img_qr_code.visibility = View.GONE
            holder.btnUploadQR.visibility = View.GONE
        }

        if (paymentArray[pos].isInput.equals("0")) {
            holder.txtview.visibility = View.GONE
            holder.view.visibility = View.GONE
        } else {
            holder.txtview.visibility = View.VISIBLE
            holder.view.visibility = View.VISIBLE
        }


        holder.btnUploadQR.setOnClickListener {
            AppConstant.setPreferenceText(AppConstant.QR_CODE, "")
            qrImg.qr_codee(holder.img_qr_code)

        }

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->


            if (isChecked) {


                payMents = Register.payment()
                payMents.payment_method_id = paymentArray[pos].id
                payMents.upi_id = holder.edtUpiId.text.toString()
                payMents.qr_img = AppConstant.getPreferenceText(AppConstant.QR_CODE)


                if (paymentArray[pos].isInput.equals("1")) {
                    if (TextUtils.isEmpty(payMents.upi_id) &&
                        (payMents.qr_img.equals(null) || payMents.qr_img.equals(""))
                    ) {
                        Toast.makeText(
                            context,
                            "Please enter UPI ID or Mobile Number OR Upload QR.",
                            Toast.LENGTH_SHORT
                        ).show()
                        holder.checkBox.isChecked = false
                    } else {
                        array.add(payMents)
                        arrapaymnt.add(array)
                    }
                }
            } else if (!isChecked) {
                val id = paymentArray[pos].id

                for (i in array.indices) {

                    if (array.get(i).payment_method_id.equals(id)) {

                        payMents = Register.payment()
                        payMents.payment_method_id = array.get(i).payment_method_id
                        payMents.upi_id = array.get(i).upi_id
                        payMents.qr_img = array.get(i).qr_img

                        Log.d("TAG", "rasgdfgdfemove--> " + array.get(i).payment_method_id)
                        Log.d("TAG", "id--> " + array.get(i).upi_id)

                        array.removeAt(i)

                    }
                }
                Log.d("TAG", "remove--> $array")
            }
        }

    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val checkBox = itemView.findViewById(R.id.checkBox) as CheckBox
        val txtPaymntName = itemView.findViewById(R.id.txt_paymnt_name) as AppCompatTextView
        val edtUpiId = itemView.findViewById(R.id.edt_upi_id) as AppCompatEditText
        val img_qr_code = itemView.findViewById(R.id.img_edit_qr_code) as AppCompatImageView
        val txtview = itemView.findViewById(R.id.textView) as TextInputLayout
        val view = itemView.findViewById(R.id.view) as View
        val txtNote = itemView.findViewById(R.id.txtnote) as AppCompatTextView
        val btnDownloadQR = itemView.findViewById(R.id.btn_download_qr) as AppCompatTextView
        val btnUploadQR = itemView.findViewById(R.id.btn_upload_qr) as AppCompatTextView
        val btnEditQR = itemView.findViewById(R.id.btn_edit_qr) as AppCompatTextView


    }

    interface addQRCode {
        fun qr_codee(img: AppCompatImageView)
    }

    interface arrayPayment {
        fun add(array: ArrayList<Register.payment>)
    }
}