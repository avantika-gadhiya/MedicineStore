package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoreRatingResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("response")
    @Expose
    var response: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {
        @SerializedName("rate_experience")
        @Expose
        var rateExperience: String? = null

        @SerializedName("review")
        @Expose
        var review: String? = null

        @SerializedName("created_date")
        @Expose
        var createdDate: String? = null

        @SerializedName("custo_name")
        @Expose
        var custoName: String? = null

        @SerializedName("store_name")
        @Expose
        var storeName: String? = null
    }
}