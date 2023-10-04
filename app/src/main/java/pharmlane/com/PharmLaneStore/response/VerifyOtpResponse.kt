package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VerifyOtpResponse {
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

    class Data {
        @SerializedName("receiver_opt_date")
        @Expose
        var receiverOptDate: String? = null

    }
}