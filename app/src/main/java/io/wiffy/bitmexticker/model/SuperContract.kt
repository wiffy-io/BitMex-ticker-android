package io.wiffy.bitmexticker.model

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface SuperContract {
    interface WiffyObject {
        fun console(str: String) = Log.d("asdf", str)
        fun toast(context: Context, str: String) =
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

        fun toast(context: Context, id: Int) =
            Toast.makeText(context, id, Toast.LENGTH_SHORT).show()

        fun toastLong(context: Context, str: String) =
            Toast.makeText(context, str, Toast.LENGTH_LONG).show()

        fun toastLong(context: Context, id: Int) =
            Toast.makeText(context, id, Toast.LENGTH_LONG).show()
    }

    abstract class SuperActivity : AppCompatActivity(), WiffyObject {
        fun toast(str: String) = Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
        fun toast(id: Int) = Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
        fun toastLong(str: String) =
            Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()

        fun toastLong(id: Int) = Toast.makeText(applicationContext, id, Toast.LENGTH_LONG).show()
    }

    abstract class SuperFragment : Fragment(), WiffyObject {
        fun toast(str: String) = Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
        fun toast(id: Int) = Toast.makeText(activity, id, Toast.LENGTH_SHORT).show()
        fun toastLong(str: String) = Toast.makeText(activity, str, Toast.LENGTH_LONG).show()
        fun toastLong(id: Int) = Toast.makeText(activity, id, Toast.LENGTH_LONG).show()
    }

    abstract class SuperDialog(context: Context) : Dialog(context), WiffyObject {
        fun toast(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        fun toast(id: Int) = Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
        fun toastLong(str: String) = Toast.makeText(context, str, Toast.LENGTH_LONG).show()
        fun toastLong(id: Int) = Toast.makeText(context, id, Toast.LENGTH_LONG).show()
    }

    abstract class SuperAsyncTask<A, B, C> : AsyncTask<A, B, C>(), WiffyObject
}