package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ViewAllStoreOrderResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("response")
    @Expose
    var response: String? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    inner class Datum {

        @SerializedName("status_view_all")
        @Expose
        var statusViewAll: Int? = null
        @SerializedName("order_id")
        @Expose
        var orderId: String? = null
        @SerializedName("order_no")
        @Expose
        var orderNo: String? = null
        @SerializedName("created_date")
        @Expose
        var createdDate: String? = null
        @SerializedName("discount_percentage")
        @Expose
        var discountPercentage: String? = null
        @SerializedName("order_status")
        @Expose
        var orderStatus: String? = null
        @SerializedName("order_type")
        @Expose
        var orderType: String? = null
        @SerializedName("order_type_name")
        @Expose
        var orderTypeName: String? = null
        @SerializedName("request_status")
        @Expose
        var requestStatus: String? = null
        @SerializedName("re_order")
        @Expose
        var reOrder: String? = null
        @SerializedName("delivery_date")
        @Expose
        var deliveryDate: String? = null
        @SerializedName("delivery_time_start")
        @Expose
        var deliveryTimeStart: String? = null
        @SerializedName("delivery_time_end")
        @Expose
        var deliveryTimeEnd: String? = null
        @SerializedName("delivery_type")
        @Expose
        var deliveryType: String? = null
        @SerializedName("delivery_type_name")
        @Expose
        var deliveryTypeName: String? = null

        @SerializedName("todate_fromdate")
        @Expose
        var todateFromdate: String? = null
        @SerializedName("offer_delivery_preference")
        @Expose
        var offerDeliveryPreference: String? = null
        @SerializedName("offer_delivery_preference_name")
        @Expose
        var offerDeliveryPreferenceName: String? = null
        @SerializedName("offer_delivery_time_start")
        @Expose
        var offerDeliveryTimeStart: String? = null
        @SerializedName("offer_delivery_time_end")
        @Expose
        var offerDeliveryTimeEnd: String? = null
        @SerializedName("offer_delivery_date")
        @Expose
        var offerDeliveryDate: String? = null
        @SerializedName("offer_final_amount")
        @Expose
        var offerFinalAmount: String? = null

        @SerializedName("offer_todate_fromdate")
        @Expose
        var offerTodateFromdate: String? = null
        @SerializedName("order_delivered")
        @Expose
        var orderDelivered: String? = null
        @SerializedName("receiver_verify_date")
        @Expose
        var receiverVerifyDate: String? = null
        @SerializedName("payment_verify_date")
        @Expose
        var paymentVerifyDate: String? = null
        @SerializedName("final_amount")
        @Expose
        var finalAmount :String? = null

    }
}