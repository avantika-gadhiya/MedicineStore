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

class MakeOfferOrderAdapter(
        var context: Context,
        var products: List<OrderDetailResponse.Product>?
) : RecyclerView.Adapter<MakeOfferOrderAdapter.MyHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MakeOfferOrderAdapter.MyHolder {
        val v =
                LayoutInflater.from(parent.context).inflate(R.layout.item_your_offer, parent, false)
        Log.e("WHICHADAPTEREEE", "MakeOfferOrderAdapter")

        return MakeOfferOrderAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return products!!.size

    }

    override fun onBindViewHolder(holder: MakeOfferOrderAdapter.MyHolder, position: Int) {
        val clr = ("#" + products!!.get(position).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.imgClrCode.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)

        val isAvail = products!!.get(position).availableProduct

        Log.d("TAG", "111-----" + products!!.get(position).productMrp)
        Log.d("TAG", "222-----" + products!!.get(position).quantity)
        Log.e("HellOCOlors", "isAvail-----" + products!!.get(position).productId + "   " + isAvail)

        if (!isAvail.equals(null)) {
            if (products!!.get(position).availableProduct!!.equals("1")) {
                holder.txtAvail.setText(R.string.available)
                holder.txtAvail.visibility = View.VISIBLE
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.green_txt))
                holder.txt_rupee.visibility = View.VISIBLE
                holder.txtRs.visibility = View.VISIBLE
                Log.e("HellOCOlors", "MRP!!   " + products!!.get(position).productMrp)
                holder.txtRs.text = products!!.get(position).productMrp

            } else if (products!!.get(position).availableProduct!!.equals("2")) {
                holder.txtAvail.setText(R.string.available)
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.green_txt))
                holder.txtAvail.visibility = View.VISIBLE
                holder.txt_rupee.visibility = View.VISIBLE
                holder.txtRs.visibility = View.VISIBLE
                Log.e("HellOCOlors", "MRP@@   " + products!!.get(position).productMrp)
                holder.txtRs.text = products!!.get(position).productMrp

            } else {
                holder.txtAvail.setText(R.string.not_available)
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.red_txt))
                holder.txtAvail.visibility = View.VISIBLE
                holder.txt_rupee.visibility = View.GONE
                holder.txtRs.visibility = View.GONE
            }
        } else {
            Log.e("HellOCOlors", "isAvail ELSEE-----" + products!!.get(position).productId + "   " + isAvail)
            holder.txtAvail.visibility = View.GONE
            holder.txt_rupee.visibility = View.GONE
            holder.txtRs.text = ""
        }


        holder.txtCode.text = "Code: " + products!!.get(position).productId

        if (products!!.get(position).quantityType.equals("0")) {
            holder.txtQnt.text = "Qty: Full"
        } else {
            Log.e("WHICHADAPTEREEE", "MakeOfferOrderAdapter    "+products!!.get(position).quantityAvail)

            if(products!!.get(position).quantityAvail != null && !products!!.get(position).quantityAvail.equals("")){
                holder.txtQnt.text = "Qty: " + products!!.get(position).quantityAvail

            }else{
                holder.txtQnt.text = "Qty: " + products!!.get(position).quantity

            }

        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtCode = itemView.findViewById(R.id.txt_code) as AppCompatTextView
        val txtQnt = itemView.findViewById(R.id.txt_qnt) as AppCompatTextView
        val txtAvail = itemView.findViewById(R.id.txt_avail) as AppCompatTextView
        val txtRs = itemView.findViewById(R.id.txt_rs) as AppCompatTextView
        val imgClrCode = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txt_rupee = itemView.findViewById(R.id.rupee) as AppCompatTextView
        val txt_edit_qty = itemView.findViewById(R.id.txt_edit_qty) as AppCompatTextView
    }
}