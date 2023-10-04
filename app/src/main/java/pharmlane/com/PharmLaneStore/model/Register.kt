package pharmlane.com.PharmLaneStore.model

import java.util.ArrayList

class Register {

    var owner_name: String? = null
    var store_id: String? = null
    var phone: String? = null
    var landmark: String? = null
    var building: String? = null
    var city: String? = null
    var zipcode: String? = null
    var employee1_name: String? = null
    var employee1_mobile: String? = null
    var employee2_name: String? = null
    var employee2_mobile: String? = null
    var store_name: String? = null
    var type: String? = null
    var gst_number: String? = null
    var merchandise_category: String? = null
    var established_since: String? = null
    var shop_no: String? = null
    var street: String? = null
    var area: String? = null
    var geo_location: String? = null
    var drug_license_image: String? = null
    var store_photo: String? = null
    var gst_certificate: String? = null
    var drug_license_number: String? = null
    var registered_pharmacist_name:String?=null
    var store_latitude:String?=null
    var store_longitude:String?=null
    var latitude:String?=null
    var longitude:String?=null
    var payment_method:String?=null
    var payment_method_array =  ArrayList<payment>()

    class payment{
        var payment_method_id: String? = null
        var upi_id: String? = null
        var is_selected: String? = null
        var qr_img: String? = null

    }

}