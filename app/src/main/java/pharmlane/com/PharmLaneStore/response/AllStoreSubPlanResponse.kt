package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AllStoreSubPlanResponse {
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

        @SerializedName("plan")
        @Expose
        var plan: String? = null

        @SerializedName("plan_type")
        @Expose
        var plan_type: String? = null

        @SerializedName("price")
        @Expose
        var price: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("created_date")
        @Expose
        var created_date: String? = null

        @SerializedName("updated_date")
        @Expose
        var updated_date: String? = null

    }
}