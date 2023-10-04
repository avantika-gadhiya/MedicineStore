package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.interfaces.FiltersInterFace
import pharmlane.com.PharmLaneStore.response.FilterResponse

class ByCustomerNameAdapter(var context: Context,
                            var storeNameList: List<FilterResponse.StoreName>,val filterListener: FiltersInterFace
) : RecyclerView.Adapter<ByStatusAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ByStatusAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_by_type, parent, false)
        return ByStatusAdapter.MyHolder(v)
    }

    var status_array: ArrayList<String> = arrayListOf()
    override fun getItemCount(): Int {
        return storeNameList.size
    }

    override fun onBindViewHolder(holder: ByStatusAdapter.MyHolder, pos: Int) {

        Log.d("TAG", "ADD===A-->" + storeNameList.get(pos))
        holder.txt.text = storeNameList.get(pos).value
        holder.txt.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                status_array.add(storeNameList[pos].key.toString())
                val allIds = TextUtils.join(",", status_array)
                filterListener.onClickCustomerName(allIds)

            } else {

                status_array.remove(storeNameList[pos].key.toString())
                val allIds = TextUtils.join(",", status_array)
                filterListener.onClickCustomerName(allIds)
            }

        }

    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txt = itemView.findViewById(R.id.checkBox1) as CheckBox
    }
}