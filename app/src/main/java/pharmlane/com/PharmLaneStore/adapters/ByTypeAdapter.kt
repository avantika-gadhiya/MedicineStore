package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.interfaces.FiltersInterFace
import pharmlane.com.PharmLaneStore.response.FilterResponse

class ByTypeAdapter(
        var context: Context,
        var typeList: List<FilterResponse.OrderType>, val filterListener: FiltersInterFace
) : RecyclerView.Adapter<ByTypeAdapter.MyHolder>() {

    var type_array: ArrayList<String> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ByTypeAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_by_type, parent, false)
        return ByTypeAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: ByTypeAdapter.MyHolder, pos: Int) {

        holder.txt.text = typeList[pos].value

        holder.txt.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                type_array.add(typeList[pos].key.toString())
                val allIds = TextUtils.join(",", type_array)
                filterListener.onClickType(allIds)
            } else {
                type_array.remove(typeList[pos].key.toString())
                val allIds = TextUtils.join(",", type_array)
                filterListener.onClickType(allIds)
            }
        }
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txt = itemView.findViewById(R.id.checkBox1) as CheckBox
    }
}