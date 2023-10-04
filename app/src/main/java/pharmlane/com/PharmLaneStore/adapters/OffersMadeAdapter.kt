package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.CustomViewOfferActivity
import pharmlane.com.PharmLaneStore.activities.PaymentActivity
import pharmlane.com.PharmLaneStore.response.GetStoreListResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class OffersMadeAdapter(
        var context: Context,
        var offersmadeOrderdata: List<GetStoreListResponse.OffersMade>
) : RecyclerView.Adapter<OffersMadeAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersMadeAdapter.MyHolder {
        val v =
                LayoutInflater.from(parent.context).inflate(R.layout.item_offers_made, parent, false)
        return OffersMadeAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return offersmadeOrderdata.size
    }

    override fun onBindViewHolder(holder: OffersMadeAdapter.MyHolder, position: Int) {

        var isMoreHour = "0"
        var statusOfOrder = "offersmade"
        var receiverVerifyDate = offersmadeOrderdata[position].receiverVerifyDate
        var paymentVerifyDate = offersmadeOrderdata[position].paymentVerifyDate
        var orderDeliveredDate = offersmadeOrderdata[position].orderDelivered

        var delDate = ""
        var delsTime = ""
        var deleTime = ""

        holder.txtDateTime.text = (offersmadeOrderdata[position].createdDate.toString())


        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
        val cal1 = Calendar.getInstance()
        val currDate1 = dateFormat.format(cal1.time)
        cal1.time = dateFormat.parse(offersmadeOrderdata[position].createdDate.toString())
        cal1.get(Calendar.HOUR_OF_DAY)
        val convertedDate1 = dateFormat.format(cal1.time)

        val stopTime1 = dateFormat.parse(convertedDate1)
        val startTime1 = dateFormat.parse(currDate1)
        val difference1 = startTime1.time - stopTime1.time

        val InSec1 = TimeUnit.MILLISECONDS.toSeconds(difference1) % 60
        val day1 = TimeUnit.MILLISECONDS.toDays(difference1)
        val hh1 = (TimeUnit.MILLISECONDS.toHours(difference1) - TimeUnit.DAYS.toHours(day1))
        val mm1 = (TimeUnit.MILLISECONDS.toMinutes(difference1) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(difference1)
        ))
        //holder.txtTimeElapsed.setText("Time Elapsed: " +day1 + "d " +  hh1 + "h " + mm1 + "m " + InSec1 + "s")
//        holder.txtTimeElapsed.text = "Time Elapsed: " + day1 + "d " + hh1 + "h " + mm1 + "m "

