package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.CustomOrderDetailActivity
import pharmlane.com.PharmLaneStore.activities.CustomViewOfferActivity
import pharmlane.com.PharmLaneStore.activities.OrderDetailActivity
import pharmlane.com.PharmLaneStore.response.ViewAllStoreOrderResponse
import pharmlane.com.PharmLaneStore.utills.AppConstant

class MyOrdersAdapter(var context: Context, var myorder: List<ViewAllStoreOrderResponse.Datum>
) : RecyclerView.Adapter<MyOrdersAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyOrdersAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_my_orders, parent, false)
        Log.e("WHICHADAPTEREEE", "MyOrdersAdapter")
        return MyOrdersAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return myorder.size
    }

    override fun onBindViewHolder(holder: MyOrdersAdapter.MyHolder, position: Int) {
        holder.txt_id_offers_made.text = "ID: " + myorder.get(position).orderNo
        var statusOfOrder = "offersmade"

        if (myorder[position].orderStatus.equals("Invoiced", ignoreCase = true)) {
            statusOfOrder = "uploadinvoice"
        }

        Log.e("HEllodatessss","ffffff    "+myorder.get(position).createdDate)
        holder.text_ofermade_dtntime.text = myorder.get(position).createdDate.toString()
        holder.txt_ofermade_status1.text = myorder.get(position).requestStatus
        holder.txt_ofermade_status2.text = myorder.get(position).orderStatus
        holder.txt_order_type_name.text = myorder.get(position).offerDeliveryPreferenceName
      holder.txt_delivery_date.text = myorder.get(position).todateFromdate
      holder.txt_price.text = "Rs."+myorder.get(position).finalAmount
      holder.txt_percentage.text = myorder.get(position).discountPercentage+" % OFF"

        DrawableCompat.setTint(
            holder.txt_ofermade_status2.getBackground(),
            ContextCompat.getColor(
                context,
                AppConstant.setsStatus(myorder.get(position).orderStatus!!)
            )
        )
        DrawableCompat.setTint(
            holder.txt_ofermade_status1.getBackground(),
            ContextCompat.getColor(
                context,
                AppConstant.setsStatus(myorder.get(position).requestStatus!!)
            )
        )

        val category: String =  myorder.get(position).todateFromdate.toString()

        val split: List<String> = category.split("to")
        val firstSubString = split[0]
        val secondSubString = split[1]

        holder.txt_delivery_date.text = firstSubString + "to"
        holder.txt_price_to_date.text = secondSubString

//        holder.itemView.setOnClickListener {
//
//            Log.e("HEllologs","Hii   "+myorder.get(position).orderId)
//            context.startActivity(Intent(context, ViewOfferActivity::class.java)
//                    .putExtra("MyOrder", myorder.get(position).orderId))
//
//
//        }


        holder.itemView.setOnClickListener {
            if (myorder.get(position).orderStatus.equals("Pending", ignoreCase = true)) {
                if (myorder.get(position).orderTypeName.equals(
                        "Custom",
                        ignoreCase = true
                    )
                ) {

                    Log.e("HELLOCLICKS", "IFFF   ")
                    context.startActivity(
                        Intent(context, CustomOrderDetailActivity::class.java)
                            .putExtra("direct", "1")
                            .putExtra(
                                "statusviewall",
                                "1"
                            )
                            .putExtra("orderId", myorder[position].orderId.toString())
                    )
                    // .putExtra("orderId", "6"))
                } else {
                    Log.e("HELLOCLICKS", "ELSE   ")
                    context.startActivity(
                        Intent(context, OrderDetailActivity::class.java)
                            .putExtra("direct", "1")
                            .putExtra("orderId", myorder[position].orderId.toString())
                    )

                }
            } else {
                Log.e("HELLOCLICKS", "ELSE MAIN   ")
                context.startActivity(
                    Intent(context, CustomViewOfferActivity::class.java)
                        .putExtra(statusOfOrder, "1")
                        .putExtra("custom", myorder.get(position).orderTypeName)
                        .putExtra("orderId", myorder.get(position).orderId.toString())
                )
            }
        }


    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var txt_id_offers_made = itemview.findViewById<AppCompatTextView>(R.id.txt_id_offers_made)
        var text_ofermade_dtntime = itemview.findViewById<AppCompatTextView>(R.id.text_ofermade_dtntime)
        var txt_ofermade_status1 = itemview.findViewById<AppCompatTextView>(R.id.txt_ofermade_status1)
        var txt_ofermade_status2 = itemview.findViewById<AppCompatTextView>(R.id.txt_ofermade_status2)
        var txt_order_type_name = itemview.findViewById<AppCompatTextView>(R.id.appCompatTextView6)
        var txt_delivery_date = itemview.findViewById<AppCompatTextView>(R.id.txt_price_date)
        var txt_price_to_date = itemview.findViewById<AppCompatTextView>(R.id.txt_price_to_date)
        var txt_delivery_time = itemview.findViewById<AppCompatTextView>(R.id.txt_price_time)
        var txt_price = itemview.findViewById<AppCompatTextView>(R.id.txt_price_rs)
        var txt_percentage = itemview.findViewById<AppCompatTextView>(R.id.txt_percentage)

    }


}