package io.wiffy.bitmexticker.model

import android.app.AlertDialog
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import io.wiffy.bitmexticker.function.getDialog
import io.wiffy.bitmexticker.function.mKey
import io.wiffy.bitmexticker.function.productName
import io.wiffy.bitmexticker.function.restartApp

class BillingModule(private val activity: AppCompatActivity) : BillingProcessor.IBillingHandler {

    private var mBillingProcessor: BillingProcessor? = null
    var callback: (String) -> Unit? = {}

    companion object {
        @JvmStatic
        var itemId = ""
    }

    fun initBillingProcessor() {
        Log.d("asdf", "asaa1")
        mBillingProcessor = BillingProcessor(activity, mKey, this)
    }

    fun purchaseProduct() {
        if (mBillingProcessor?.isSubscribed(productName) == false)
            mBillingProcessor?.subscribe(activity, productName)
        else
            helu("Success", "Already Purchased!")
    }

    fun releaseBillingProcessor() {
        mBillingProcessor?.release()
    }

    fun getBillingProcessor(): BillingProcessor? {
        return mBillingProcessor
    }

    fun aaaaaa() {
        if (mBillingProcessor?.isSubscribed(productName) == true) {
            Component.isConsumer = true
            helu("Success", "Purchase, Succeed!")
        } else {
            helu("Error", "Try Again!")
        }
    }

    override fun onBillingInitialized() {
        Log.d("asdf", "asaa2")
        mBillingProcessor?.getSubscriptionListingDetails(productName)?.let {
            Log.d("asdf", "asaa3")
            itemId = it.productId
            Log.d("asdf", itemId)
            mBillingProcessor?.loadOwnedPurchasesFromGoogle()
            Log.d("asdf", mBillingProcessor?.isSubscribed(itemId).toString())
            if (mBillingProcessor?.isSubscribed(itemId) == true) {
                Log.d("asdf", "asaa4")
                Component.isConsumer = true
                if (callback == {}) {
                    helu("Success", "Purchase, Succeed!")
                }
            }
            if (callback != {}) {
                callback.invoke("")
            }
        }

    }

    override fun onPurchaseHistoryRestored() {
        Log.d("asdf", "onPurchaseHistoryRestored")
//        if (mBillingProcessor?.isSubscribed(itemId) == true) {
//            Component.isConsumer = true
//            helu("Success", "Purchase, Succeed!")
//        }
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        Log.d("asdf", "onProductPurchased")
        Component.isConsumer = true
        helu("Success", "Purchase, Succeed!")
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        helu("Error", "Try Again!")
    }

    private fun helu(title: String, context: String) {
        AlertDialog.Builder(activity, getDialog()).apply {
            setTitle(title)
            setMessage(context)
            setPositiveButton(
                "OK"
            ) { _, _ -> }
            if (title == "Success") setPositiveButton("OK") { _, _ ->
                if (!Component.isConsumer) {
                    restartApp(activity)
                }
            }
        }.show()
    }
}