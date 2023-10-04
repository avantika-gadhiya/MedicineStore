package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.activities.StoreDetailActivity
import pharmlane.com.PharmLaneStore.response.StoreListResponse
import com.bumptech.glide.Glide

class AvailableStoreAdapter(
    var context: Context,
   var storeListArray: List<StoreListResponse.Datum>
) : RecyclerView.Adapter<AvailableStoreAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycl_store, parent, false)
        return AvailableStoreAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return storeListArray.size
    }

    fun updateList(list: List<StoreListResponse.Datum>) {
        storeListArray = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, pos: Int) {

        val locaTion =
            storeListArray.get(pos).shopNo + ", " +
                    storeListArray.get(pos).street + ", " +
                    storeListArray.get(pos).area+ ", " +
                    storeListArray.get(pos).landmark + ", " +
                    storeListArray.get(pos).city + ", " +
                    storeListArray.get(pos).zipcode

        Glide.with(context)  //2
            .load(storeListArray.get(pos).storePhoto) //3
            .centerCrop() //4
            .placeholder(R.drawable.ic_launcher_background) //5
            .error(R.drawable.ic_launcher_background) //6
            .fallback(R.drawable.ic_launcher_background) //7
            .into(holder.imageView) //8

        holder.txtStoreName.setText(storeListArray.get(pos).name)
        holder.txtStoreAdd.setText(locaTion)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, StoreDetailActivity::class.java)
                .putExtra("phoneNumber", storeListArray.get(pos).phone))
        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageView = itemView.findViewById(R.id.img_store) as AppCompatImageView
        val txtStoreName = itemView.findViewById(R.id.txt_stor_nm) as AppCompatTextView
        val txtStoreAdd = itemView.findViewById(R.id.txt_store_add) as AppCompatTextView

    }
}