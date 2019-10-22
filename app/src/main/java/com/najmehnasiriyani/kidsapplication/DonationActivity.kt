package com.najmehnasiriyani.kidsapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.wallet.*
import kotlinx.android.synthetic.main.activity_donation.*
import org.json.JSONArray
import org.json.JSONObject

class DonationActivity : AppCompatActivity() {
    private lateinit var paymentsClient: PaymentsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)
        paymentsClient = createPaymentsClient(this)
        val readyToPayRequest =
            IsReadyToPayRequest.fromJson(googlePayBaseConfiguration.toString())

        val readyToPayTask = paymentsClient.isReadyToPay(readyToPayRequest)
        .addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)?.let(::setGooglePayAvailable)
            } catch (exception: ApiException) {
                // Error determining readiness to use Google Pay.
                // Inspect the logs for more details.
            }
        }
        val paymentDataRequest =
            PaymentDataRequest.fromJson(paymentDataRequestJson.toString())

//        AutoResolveHelper.resolveTask(
//            paymentsClient.loadPaymentData(paymentDataRequest),
//            this, LOAD_PAYMENT_DATA_REQUEST_CODE)

    }

//    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
//                when (resultCode) {
//                    Activity.RESULT_OK ->
//                        PaymentData.getFromIntent(data!!)?.let(::handlePaymentSuccess)
//
//                    Activity.RESULT_CANCELED -> {
//                        // The user cancelled without selecting a payment method.
//                    }
//
//                    AutoResolveHelper.RESULT_ERROR -> {
//                        AutoResolveHelper.getStatusFromIntent(data)?.let {
//                            handleError(it.statusCode)
//                        }
//                    }
//                }
//            }
//        }
//    }


    private val cardPaymentMethod = JSONObject().apply {
        put("type", "CARD")
        put("tokenizationSpecification", tokenizationSpecification)
        put("parameters", JSONObject().apply {
            put("allowedCardNetworks", JSONArray(listOf("VISA", "MASTERCARD")))
            put("allowedAuthMethods", JSONArray(listOf("PAN_ONLY", "CRYPTOGRAM_3DS")))
            put("billingAddressRequired", true)
            put("billingAddressParameters", JSONObject(mapOf("format" to "FULL")))
        })
    }
    private val transactionInfo = JSONObject().apply {
        put("totalPrice", "123.45")
        put("totalPriceStatus", "FINAL")
        put("currencyCode", "USD")
    }

    private val merchantInfo = JSONObject().apply {
        put("merchantName", "Example Merchant")
        put("merchantId", "01234567890123456789")
    }

    private val googlePayBaseConfiguration = JSONObject().apply {
        put("apiVersion", 2)
        put("apiVersionMinor", 0)
        put("allowedPaymentMethods",  JSONArray().put(baseCardPaymentMethod))
    }

    private val paymentDataRequestJson = JSONObject(googlePayBaseConfiguration.toString()).apply {
        put("allowedPaymentMethods", JSONArray().put(cardPaymentMethod))
        put("transactionInfo", transactionInfo)
        put("merchantInfo", merchantInfo)
    }

    private val tokenizationSpecification = JSONObject().apply {
        put("type", "PAYMENT_GATEWAY")
        put("parameters", JSONObject(mapOf(
            "gateway" to "example",
            "gatewayMerchantId" to "exampleGatewayMerchantId")))
    }
    private fun setGooglePayAvailable(available: Boolean) {
        if (available) {
            googlePayButton.visibility = View.VISIBLE
            googlePayButton.setOnClickListener { requestPayment() }
        } else {
            // Unable to pay using Google Pay. Update your UI accordingly.
        }
    }

    private fun requestPayment() {
        // TODO: Perform transaction
    }
    fun createPaymentsClient(activity: Activity): PaymentsClient {
        val walletOptions = Wallet.WalletOptions.Builder()
            .setEnvironment(WalletConstants.ENVIRONMENT_TEST).build()
        return Wallet.getPaymentsClient(activity, walletOptions)
    }
    private val baseCardPaymentMethod = JSONObject().apply {
        put("type", "CARD")
        put("parameters", JSONObject().apply {
            put("allowedCardNetworks", JSONArray(listOf("VISA", "MASTERCARD")))
            put("allowedAuthMethods", JSONArray(listOf("PAN_ONLY", "CRYPTOGRAM_3DS")))
        })
    }





}
