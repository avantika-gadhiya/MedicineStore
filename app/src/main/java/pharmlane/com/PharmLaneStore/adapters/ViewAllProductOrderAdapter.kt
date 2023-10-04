package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.response.OrderDetailResponse

class ViewAllProductOrderAdapter (
        var context: Context,
        var products: List<OrderDetailResponse.Product>?,
        var viewall: String
) : RecyclerView.Adapter<ViewAllProductOrderAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewAllProductOrderAdapter.MyHolder {
        val v =
                LayoutInflater.from(parent.context).inflate(R.layout.item_product_order, parent, false)
        Log.e("WHICHADAPTEREEE", "ViewAllProductOrderAdapter")
        return ViewAllProductOrderAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        if (viewall.equals("0") && products!!.size > 3)
            return 3
        else
            return products!!.size
    }

    override fun onBindViewHolder(holder: ViewAllProductOrderAdapter.MyHolder, pos: Int) {

        val clr = ("#" + products!!.get(pos).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.img_clr_code.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)

        val avail = products!!.get(pos).availableProduct

        holder.txt_product_name.setText("Code: " + products!!.get(pos).productId)

        if (products!!.get(pos).quantityType.equals("0")) {
            holder.txt_qntt.setText("Qty: Full")
        } else {
            holder.txt_qntt.setText("Qty: " + products!!.get(pos).quantity)
        }
       // holder.txtCode.setText("Code: " + products!!.get(pos).productId)
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txt_product_name = itemView.findViewById(R.id.txt_product_name) as AppCompatTextView
        val img_clr_code = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txt_qntt = itemView.findViewById(R.id.txt_qntt) as AppCompatTextView
    }
}