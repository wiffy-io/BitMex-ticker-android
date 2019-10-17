package io.wiffy.bitmexticker.model

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import io.wiffy.bitmexticker.function.getDialog
import io.wiffy.bitmexticker.function.mKey
import io.wiffy.bitmexticker.function.productName

class BillingModule(private val activity:AppCompatActivity):BillingProcessor.IBillingHandler {

    private var mBillingProcessor: BillingProcessor? = null

    companion object{
        @JvmStatic
        var itemId = ""
    }

    fun initBillingProcessor()
    {
        mBillingProcessor = BillingProcessor(activity, mKey, this)
    }

    fun purchaseProduct()
    {
        if(mBillingProcessor?.isPurchased(productName)==true)
            mBillingProcessor?.purchase(activity, productName)
    }

    fun releaseBillingProcessor()
    {
        if(mBillingProcessor!=null)
            mBillingProcessor?.release()
    }

    fun getBillingProcessor():BillingProcessor?
    {
        return mBillingProcessor
    }

    override fun onBillingInitialized() {
       mBillingProcessor?.getPurchaseListingDetails(productName)?.let {
           itemId = it.productId
           mBillingProcessor?.loadOwnedPurchasesFromGoogle()
           if(mBillingProcessor?.isPurchased(itemId)==true)
           {
               Component.isConsumer = true
               helu("Success","Purchase, Succeed!")
           }
       }
    }

    override fun onPurchaseHistoryRestored() {
        Component.isConsumer = true
        helu("Success","Purchase, Succeed!")
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        Component.isConsumer = true
        helu("Success","Purchase, Succeed!")
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        helu("Error", "Try again!")
    }

    private fun helu(title:String, context:String)
    {
        AlertDialog.Builder(activity, getDialog()).apply {
            setTitle(title)
            setMessage(context)
            setPositiveButton(
                "OK"
            ) { _, _ -> }
        }.show()
    }
}