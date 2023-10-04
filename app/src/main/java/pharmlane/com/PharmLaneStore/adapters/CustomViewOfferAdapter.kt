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

class CustomViewOfferAdapter(
    var context: Context,
    var products: List<OrderDetailResponse.Product>?,
    var viewall: String
) : RecyclerView.Adapter<CustomViewOfferAdapter.MyHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewOfferAdapter.MyHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_custm_your_offer, parent, false)
        Log.e("WHICHADAPTEREEE", "CustomViewOfferAdapter")
        return CustomViewOfferAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        if (viewall.equals("0") && products!!.size > 3)
            return 3
        else
            return products!!.size
    }

    override fun onBindViewHolder(holder: CustomViewOfferAdapter.MyHolder, pos: Int) {
        val clr = ("#" + products!!.get(pos).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.imageView.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)
        val presc = products!!.get(pos).prescrStatus

        val avail = products!!.get(pos).availableProduct

        if (!avail.equals(null)) {
            if (products!!.get(pos).availableProduct!!.equals("1")) {
                holder.txtAvail.text = context.resources.getString(R.string.available)
                holder.txtAvail.setTextColor(context.resources.getColor(R.color.green_txt))

                holder.txtRs.visibility = View.VISIBLE
                holder.txtPrice.setText(products?.get(pos)!!.productMrp)
            }else{
                holder.txtAvail.text = context.resources.getString(R.string.not_available)
                holder.txtAvail.setTextColor(context.resources.getColor(R.color.red_txt))
                holder.txtRs.visibility = View.INVISIBLE
            }
        }


        if (!products!!.get(pos).productBrand.equals("")) {
            holder.txtBrandName.text = products!!.get(pos).productBrand
            holder.txtBrandName.visibility = View.VISIBLE
        }else{
            holder.txtBrandName.visibility = View.GONE
        }
        if (products!!.get(pos).productContent.equals("") && products!!.get(pos).productUnitName.equals("")) {
            holder.txtQntty.setText(products!!.get(pos).productPackName.toString())
        }else if (products!!.get(pos).productContent.equals("") ){
            holder.txtQntty.setText(products!!.get(pos).productUnitName.toString() + " " +
                    products!!.get(pos).productPackName.toString())
        }else if (products!!.get(pos).productUnitName.equals("") ){
            holder.txtQntty.setText(products!!.get(pos).productContent.toString() + " " +
                    products!!.get(pos).productPackName.toString())
        }else{
            holder.txtQntty.setText(products!!.get(pos).productContent.toString() + " " +
                    products!!.get(pos).productUnitName.toString() + " " +
                    products!!.get(pos).productPackName.toString())
        }

        if (!presc.equals(null)) {
            if (products!!.get(pos).prescrStatus!!.equals("1")) {
                holder.txt_presc_only.visibility = View.VISIBLE
            } else
                holder.txt_presc_only.visibility = View.INVISIBLE
        }

        holder.txtProductName.text = products!!.get(pos).productName
        holder.txtQnttys.text = "QTY: "+ products!!.get(pos).storeAvailableQuantity


//        if (products!!.get(pos).quantityType.equals("0")) {
//            holder.txtQnt.setText("Qty: Full")
//        } else {
////            holder.txtQnt.setText("Qty: " + products!!.get(pos).quantityAvail)
////            holder.txtQnt.text = "Qty: " + products!!.get(pos).quantityAvail
//            if(products!!.get(pos).quantityAvail != null && !products!!.get(pos).quantityAvail.equals("")){
//                holder.txtQnt.text = "Qty: " + products!!.get(pos).quantityAvail
//
//            }else{
//                holder.txtQnt.text = "Qty: " + products!!.get(pos).quantity
//
//            }
//        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageView = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txtQnttys = itemView.findViewById(R.id.txt_qntt) as AppCompatTextView
        val txtQntty = itemView.findViewById(R.id.txt_qntty) as AppCompatTextView
        val txtBrandName = itemView.findViewById(R.id.txt_brand_name) as AppCompatTextView
        val txtProductName = itemView.findViewById(R.id.txt_product_name) as AppCompatTextView
        val txtAvail = itemView.findViewById(R.id.txt_avail) as AppCompatTextView
        val txt_presc_only = itemView.findViewById(R.id.txt_presc_only) as AppCompatTextView
        val txtPrice = itemView.findViewById(R.id.txt_price) as AppCompatTextView
        val txtRs = itemView.findViewById(R.id.txt_rs) as AppCompatTextView
    }
}