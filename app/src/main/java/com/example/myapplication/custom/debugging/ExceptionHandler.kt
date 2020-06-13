package debugging

import android.content.Intent
import android.os.Build.*
import android.os.Build.VERSION.*
import android.os.Process.killProcess
import android.os.Process.myPid
import base.ActBase.Companion.currentActivity
import com.example.myapplication.BuildConfig.APPLICATION_ID
import com.example.myapplication.R
import com.example.myapplication.custom.debugging.ActException
import java.io.PrintWriter
import java.io.StringWriter

class ExceptionHandler : Thread.UncaughtExceptionHandler {

    companion object {
        const val EXCEPTION_MESSAGE = "message"
        const val EXCEPTION_LOG = "log"
        const val EXCEPTION_DEVICE = "device"
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        currentActivity.run {
            startActivity(
                Intent(currentActivity, ActException::class.java)
                    .putExtra(EXCEPTION_MESSAGE, exception.message)
                    .putExtra(EXCEPTION_LOG, exception.formattedLog)
                    .putExtra(EXCEPTION_DEVICE, deviceInfo)
            )
            finish()
        }
        killProcess(myPid())
    }
}

private val Throwable.formattedLog
    get() = StringBuilder().apply {
        val stackTrace = StringWriter()
        printStackTrace(PrintWriter(stackTrace))

        append(
            stackTrace.toString()
                .replace("java.lang.", "")
                .replace("Exception", "Ex\n\n")
                .replace(APPLICATION_ID, "")
                .replace("at ", "")
                .replace("(", "\n(")
                .replace(")", ")\n")
        )
    }.toString()

private val deviceInfo
    get() = StringBuilder().apply {
        val tab = "            "
        append("\n\n\n${currentActivity.getString(R.string.device_information)}")
        append("\n\n$tab${currentActivity.getString(R.string.brand)}: $BRAND")
        append("\n\n$tab${currentActivity.getString(R.string.device)}: $DEVICE")
        append("\n\n$tab${currentActivity.getString(R.string.model)}: $MODEL")
        append("\n\n$tab${currentActivity.getString(R.string.id)}: $ID")
        append("\n\n$tab${currentActivity.getString(R.string.product)}: $PRODUCT")

        append("\n\n\n${currentActivity.getString(R.string.build_info)}")
        append("\n\n$tab${currentActivity.getString(R.string.sdk)}: $SDK_INT")
        append("\n\n$tab${currentActivity.getString(R.string.release)}: $RELEASE")
        append("\n\n$tab${currentActivity.getString(R.string.incremental)}: $INCREMENTAL")
    }.toString()