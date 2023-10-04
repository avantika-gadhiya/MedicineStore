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

class ProductsOrederedAdapter(
    var context: Context,
    var products: List<OrderDetailResponse.Product>?,
    var viewall: String
) : RecyclerView.Adapter<ProductsOrederedAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ProductsOrederedAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products_ordered, parent, false)
        return ProductsOrederedAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {

        if (viewall.equals("0") && products!!.size > 3)
            return 3
        else
            return products!!.size

    }

    override fun onBindViewHolder(holder: ProductsOrederedAdapter.MyHolder, pos: Int) {


        val clr = ("#" + products!!.get(pos).color!!)
        val color = Color.parseColor(clr)

        val drawableBg = holder.imageView.getBackground().mutate() as GradientDrawable
        drawableBg.setColor(color)
        Log.e("HelloReOrde", "onBindViewHolder   "+products!!.get(pos).productUnitName.toString() + " " +
                products!!.get(pos).productPackName.toString())


        if (!products!!.get(pos).productBrand.equals("")) {
            holder.txtBrandName.text = products!!.get(pos).productBrand
        }else{
            holder.txtBrandName.visibility = View.GONE
        }
        if (products!!.get(pos).productContent.equals("") && products!!.get(pos).productUnitName.equals("")) {
            holder.txtQntty.setText(products!!.get(pos).productPackName.toString())
            Log.e("HelloReOrde","IFF   "+products!!.get(pos).productId)
        }else if (products!!.get(pos).productContent.equals("") ){
            holder.txtQntty.setText(products!!.get(pos).productUnitName.toString() + " " +
                    products!!.get(pos).productPackName.toString())

            Log.e("HelloReOrde","ELSE IFF   "+products!!.get(pos).productId)
        }else if (products!!.get(pos).productUnitName.equals("") ){
            holder.txtQntty.setText(products!!.get(pos).productContent.toString() + " " +
                    products!!.get(pos).productPackName.toString())
            Log.e("HelloReOrde","ELSE IFF 222  "+products!!.get(pos).productId)

        }else{
            holder.txtQntty.setText(products!!.get(pos).productContent.toString() + " " +
                    products!!.get(pos).productUnitName.toString() + " " +
                    products!!.get(pos).productPackName.toString())

            Log.e("HelloReOrde","ELSE   "+products!!.get(pos).productId)
        }


        holder.txtProductName.text = products!!.get(pos).productName
        holder.txtQty.text = products!!.get(pos).quantity
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val imageView = itemView.findViewById(R.id.img_clr_code) as AppCompatTextView
        val txtQty = itemView.findViewById(R.id.txt_qty) as AppCompatTextView
        val txtQntty = itemView.findViewById(R.id.txt_qntty) as AppCompatTextView
        val txtBrandName = itemView.findViewById(R.id.txt_brand_name) as AppCompatTextView
        val txtProductName = itemView.findViewById(R.id.txt_product_name) as AppCompatTextView
    }

}