package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class RegisterResponse {

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

        @SerializedName("owner_name")
        @Expose
        var ownerName: String? = null
        @SerializedName("phone")
        @Expose
        var phone: String? = null
        @SerializedName("store_name")
        @Expose
        var storeName: String? = null
        @SerializedName("store_id")
        @Expose
        var storeId: Int? = null

    }

}