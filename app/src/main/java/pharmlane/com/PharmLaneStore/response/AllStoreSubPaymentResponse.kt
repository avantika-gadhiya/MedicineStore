package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AllStoreSubPaymentResponse {
    @SerializedName("status")
    @Expose
   var status: Boolean? = null

    @SerializedName("response")
    @Expose
    var response: String? = null

    @SerializedName("data")
    @Expose
     var data: List<Datum>? = null

    @SerializedName("current_plan")
    @Expose
    val currentPlan: List<CurrentPlan>? = null

    inner class Datum  {


        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("upi_id")
        @Expose
        var upi_id: String? = null

        @SerializedName("qr_img")
        @Expose
        var qr_img: String? = null

        @SerializedName("created_date")
        @Expose
        var created_date: String? = null

        @SerializedName("updated_date")
        @Expose
        var updated_date: String? = null

    }

    inner class CurrentPlan {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("store_id")
        @Expose
        var storeId: String? = null

        @SerializedName("subscription_plan_id")
        @Expose
        var subscriptionPlanId: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("expire_date")
        @Expose
        var expireDate: String? = null

        @SerializedName("created_date")
        @Expose
        var createdDate: String? = null

        @SerializedName("plan")
        @Expose
        var plan: String? = null

        @SerializedName("price")
        @Expose
        var price: String? = null
    }
}