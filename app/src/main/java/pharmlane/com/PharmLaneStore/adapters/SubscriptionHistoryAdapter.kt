package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.response.StoreSubHistoryResponse
import java.text.SimpleDateFormat
import java.util.*

class SubscriptionHistoryAdapter
(
        var subsList: List<StoreSubHistoryResponse.Datum>,
        var context: Context
) : RecyclerView.Adapter<SubscriptionHistoryAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SubscriptionHistoryAdapter.MyHolder {
        val v =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_my_subscription_history, parent, false)
        return SubscriptionHistoryAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return subsList.size
    }

    override fun onBindViewHolder(holder: MyHolder, pos: Int) {

        var date = subsList[pos].created_date!!
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("dd MMM, yyyy")
        date = spf.format(newDate)

        var datec = subsList[pos].expiredate!!
        var spfc = SimpleDateFormat("yyyy-MM-dd")
        val newDatec: Date = spfc.parse(datec)
        spfc = SimpleDateFormat("dd MMM, yyyy")
        datec = spfc.format(newDatec)



        if(subsList[pos].status!!.equals("Pending")){
            holder.textView144.visibility = View.GONE
            holder.txt_expiry.text = "Pending"
        }else{
            holder.textView144.visibility = View.VISIBLE
            holder.txt_expiry.text = datec
        }

        holder.txt_date.text =  date
        holder.txt_plans.text = subsList[pos].plan.toString()
        holder.txt_price.text = "Rs."+subsList[pos].price.toString()




    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val txt_date = itemView.findViewById(R.id.txt_date) as AppCompatTextView
        val txt_plans = itemView.findViewById(R.id.txt_plans) as AppCompatTextView
        val txt_price = itemView.findViewById(R.id.txt_price) as AppCompatTextView
        val txt_expiry = itemView.findViewById(R.id.txt_expiry) as AppCompatTextView
        val textView144 = itemView.findViewById(R.id.textView144) as AppCompatTextView
    }
}