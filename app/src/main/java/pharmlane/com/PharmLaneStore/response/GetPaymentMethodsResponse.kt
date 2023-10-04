package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetPaymentMethodsResponse {
    @SerializedName("status")
    @Expose
     var status: Boolean? = null
    @SerializedName("response")
    @Expose
     var response: String? = null
    @SerializedName("data")
    @Expose
     var data: List<Data>? = null

    inner class Data {

        @SerializedName("method_name")
        @Expose
        var method_name: String? = null

        @SerializedName("payment_method_id")
        @Expose
        var paymentMethodId: String? = null

        @SerializedName("upi_id")
        @Expose
        var upi_id: String? = null

        @SerializedName("qr_img")
        @Expose
        var qr_img: String? = null

        @SerializedName("isSelected")
        @Expose
        var isSelected: String? = null
    }
}