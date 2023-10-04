package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.response.OrderDetailResponse


class OrderOfferAdapter(
    var context: Context,
    var products: List<OrderDetailResponse.Product>?,
    var viewall: String
) : RecyclerView.Adapter<OrderOfferAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OrderOfferAdapter.MyHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_offer, parent, false)
        Log.e("WHICHADAPTEREEE", "OrderOfferAdapter")

        return OrderOfferAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        if (viewall.equals("0") && products!!.size > 3)
            return 3
        else
            return products!!.size
    }

    override fun onBindViewHolder(holder: OrderOfferAdapter.MyHolder, pos: Int) {

        val clr = ("#" + products!!.get(pos).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.imgColorCode.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)

        val avail = products!!.get(pos).availableProduct

        if (!avail.equals(null)) {
            if (products!!.get(pos).availableProduct!!.equals("1")) {
                holder.txtAvail.setText(R.string.available)
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.green_txt))
                holder.txtMrp.setText("₹ " + products!!.get(pos).productMrp)

                holder.txt.visibility = View.GONE
            } else {
                holder.txtAvail.setText(R.string.not_available)
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.red_txt))

                holder.txt.visibility = View.GONE
            }
        }
        Log.e("HElloQQQ","TTTT    "+products!!.get(pos).quantityType)
        if (products!!.get(pos).quantityType.equals("0")) {
            holder.txtQnt.setText("Qty: Full")
        } else {
            Log.e("HElloQQQ","WWWW    "+products!!.get(pos).storeAvailableQuantity)
//            holder.txtQnt.setText("Qty: " + products!!.get(pos).quantityAvail)
//            holder.txtQnt.text = "Qty: " + products!!.get(pos).quantityAvail
            if(products!!.get(pos).storeAvailableQuantity != null && !products!!.get(pos).storeAvailableQuantity.equals("") && !products!!.get(pos).storeAvailableQuantity.equals("0")){
                holder.txtQnt.text = "Qty: " + products!!.get(pos).storeAvailableQuantity
            }else{
                holder.txtQnt.text = "Qty: " + products!!.get(pos).quantity
            }
        }


        holder.txtCode.setText("Code: " + products!!.get(pos).productId)
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtCode = itemView.findViewById(R.id.txt_code) as AppCompatTextView
        val imgColorCode = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txtQnt = itemView.findViewById(R.id.txt_qnt) as AppCompatTextView
        val txtMrp = itemView.findViewById(R.id.txt_mrp) as AppCompatTextView
        val txtAvail = itemView.findViewById(R.id.txt_avail) as AppCompatTextView
        val txt = itemView.findViewById(R.id.textView71) as AppCompatTextView


    }
}