//        holder.timer = ("" + hh1 + "h:" + mm1 + "m")

        try {
            holder.simpleChronometer.base = stopTime1.time
            holder.simpleChronometer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
                var t: Long = System.currentTimeMillis() - holder.simpleChronometer.base
                val days = TimeUnit.MILLISECONDS.toDays(t)
                t -= TimeUnit.DAYS.toMillis(days)
                val hours = TimeUnit.MILLISECONDS.toHours(t)
                t -= TimeUnit.HOURS.toMillis(hours)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(t)
                t -= TimeUnit.MINUTES.toMillis(minutes)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(t)
                var stopwatchDisplay = "%dd %dh %dm %ds"
                stopwatchDisplay = String.format(stopwatchDisplay, days, hours, minutes, seconds)
                holder.simpleChronometer.text = "Time Elapsed: " + stopwatchDisplay
            }
            holder.simpleChronometer.start()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        holder.timer = ("" + hh1 + "h:" + mm1 + "m")


        if (offersmadeOrderdata[position].offerDeliveryDate.toString().equals("")) {

            if (offersmadeOrderdata[position].deliveryType.equals("0")) {
                holder.txtDelDateT.text = context.getString(R.string.pickup_date)
                // holder.txtDelTimeT.text = context.getString(R.string.pickup_time)
            }

            val category: String = (offersmadeOrderdata[position].todateFromdate.toString())
            val builderr = StringBuilder()

            /* val merchandise = category.split("to").toTypedArray()

             for (details in merchandise) {
                 builderr.append(details+"to" + "\n")
             }*/

            val split: List<String> = category.split("to")
            val firstSubString = split[0]
            val secondSubString = split[1]
            holder.txtDelDate.text = firstSubString + "to"
            holder.txt_price_to_date.text = secondSubString
            //holder.txtDelDate.text=(builderr)

            // holder.txtDelTime.text=(offersmadeOrderdata[position].deliveryTimeStart.toString()+" "+offersmadeOrderdata[position].deliveryTimeEnd.toString())
            // holder.txtDelDate.text=(offersmadeOrderdata[position].todateFromdate.toString())


        } else {
            if (offersmadeOrderdata[position].offerDeliveryPreference.equals("0")) {
                holder.txtDelDateT.text = context.getString(R.string.pickup_date)
                // holder.txtDelTimeT.text = context.getString(R.string.pickup_time)
            }

            // holder.txtDelTime.text=(offersmadeOrderdata[position].offerDeliveryTimeStart.toString()+" "+offersmadeOrderdata[position].offerDeliveryTimeEnd.toString())

            val category: String = (offersmadeOrderdata[position].offerTodateFromdate.toString())

            val split: List<String> = category.split("to")
            val firstSubString = split[0]
            val secondSubString = split[1]

            holder.txtDelDate.text = firstSubString + "to"
            holder.txt_price_to_date.text = secondSubString
        }

        DrawableCompat.setTint(
                holder.txtStatus.background,
                ContextCompat.getColor(
                        context,
                        AppConstant.setsStatus(offersmadeOrderdata[position].orderStatus!!)
                )
        )

        /*if (offersmadeOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true)) {
            isMoreHour = "1"
            holder.txtTimeElapsed.visibility = View.GONE
            holder.txtOfferAccpted.visibility = View.VISIBLE
        }else{
            holder.txtTimeElapsed.visibility = View.VISIBLE
            holder.txtOfferAccpted.visibility = View.GONE
        }*/

        Log.d("TAGree", "Status   " + offersmadeOrderdata[position].requestStatus!!)
        DrawableCompat.setTint(
                holder.txtStatus1.background,
                ContextCompat.getColor(
                        context,
                        AppConstant.setsStatus(offersmadeOrderdata[position].requestStatus!!)
                )
        )

        if ((offersmadeOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true) && offersmadeOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true) ||
                        (offersmadeOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true) && offersmadeOrderdata[position].requestStatus.equals("Timed Out", ignoreCase = true)))) {
            holder.simpleChronometer.visibility = View.GONE
            holder.txtOfferAccpted.visibility = View.VISIBLE
        } else if (offersmadeOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true)) {
            holder.simpleChronometer.visibility = View.GONE
            holder.txtOfferAccpted.visibility = View.VISIBLE
        } else if (offersmadeOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true) || offersmadeOrderdata[position].requestStatus.equals("Timed Out", ignoreCase = true)) {
            holder.simpleChronometer.visibility = View.VISIBLE
            // holder.txtTimeElapsed.setText(R.string.edit_n_upoad_again)
            holder.txtOfferAccpted.visibility = View.GONE
        } else {
            holder.simpleChronometer.visibility = View.VISIBLE
            holder.txtOfferAccpted.visibility = View.GONE
        }

        holder.txtId.text = "ID: " + offersmadeOrderdata.get(position).orderNo
        holder.txtStatus.text = offersmadeOrderdata.get(position).orderStatus
        holder.txtStatus1.text = offersmadeOrderdata.get(position).requestStatus
        holder.txt_bip_del_perc.text = offersmadeOrderdata.get(position).discountPercentage + "% OFF"
        AppConstant.setPreferenceText(AppConstant.FINAL_AMOUNT, offersmadeOrderdata[position].offerFinalAmount.toString())

        Log.d("TAG", "------1111-------" + AppConstant.getPreferenceText(AppConstant.FINAL_AMOUNT))
        if (offersmadeOrderdata[position].offerDeliveryPreferenceName.equals("")) {
            holder.txtDelType.text = offersmadeOrderdata.get(position).deliveryTypeName
        } else {
            holder.txtDelType.text = offersmadeOrderdata.get(position).offerDeliveryPreferenceName
        }

        if (offersmadeOrderdata.get(position).offerFinalAmount.equals("")) {
            holder.txtDelPrice.text = "₹ -"
        } else {
            holder.txtDelPrice.text = "₹ " + offersmadeOrderdata.get(position).offerFinalAmount
        }
        /*holder.itemView.setOnClickListener {

            if (offersmadeOrderdata.get(position).orderTypeName.equals("custom",ignoreCase = true)){
                context.startActivity(
                    Intent(context, CustomOrderDetailActivity::class.java)
                        .putExtra("timer", holder.timer)
                        .putExtra("moreHour", isMoreHour)
                        .putExtra("statusviewall", offersmadeOrderdata[position].statusViewAll.toString())
                        .putExtra("orderId", offersmadeOrderdata[position].orderId.toString()))
                       // .putExtra("orderId", "6"))
            }else{
                context.startActivity(
                    Intent(context, OrderDetailActivity::class.java)
                        .putExtra("timer", holder.timer)
                        .putExtra("moreHour", isMoreHour)
                        //.putExtra("orderId", offersmadeOrderdata[position].orderId.toString()))
                        .putExtra("orderId", offersmadeOrderdata[position].orderId.toString()))
            }


        }*/
        holder.itemView.setOnClickListener {

            if (offersmadeOrderdata[position].orderStatus.equals("Invoiced", ignoreCase = true)) {
                statusOfOrder = "uploadinvoice"
            } else if (offersmadeOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true)) {
                statusOfOrder = "Accepted"
            }
            if (!offersmadeOrderdata[position].orderStatus.equals("Invoiced") && !offersmadeOrderdata[position].orderStatus.equals("Delivered")) {

                context.startActivity(
                        Intent(context, CustomViewOfferActivity::class.java)
                                .putExtra(statusOfOrder, "1")
                                .putExtra("custom", offersmadeOrderdata.get(position).orderTypeName)
                                .putExtra("orderId", offersmadeOrderdata.get(position).orderId.toString())
                )
            } else {
                context.startActivity(
                        Intent(context, PaymentActivity::class.java)
                                .putExtra(
                                        "receiverVerifyDate",
                                        offersmadeOrderdata[position].receiverVerifyDate
                                )
                                .putExtra(
                                        "paymentVerifyDate",
                                        offersmadeOrderdata[position].paymentVerifyDate
                                )
                                .putExtra(
                                        "orderDeliveredDate",
                                        offersmadeOrderdata[position].orderDelivered
                                )
                                .putExtra("orderId", offersmadeOrderdata.get(position).orderId.toString())
                )
            }

        }
        holder.imgPrompt.setOnClickListener {

        }

    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val viewoffer = itemView.findViewById(R.id.txt_view_p_offer) as AppCompatTextView
        val txtId = itemView.findViewById(R.id.txt_id_offers_made) as AppCompatTextView
        val txtDateTime = itemView.findViewById(R.id.text_ofermade_dtntime) as AppCompatTextView
        val txtStatus1 = itemView.findViewById(R.id.txt_ofermade_status1) as AppCompatTextView
        val txtStatus = itemView.findViewById(R.id.txt_ofermade_status2) as AppCompatTextView
        val txtDelType = itemView.findViewById(R.id.txt_del_type) as AppCompatTextView
        val txtDelDate = itemView.findViewById(R.id.txt_price_date) as AppCompatTextView
        val txtDelTime = itemView.findViewById(R.id.txt_price_time) as AppCompatTextView
        val txtDelPrice = itemView.findViewById(R.id.txt_price_rs) as AppCompatTextView
        val txt_price_to_date = itemView.findViewById(R.id.txt_price_to_date) as AppCompatTextView
//        val txtTimeElapsed = itemView.findViewById(R.id.txt_ofermade_time_elapsed) as AppCompatTextView
        val simpleChronometer = itemView.findViewById(R.id.simpleChronometer) as Chronometer

        val txtOfferAccpted = itemView.findViewById(R.id.txt_ofermade_accepted) as AppCompatTextView
        val imgPrompt = itemView.findViewById(R.id.img_prompt) as AppCompatImageView
        val txtDelDateT = itemView.findViewById(R.id.txt_del_date) as AppCompatTextView
        val txtDelTimeT = itemView.findViewById(R.id.txt_del_time) as AppCompatTextView
        val txt_bip_del_perc = itemView.findViewById(R.id.txt_bip_del_perc) as AppCompatTextView


        var timer = ""
    }


}