package base

import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import debugging.ExceptionHandler

open class ActBase(open val layout: Any? = null) : AppCompatActivity() {

    companion object {
        @JvmStatic lateinit var currentActivity: AppCompatActivity
        @JvmStatic var exceptionHandler = ExceptionHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
        when (layout) {
            is Int -> setContentView(layout as Int)
            is View -> setContentView(layout as View)
        }
        intent?.extras?.onExtras()
        onView()
    }

    override fun onResume() {
        super.onResume()
        currentActivity = this
    }

    open fun Bundle.onExtras() {}

    open fun onView() {}

}