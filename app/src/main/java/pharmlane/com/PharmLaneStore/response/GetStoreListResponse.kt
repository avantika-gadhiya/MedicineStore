package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetStoreListResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("response")
    @Expose
    var response: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null


    inner class Data {

        @SerializedName("direct_order")
        @Expose
        var directOrder: List<DirectOrder>? = null
        @SerializedName("pending_order")
        @Expose
        var pendingOrder: List<PendingOrder>? = null
        @SerializedName("offers_made")
        @Expose
        var offersMade: List<OffersMade>? = null

    }

    inner class DirectOrder {

        @SerializedName("status_view_all")
        @Expose
        val statusViewAll: Int? = null
        @SerializedName("order_id")
        @Expose
        val orderId: String? = null
        @SerializedName("order_no")
        @Expose
        val orderNo: String? = null
        @SerializedName("created_date")
        @Expose
        val createdDate: String? = null
        @SerializedName("order_status")
        @Expose
        val orderStatus: String? = null
        @SerializedName("order_type")
        @Expose
        val orderType: String? = null
        @SerializedName("order_type_name")
        @Expose
        val orderTypeName: String? = null
        @SerializedName("request_status")
        @Expose
        val requestStatus: String? = null
        @SerializedName("re_order")
        @Expose
        val reOrder: String? = null

    }

    inner class OffersMade {
        @SerializedName("status_view_all")
        @Expose
         val statusViewAll: Int? = null

        @SerializedName("order_id")
        @Expose
         val orderId: String? = null

        @SerializedName("order_no")
        @Expose
         val orderNo: String? = null

        @SerializedName("created_date")
        @Expose
         val createdDate: String? = null

        @SerializedName("to_delivery_date")
        @Expose
         val toDeliveryDate: String? = null

        @SerializedName("order_status")
        @Expose
         val orderStatus: String? = null

        @SerializedName("discount_percentage")
        @Expose
         val discountPercentage: String? = null

        @SerializedName("offer_delivery_preference")
        @Expose
         val offerDeliveryPreference: String? = null

        @SerializedName("offer_delivery_preference_name")
        @Expose
         val offerDeliveryPreferenceName: String? = null

        @SerializedName("timeline_flag")
        @Expose
         val timelineFlag: String? = null

        @SerializedName("order_type")
        @Expose
         val orderType: String? = null

        @SerializedName("order_type_name")
        @Expose
         val orderTypeName: String? = null

        @SerializedName("request_status")
        @Expose
         val requestStatus: String? = null

        @SerializedName("re_order")
        @Expose
         val reOrder: String? = null

        @SerializedName("delivery_date")
        @Expose
         val deliveryDate: String? = null

        @SerializedName("delivery_time_start")
        @Expose
         val deliveryTimeStart: String? = null

        @SerializedName("delivery_time_end")
        @Expose
         val deliveryTimeEnd: String? = null

        @SerializedName("todate_fromdate")
        @Expose
         val todateFromdate: String? = null

        @SerializedName("payment_name")
        @Expose
         val paymentName: String? = null

        @SerializedName("delivery_type")
        @Expose
         val deliveryType: String? = null

        @SerializedName("delivery_type_name")
        @Expose
         val deliveryTypeName: String? = null

        @SerializedName("order_delivered")
        @Expose
         val orderDelivered: String? = null

        @SerializedName("is_skip_receiver_opt_date")
        @Expose
         val isSkipReceiverOptDate: String? = null

        @SerializedName("receiver_verify_date")
        @Expose
         val receiverVerifyDate: String? = null

        @SerializedName("payment_verify_date")
        @Expose
         val paymentVerifyDate: String? = null

        @SerializedName("offer_delivery_date")
        @Expose
         val offerDeliveryDate: String? = null

        @SerializedName("offer_delivery_time_start")
        @Expose
         val offerDeliveryTimeStart: String? = null

        @SerializedName("offer_delivery_time_end")
        @Expose
         val offerDeliveryTimeEnd: String? = null

        @SerializedName("offer_to_delivery_date")
        @Expose
         val offerToDeliveryDate: String? = null

        @SerializedName("offer_todate_fromdate")
        @Expose
         val offerTodateFromdate: String? = null

        @SerializedName("offer_final_amount")
        @Expose
         val offerFinalAmount: String? = null

        @SerializedName("offer_price_change_by_store")
        @Expose
         val offerPriceChangeByStore: String? = null
    }

    inner class PendingOrder {

        @SerializedName("status_view_all")
        @Expose
        val statusViewAll: Int? = null
        @SerializedName("order_id")
        @Expose
        val orderId: String? = null
        @SerializedName("order_no")
        @Expose
        val orderNo: String? = null
        @SerializedName("created_date")
        @Expose
        val createdDate: String? = null
        @SerializedName("order_status")
        @Expose
        val orderStatus: String? = null
        @SerializedName("order_type")
        @Expose
        val orderType: String? = null
        @SerializedName("order_type_name")
        @Expose
        val orderTypeName: String? = null
        @SerializedName("request_status")
        @Expose
        val requestStatus: String? = null
        @SerializedName("re_order")
        @Expose
        val reOrder: String? = null
    }
}