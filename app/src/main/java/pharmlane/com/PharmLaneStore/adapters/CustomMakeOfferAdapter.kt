package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.response.OrderDetailResponse
import java.lang.Exception

class CustomMakeOfferAdapter(
        var context: Context,
        var clikIntrfc: ClikIntrfc,
        var products: List<OrderDetailResponse.Product>?
) : RecyclerView.Adapter<CustomMakeOfferAdapter.MyHolder>() {


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CustomMakeOfferAdapter.MyHolder {
        val v =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_make_offer_recycl_test, parent, false)
        return CustomMakeOfferAdapter.MyHolder(v)
        Log.e("WHICHADAPTEREEE", "CustomMakeOfferAdapter")

        return CustomMakeOfferAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun onBindViewHolder(holder: CustomMakeOfferAdapter.MyHolder, position: Int) {

        val avail = products!!.get(position).availableProduct
        val presc = products!!.get(position).prescrStatus

        if (!avail.equals(null)) {
            if (products!!.get(position).availableProduct!!.equals("1")) {

                holder.txtQTY.visibility = View.VISIBLE
                holder.txtAvail.visibility = View.VISIBLE
                holder.txtAvail.setText(R.string.available)
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.green_color))

                holder.imgRej.visibility = View.GONE
                holder.imgAcc.visibility = View.GONE

                if (!products!!.get(position).productMrp.equals("0")) {
                    holder.txtRs.visibility = View.VISIBLE
                    holder.txtTotal.visibility = View.VISIBLE
                    holder.txtTotal.setText(" " + products?.get(position)!!.productMrp)
                } else {

                    holder.txtRs.visibility = View.GONE
                    holder.txtTotal.visibility = View.GONE
                }
            }else {
                holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.review_red_btn))
                holder.txtAvail.text =context.resources.getString(R.string.not_available)
                holder.txtAvail.visibility = View.VISIBLE
                holder.imgRej.visibility = View.GONE
                holder.imgAcc.visibility = View.GONE
                holder.txtQTY.visibility = View.VISIBLE

            }
        }
        if (!presc.equals(null)) {
            if (products!!.get(position).prescrStatus!!.equals("1")) {

                holder.txtPrscOnly.visibility = View.VISIBLE
            } else
                holder.txtPrscOnly.visibility = View.INVISIBLE
        }

        val clr = ("#" + products!!.get(position).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.clrCode.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)


        if (!products!!.get(position).productBrand.equals("")) {
            holder.txtBrandName.text = products!!.get(position).productBrand
            holder.txtBrandName.visibility = View.VISIBLE
        } else {
            holder.txtBrandName.visibility = View.GONE
        }
        if (products!!.get(position).productContent.equals("") && products!!.get(position).productUnitName.equals("")) {
            holder.txtQntty.setText(products!!.get(position).productPackName.toString())
        } else if (products!!.get(position).productContent.equals("")) {
            holder.txtQntty.setText(products!!.get(position).productUnitName.toString() + " " +
                    products!!.get(position).productPackName.toString())
        } else if (products!!.get(position).productUnitName.equals("")) {
            holder.txtQntty.setText(products!!.get(position).productContent.toString() + " " +
                    products!!.get(position).productPackName.toString())
        } else {
            holder.txtQntty.setText(products!!.get(position).productContent.toString() + " " +
                    products!!.get(position).productUnitName.toString() + " " +
                    products!!.get(position).productPackName.toString())
        }


        holder.txtProductName.text = products!!.get(position).productName

        if(!products!!.get(position).storeAvailableQuantity.equals("") && products!!.get(position).storeAvailableQuantity != null){
            holder.txtQTY.text = "QTY: "+products!!.get(position).storeAvailableQuantity
        }else {
            holder.txtQTY.text = "QTY: "+products!!.get(position).quantity

        }


        holder.imgAcc.setOnClickListener {

            try {
                clikIntrfc.openDialog(position, products?.get(position)!!.quantity.toString(), "", "acc")
            }catch (e:Exception){

                Log.e("HElloposs","Wrorg   "+e)
            }


        }
        holder.imgRej.setOnClickListener {
            clikIntrfc.openDialog(position, products?.get(position)!!.quantity.toString(), "", "rej")
            products!![position].availableProduct = "0"
            holder.txtAvail.setTextColor(ContextCompat.getColor(context, R.color.review_red_btn))
            holder.txtAvail.text =context.resources.getString(R.string.not_available)
            holder.txtAvail.visibility = View.VISIBLE
            holder.txtQTY.visibility = View.VISIBLE
            holder.imgRej.visibility = View.GONE
            holder.imgAcc.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            try {
                clikIntrfc.openDialog(position, products?.get(position)!!.quantity.toString(), products!!.get(position).productMrp!!, "acc")
            }catch (e:Exception){
            }
        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtPrscOnly = itemView.findViewById(R.id.txt_presc_only) as AppCompatTextView
        val txtAvail = itemView.findViewById(R.id.txt_avail) as AppCompatTextView
        val clrCode = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txtRs = itemView.findViewById(R.id.textView71) as AppCompatTextView
        val txtTotal = itemView.findViewById(R.id.textView72) as AppCompatTextView
        val txtProductName = itemView.findViewById(R.id.txt_product_name) as AppCompatTextView
        val txtBrandName = itemView.findViewById(R.id.txt_brand_name) as AppCompatTextView
        val txtQntty = itemView.findViewById(R.id.txt_qntty) as AppCompatTextView
        val txtQTY = itemView.findViewById(R.id.txt_qntt) as AppCompatTextView
        val imgRej = itemView.findViewById(R.id.img_rej) as AppCompatImageView
        val imgAcc = itemView.findViewById(R.id.img_acc) as AppCompatImageView

    }

    interface ClikIntrfc {
        fun openDialog(pos: Int, quantity: String, mrp: String, str: String)
    }
}