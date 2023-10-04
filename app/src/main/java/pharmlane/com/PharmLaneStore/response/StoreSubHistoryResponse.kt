package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class StoreSubHistoryResponse {
    @SerializedName("status")
    @Expose
   var status: Boolean? = null

    @SerializedName("response")
    @Expose
    var response: String? = null

    @SerializedName("data")
    @Expose
     var data: List<Datum>? = null


    inner class Datum  {


        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("store_id")
        @Expose
        var store_id: String? = null

        @SerializedName("subscription_plan_id")
        @Expose
        var subscription_plan_id: String? = null

        @SerializedName("Pmethod_name")
        @Expose
        var Pmethod_name: String? = null

        @SerializedName("upi_id")
        @Expose
        var upi_id: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("expire_date")
        @Expose
        var expiredate: String? = null

        @SerializedName("plan")
        @Expose
        var plan: String? = null

        @SerializedName("price")
        @Expose
        var price: String? = null

        @SerializedName("storename")
        @Expose
        var storename: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

        @SerializedName("created_date")
        @Expose
        var created_date: String? = null

        @SerializedName("updated_date")
        @Expose
        var updated_date: String? = null

    }
}