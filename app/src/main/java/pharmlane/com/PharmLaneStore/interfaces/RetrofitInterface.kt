package pharmlane.com.PharmLaneStore.interfaces


import pharmlane.com.PharmLaneStore.model.*
import pharmlane.com.PharmLaneStore.response.*
import pharmlane.com.PharmLaneStore.utills.AppConstant.ADD_INVOICE_OFFER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.ADD_TOKEN
import pharmlane.com.PharmLaneStore.utills.AppConstant.COMMON_FILTER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.COMMON_NOTIFICATION_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.CONTACT_US
import pharmlane.com.PharmLaneStore.utills.AppConstant.GENERATE_OTP_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_ALL_DROPDOWN_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_COLOR_CODE_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_INVOICE_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_QR_CODES_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_STORE_DETAIL_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_STORE_LIST_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_STORE_ORDER_LIST_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.GET_STORE_RATING_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.LOGIN_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.MAKE_BUYER_REVIEW_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.ORDER_DETAIL_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.ORDER_DETAIL_FOR_OFFER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.ORDER_TIMELINE_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.REGISTER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.SEEK_FINANCE
import pharmlane.com.PharmLaneStore.utills.AppConstant.SEND_FEEDBACK
import pharmlane.com.PharmLaneStore.utills.AppConstant.SKIP_VERIFY_RECEIVER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_ALL_ORDER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_APP_CHECK_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_DETAIL_UPDATE_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_MAKE_SUB_PAY
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_SUBMIT_OFFER_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_SUB_HISTORY
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_SUB_PAY_METHODS
import pharmlane.com.PharmLaneStore.utills.AppConstant.STORE_SUB_PLANS
import pharmlane.com.PharmLaneStore.utills.AppConstant.TANDC
import pharmlane.com.PharmLaneStore.utills.AppConstant.VERIFICATION_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.VERIFY_OTP_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.VERIFY_PAYMENT_API
import pharmlane.com.PharmLaneStore.utills.AppConstant.VIEWALLSTORE_ORDER_API
import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(REGISTER_API)
    fun register(@Body body: Register): Call<RegisterResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(LOGIN_API)
    fun login(@Body body: Login): Call<LoginResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_ALL_DROPDOWN_API)
    fun getalldropdown(@Body body: GetAllDropDown): Call<GetAllDropdownResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_QR_CODES_API)
    fun getqrcodes(@Body body: Filter): Call<GetPaymentMethodsResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_STORE_ORDER_LIST_API)
    fun getstoreorderlist(@Body body: GetStoreList): Call<GetStoreListResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(ORDER_TIMELINE_API)
    fun getstoreordertimeline(@Body body: GetStoreList): Call<GetStoreListResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_SUB_PLANS)
    fun getallstoresubplanlist(): Call<AllStoreSubPlanResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_SUB_PAY_METHODS)
    fun getallstoresubmethodlist(@Body body: Filter): Call<AllStoreSubPaymentResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_SUB_HISTORY)
    fun getallstoresubhistory(@Body body: Filter): Call<StoreSubHistoryResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_MAKE_SUB_PAY)
    fun makesubpayment(@Body body: MakeSubPayment): Call<VerifySubPaymentResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_ALL_ORDER_API)
    fun getallstoreorderlist(@Body body: OrderListByFilter): Call<ViewAllStoreOrderResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_STORE_DETAIL_API)
    fun storedetail(@Body body: StoreDetail): Call<StoreDetailResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_STORE_LIST_API)
    fun storelist(@Body body: StoreList): Call<StoreListResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(VIEWALLSTORE_ORDER_API)
    fun viewallstorelist(@Body body: ViewallStoreOrder): Call<ViewAllStoreOrderResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(ORDER_DETAIL_API)
    fun orderdetail(@Body body: OrderDetail): Call<OrderDetailResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @GET(GET_COLOR_CODE_API)
    fun getcolorcode(): Call<GetColorCodeResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(COMMON_FILTER_API)
    fun common_filter(@Body body: Filter): Call<FilterResponse>

    //CHeck this api
    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_SUBMIT_OFFER_API)
    fun submitoffer(@Body body: SubmitOffer): Call<SubmitOfferResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(ORDER_DETAIL_FOR_OFFER_API)
    fun orderdetailoffer(@Body body: OrderDetailOffer): Call<OrderDetailResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(ADD_INVOICE_OFFER_API)
    fun addinvoiceoffer(@Body body: AddInvoiceOffer): Call<AddInvoiceOfferResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(VERIFY_PAYMENT_API)
    fun verifypayment(@Body body: VerifyPayment): Call<VerifyPaymentResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_INVOICE_API)
    fun getinvoice(@Body body: GetInvoice): Call<GetInvoiceResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GENERATE_OTP_API)
    fun generateotp(@Body body: GenerateOtp): Call<GenerateOtpResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(VERIFY_OTP_API)
    fun verifyotp(@Body body: VerifyOtp): Call<VerifyOtpResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(SKIP_VERIFY_RECEIVER_API)
    fun skipverify(@Body body: SkipVerify): Call<SkipVerifyResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(MAKE_BUYER_REVIEW_API)
    fun makebuyerreview(@Body body: MakeBuyerReview): Call<MakeBuyerReviewResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(GET_STORE_RATING_API)
    fun getstorerating(@Body body: StoreRating): Call<StoreRatingResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(VERIFICATION_API)
    fun verification(@Body body: Verification): Call<VerificationResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(COMMON_NOTIFICATION_API)
    fun getNtifications(@Body body: NotiFications): Call<NotificationResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_DETAIL_UPDATE_API)
    fun update_store_detail(@Body body: Register): Call<StoreDetailResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(STORE_APP_CHECK_API)
    fun storeCheck(@Body body: Register): Call<StoreDetailResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(TANDC)
    fun getTandC(): Call<TandCResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(CONTACT_US)
    fun getContactUS(): Call<ContactUSResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(SEND_FEEDBACK)
    fun sendFeedBack(@Body body: Feedback): Call<CommonResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(SEEK_FINANCE)
    fun seekFinance(@Body body: SeekFinance): Call<CommonResponse>

    @Headers("Content-Type: application/json", "Cache-Control: no-cache")
    @POST(ADD_TOKEN)
    fun addToken(@Body body: AddToken): Call<CommonResponse>

}