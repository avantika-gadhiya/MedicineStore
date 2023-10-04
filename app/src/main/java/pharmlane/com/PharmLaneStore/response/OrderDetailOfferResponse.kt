package pharmlane.com.PharmLaneStore.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class OrderDetailOfferResponse {
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

        @SerializedName("order_id")
        @Expose
        var orderId: String? = null
        @SerializedName("customer_name")
        @Expose
        var customerName: String? = null
        @SerializedName("order_no")
        @Expose
        var orderNo: String? = null
        @SerializedName("created_date")
        @Expose
        var createdDate: String? = null
        @SerializedName("order_status")
        @Expose
        var orderStatus: String? = null
        @SerializedName("request_status")
        @Expose
        var requestStatus: String? = null
        @SerializedName("re_order")
        @Expose
        var reOrder: String? = null
        @SerializedName("buying_preference")
        @Expose
        var buyingPreference: String? = null
        @SerializedName("buying_preference_name")
        @Expose
        var buyingPreferenceName: String? = null
        @SerializedName("order_preference")
        @Expose
        var orderPreference: String? = null
        @SerializedName("order_preference_name")

        @Expose
        var orderPreferenceName: String? = null
        @SerializedName("order_type")
        @Expose
        var orderType: String? = null
        @SerializedName("order_type_name")
        @Expose
        var orderTypeName: String? = null
        @SerializedName("delivery_longitude")
        @Expose
        var deliveryLongitude: String? = null
        @SerializedName("area")
        @Expose
        var area: String? = null
        @SerializedName("block_no")
        @Expose
        var blockNo: String? = null
        @SerializedName("building_name")
        @Expose
        var buildingName: String? = null
        @SerializedName("street")
        @Expose
        var street: String? = null
        @SerializedName("zipcode")
        @Expose
        var zipcode: String? = null
        @SerializedName("landmark")
        @Expose
        var landmark: String? = null
        @SerializedName("location")
        @Expose
        var location: String? = null
        @SerializedName("delivery_date")
        @Expose
        var deliveryDate: String? = null
        @SerializedName("delivery_latitude")
        @Expose
        var deliveryLatitude: String? = null
        @SerializedName("delivery_type")
        @Expose
        var deliveryType: String? = null
        @SerializedName("delivery_type_name")
        @Expose
        var deliveryTypeName: String? = null
        @SerializedName("end_time")
        @Expose
        var endTime: String? = null
        @SerializedName("location_criteria_delivery_location")
        @Expose
        var locationCriteriaDeliveryLocation: String? = null
        @SerializedName("location_criteria_location")
        @Expose
        var locationCriteriaLocation: String? = null
        @SerializedName("order_for")
        @Expose
        var orderFor: String? = null
        @SerializedName("order_for_name")
        @Expose
        var orderForName: String? = null
        @SerializedName("order_latitude")
        @Expose
        var orderLatitude: String? = null
        @SerializedName("order_longitude")
        @Expose
        var orderLongitude: String? = null
        @SerializedName("turn_on_location")
        @Expose
        var turnOnLocation: String? = null
        @SerializedName("photo_id")
        @Expose
        var photoId: String? = null
        @SerializedName("prescription_image")
        @Expose
        var prescriptionImage: String? = null
        @SerializedName("start_time")
        @Expose
        var startTime: String? = null
        @SerializedName("store_criteria_area")
        @Expose
        var storeCriteriaArea: String? = null
        @SerializedName("store_criteria_city")
        @Expose
        var storeCriteriaCity: String? = null
        @SerializedName("store_criteria_store_md_name")
        @Expose
        var storeCriteriaStoreMdName: String? = null
        @SerializedName("store_criteria_store_md_id")
        @Expose
        var storeCriteriaStoreMdId: String? = null
        @SerializedName("store_criteria_store_type_id")
        @Expose
        var storeCriteriaStoreTypeId: String? = null
        @SerializedName("store_criteria_store_type_name")
        @Expose
        var storeCriteriaStoreTypeName: String? = null
        @SerializedName("store_criteria_zipcode")
        @Expose
        var storeCriteriaZipcode: String? = null
        @SerializedName("offer_discount_percentage")
        @Expose
        var offerDiscountPercentage: String? = null
        @SerializedName("offer_discount_price")
        @Expose
        var offerDiscountPrice: String? = null
        @SerializedName("offer_delivery_charges")
        @Expose
        var offerDeliveryCharges: String? = null
        @SerializedName("offer_delivery_preference")
        @Expose
        var offerDeliveryPreference: String? = null
        @SerializedName("offer_final_amount")
        @Expose
        var offerFinalAmount: String? = null
        @SerializedName("offer_delivery_date")
        @Expose
        var offerDeliveryDate: String? = null
        @SerializedName("offer_delivery_time_start")
        @Expose
        var offerDeliveryTimeStart: String? = null
        @SerializedName("offer_delivery_time_end")
        @Expose
        var offerDeliveryTimeEnd: String? = null
        @SerializedName("products")
        @Expose
        var products: List<Product>? = null

    }
    inner class Product {

        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("product_id")
        @Expose
        var productId: String? = null
        @SerializedName("order_id")
        @Expose
        var orderId: String? = null
        @SerializedName("color_id")
        @Expose
        var colorId: String? = null
        @SerializedName("color")
        @Expose
        var color: String? = null
        @SerializedName("quantity_type")
        @Expose
        var quantityType: String? = null
        @SerializedName("quantity")
        @Expose
        var quantity: String? = null
        @SerializedName("product_x_position")
        @Expose
        var productXPosition: String? = null
        @SerializedName("product_y_position")
        @Expose
        var productYPosition: String? = null
        @SerializedName("product_price")
        @Expose
        var productPrice: String? = null
        @SerializedName("product_available")
        @Expose
        var productAvailable: String? = null
        @SerializedName("quantity_status")
        @Expose
        var quantityStatus: String? = null
        @SerializedName("store_available_quantity")
        @Expose
        var storeAvailableQuantity: String? = null

    }
}