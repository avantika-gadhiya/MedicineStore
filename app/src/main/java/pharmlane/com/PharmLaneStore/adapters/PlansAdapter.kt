package pharmlane.com.PharmLaneStore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import pharmlane.com.PharmLaneStore.R
import pharmlane.com.PharmLaneStore.model.Register
import pharmlane.com.PharmLaneStore.response.AllStoreSubPlanResponse

class PlansAdapter(
        var context: Context,
        var arraSubs: List<AllStoreSubPlanResponse.Datum>, var plans: clickedPlan) : RecyclerView.Adapter<PlansAdapter.MyHolder>() {

    var array = ArrayList<Register.payment>()
    var payMents = Register.payment()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_plans, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return arraSubs.size
    }

    override fun onBindViewHolder(holder: PlansAdapter.MyHolder, pos: Int) {

        holder.txt_1_plan.text = arraSubs[pos].plan!!
        holder.txt_1_plan_price.text ="Rs."+ arraSubs[pos].price!!
        holder.txt_desc_1_plan.text = arraSubs[pos].description!!

        holder.con_first_plan.setOnClickListener {
            plans.my_plan(arraSubs[pos].id!!,arraSubs[pos].plan!!, arraSubs[pos].price!!,arraSubs[pos].description!!)
        }


    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val txt_1_plan = itemView.findViewById(R.id.txt_1_plan) as AppCompatTextView
        val txt_1_plan_price = itemView.findViewById(R.id.txt_1_plan_price) as AppCompatTextView
        val txt_desc_1_plan = itemView.findViewById(R.id.txt_desc_1_plan) as AppCompatTextView
        val con_first_plan = itemView.findViewById(R.id.con_first_plan) as ConstraintLayout

    }

    interface clickedPlan {
        fun my_plan(planId: String, planName: String, planPrice: String, planDesc: String)
    }

}