package pharmlane.com.PharmLaneStore.model

import java.util.ArrayList

class SubmitOffer {

    var order_id: String? = null
    var store_id: String? = null
    var discount_price: String? = null
    var discount_percentage: String? = null
    var delivery_charges: String? = null
    var final_amount: String? = null
    var delivery_date: String? = null
    var to_delivery_date: String? = null
    var delivery_time_start: String? = null
    var delivery_time_end: String? = null
    var delivery_preference: String? = null
    var is_prescription: String? = null
    var delivery_type: String? = null
    // var Notes: String? = null
  //  var quantity_status: String? = null
    var totalMrp: String? = null

    var products = ArrayList<Products>()

    class Products {
        var order_product_id: String? = null
        var product_available: String? = null
        var quantity: String? = null
        var quantity_status: String? = null
        var product_price: String? = null
        var storeAvailableQuantity:String?=null
        var quantity_avail: String? = null
        var quantity_type: String? = null
        var productMrp: String? = null
        var prescr_status: String? = null

    }
/*order_id,
store_id,discount_percentage,discount_price,delivery_charges,delivery_preference,
delivery_date,delivery_time_start,delivery_time_end,final_amount*/
    /*Notes[Status =   // 0: full 1 partial,
 quantity_status //0 no, 1 yes, 2 less == product_available
*/
}