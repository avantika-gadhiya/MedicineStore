package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GenerateOtpResponse {
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

        @SerializedName("receiver_opt")
        @Expose
        var receiverOpt: Int? = null
        @SerializedName("receiver_opt_phone ")
        @Expose
        var receiverOptPhone: String? = null

    }
}