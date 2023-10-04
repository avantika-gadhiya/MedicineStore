package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class VerifyPaymentResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("response")
    @Expose
    var response: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null
    @SerializedName("message")
    @Expose
    var message: String? = null


    inner class Data {

        @SerializedName("payment_verify_date")
        @Expose
        var paymentVerifyDate: String? = null

    }

}