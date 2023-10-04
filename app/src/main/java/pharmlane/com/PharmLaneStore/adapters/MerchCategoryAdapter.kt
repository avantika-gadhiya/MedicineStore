package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.response.GetAllDropdownResponse
import kotlin.collections.ArrayList

class MerchCategoryAdapter(
    var context: Context,
    var arrMerchList: List<GetAllDropdownResponse.MerchandiseCategory>,
    var arrayOf: ArraylistOfCategory,
    var selectedCat: String
) : RecyclerView.Adapter<MerchCategoryAdapter.MyHolder>() {

    var addList: ArrayList<String>? = arrayListOf()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_merch_category, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return arrMerchList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.txttitle.setText(arrMerchList.get(position).name.toString())



        holder.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (addList!!.size == 3 && isChecked) {
                buttonView.isChecked = false
                Toast.makeText(
                    context,
                    "You are allowed maximum 3 Merchandise categories!", Toast.LENGTH_SHORT
                ).show()
            } else if (isChecked) {
             addList?.add(arrMerchList.get(position).name.toString())
                arrayOf.add(arrMerchList.get(position).id.toString(),arrMerchList.get(position).name.toString())
            } else if (!isChecked) {
             addList?.remove(arrMerchList.get(position).name.toString())
                arrayOf.remove(arrMerchList.get(position).id.toString(),arrMerchList.get(position).name.toString())
            }
        }

        if(selectedCat.contains(arrMerchList.get(position).name.toString(),true)){
            holder.checkbox.isChecked = true
        }else {
            holder.checkbox.isChecked = false

        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txttitle = itemView.findViewById(R.id.txt_title) as AppCompatTextView
        val checkbox = itemView.findViewById(R.id.check_box) as CheckBox
    }

    interface ArraylistOfCategory {

        fun add(position: String,position1: String)
        fun remove(position: String,position1: String)

    }
}