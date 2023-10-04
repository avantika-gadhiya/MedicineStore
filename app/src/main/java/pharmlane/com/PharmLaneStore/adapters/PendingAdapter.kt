package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.content.Intent
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
import pharmlane.com.PharmLaneStore.activities.CustomOrderDetailActivity
import pharmlane.com.PharmLaneStore.activities.CustomViewOfferActivity
import pharmlane.com.PharmLaneStore.activities.OrderDetailActivity
import pharmlane.com.PharmLaneStore.response.GetStoreListResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class PendingAdapter(
    var context: Context,
    var pendingOrderdata: List<GetStoreListResponse.PendingOrder>
) : RecyclerView.Adapter<PendingAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingAdapter.MyHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_orders_viewall, parent, false)
        return PendingAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return pendingOrderdata.size
    }

    override fun onBindViewHolder(holder: PendingAdapter.MyHolder, position: Int) {


        var isMoreHour = "0"
        var statusOfOrder = "offersmade"


        if (pendingOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true)) {
            isMoreHour = "1"
        }

        if (pendingOrderdata[position].orderStatus.equals("Invoiced", ignoreCase = true)) {
            statusOfOrder = "uploadinvoice"
        }
        if (pendingOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true) &&
                pendingOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true)) {
//            holder.txtTimeElapsed.visibility = View.GONE
            holder.simpleChronometer.visibility = View.GONE

            holder.txtOfferAccepted.visibility = View.VISIBLE
        }else if (pendingOrderdata[position].orderStatus.equals("Accepted", ignoreCase = true)) {
//            holder.txtTimeElapsed.visibility = View.GONE
            holder.simpleChronometer.visibility = View.GONE
            holder.txtOfferAccepted.visibility = View.VISIBLE
        }else if (pendingOrderdata[position].requestStatus.equals(">24 hrs", ignoreCase = true)) {
//            holder.txtTimeElapsed.visibility = View.VISIBLE
            holder.simpleChronometer.visibility = View.VISIBLE
            holder.txtOfferAccepted.visibility = View.GONE
        } else {
//            holder.txtTimeElapsed.visibility = View.VISIBLE
            holder.simpleChronometer.visibility = View.VISIBLE
            holder.txtOfferAccepted.visibility = View.GONE
        }

        holder.txtId.text = "ID: " + pendingOrderdata.get(position).orderNo
        holder.txtStatus.text = pendingOrderdata.get(position).orderStatus
        holder.txtStatus1.text = pendingOrderdata.get(position).requestStatus
        holder.txtDatenTime.text =pendingOrderdata.get(position).createdDate


        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
        val cal1 = Calendar.getInstance()
        val currDate1 = dateFormat.format(cal1.time)
        cal1.time = dateFormat.parse(pendingOrderdata[position].createdDate.toString())
        cal1.get(Calendar.HOUR_OF_DAY)
        val convertedDate1 = dateFormat.format(cal1.time)

        val stopTime1 = dateFormat.parse(convertedDate1)
        val startTime1 = dateFormat.parse(currDate1)
        val difference = startTime1.time - stopTime1.time

        val InSec1 = TimeUnit.MILLISECONDS.toSeconds(difference) % 60
        val day1 = TimeUnit.MILLISECONDS.toDays(difference)
        val hh1 = (TimeUnit.MILLISECONDS.toHours(difference) - TimeUnit.DAYS.toHours(day1))
        val mm1 = (TimeUnit.MILLISECONDS.toMinutes(difference) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(difference)
        ))
        DrawableCompat.setTint(
                holder.txtStatus.getBackground(),
                ContextCompat.getColor(
                        context,
                        AppConstant.setsStatus(pendingOrderdata[position].orderStatus!!)
                )
        )

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

//        val InSec = TimeUnit.MILLISECONDS.toSeconds(difference) % 60
//        val day = TimeUnit.MILLISECONDS.toDays(difference)
//        val hh = (TimeUnit.MILLISECONDS.toHours(difference) - TimeUnit.DAYS.toHours(day))
//        val mm = (TimeUnit.MILLISECONDS.toMinutes(difference) - TimeUnit.HOURS.toMinutes(
//                TimeUnit.MILLISECONDS.toHours(difference)
//        ))
        //holder.txtTimeElapsed.setText("Time Elapsed: " +day1 + "d " +  hh1 + "h " + mm1 + "m " + InSec1 + "s")
//        holder.txtTimeElapsed.setText("Time Elapsed: " +day1 + "d " +  hh1 + "h " + mm1 + "m ")
//        holder.timer = ("" + hh1 + "h:" + mm1 + "m")
//        holder.timer = ("" + hh + "h:" + mm + "m:" + InSec + "s")



        DrawableCompat.setTint(
            holder.txtStatus1.getBackground(),
            ContextCompat.getColor(
                context,
                AppConstant.setsStatus(pendingOrderdata[position].requestStatus!!)
            )
        )






        holder.txtFordatenTime= pendingOrderdata.get(position).createdDate.toString()

        holder.itemView.setOnClickListener {
            if (pendingOrderdata.get(position).orderStatus.equals("Pending", ignoreCase = true)) {
                if (pendingOrderdata.get(position).orderTypeName.equals(
                        "Custom",
                        ignoreCase = true
                    )
                ) {
                    context.startActivity(
                        Intent(context, CustomOrderDetailActivity::class.java)
                            .putExtra("timer", holder.timer)
                            .putExtra("datentime", holder.txtFordatenTime)
                            .putExtra("moreHour", isMoreHour)
                            .putExtra(
                                "statusviewall",
                                pendingOrderdata[position].statusViewAll.toString()
                            )
                            .putExtra("orderId", pendingOrderdata[position].orderId.toString())
                    )
                    // .putExtra("orderId", "6"))
                } else {
                    context.startActivity(
                        Intent(context, OrderDetailActivity::class.java)
                            .putExtra("timer", holder.timer)
                            .putExtra("moreHour", isMoreHour)
                            .putExtra("datentime", holder.txtFordatenTime)
                            .putExtra("orderId", pendingOrderdata[position].orderId.toString())
                    )
                }
            }else{
                context.startActivity(
                    Intent(context, CustomViewOfferActivity::class.java)
                        .putExtra(statusOfOrder, "1")
                        .putExtra("custom", pendingOrderdata.get(position).orderTypeName)
                        .putExtra("orderId", pendingOrderdata.get(position).orderId.toString())
                )
            }
        }

        holder.imgPrompt.setOnClickListener {

        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtId = itemView.findViewById(R.id.txt_id) as AppCompatTextView
        val txtStatus1 = itemView.findViewById(R.id.txt_status1) as AppCompatTextView
        val txtStatus = itemView.findViewById(R.id.txt_status) as AppCompatTextView
        val txtDatenTime = itemView.findViewById(R.id.txt_dtntime) as AppCompatTextView
        val txtTimeElapsed = itemView.findViewById(R.id.txt_time_elapsed) as AppCompatTextView
        val simpleChronometer = itemView.findViewById(R.id.simpleChronometer) as Chronometer

        val txtOfferAccepted =
            itemView.findViewById(R.id.txt_ofer_accepted) as AppCompatTextView
        val imgPrompt = itemView.findViewById(R.id.img_prompt) as AppCompatImageView

        var timer = ""
        var txtFordatenTime = ""
    }

}