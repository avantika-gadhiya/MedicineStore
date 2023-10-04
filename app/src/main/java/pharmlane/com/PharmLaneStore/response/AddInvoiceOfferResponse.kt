package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class AddInvoiceOfferResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("response")
    @Expose
    private var response: String? = null

    @SerializedName("data")
    @Expose
    private var data: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(status: Boolean?) {
        this.status = status
    }

    fun getResponse(): String? {
        return response
    }

    fun setResponse(response: String?) {
        this.response = response
    }

    fun getData(): String? {
        return data
    }

    fun setData(data: String?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }


}