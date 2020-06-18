package com.example.myapplication.custom

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.res.Resources.getSystem
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import custom.adapter.ItemViewBuilder
import kotlin.reflect.KClass
import kotlin.reflect.KFunction0

operator fun <T> Collection<T>.get(index: Int): T {
    forEachIndexed { indexed, element -> if (indexed == index) return element }
    throw IndexOutOfBoundsException()
}

inline fun <reified Builder : ItemViewBuilder<*, *>>
        RecyclerView.setup(list: Collection<*>) =
        recyclerAdapter<Builder>(list).apply { adapter = this }

val RecyclerView.recyclerAdapter get() = adapter as RecyclerAdapter?

fun RecyclerView.update() = recyclerAdapter?.notifyDataSetChanged()

fun <T> MutableList<T>.update(collection: MutableList<T>) {
    clear()
    collection.forEach { add(it) }
}

fun Activity.hideKeyBoard() {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?)
            ?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

val Int.isEven get() = this % 2 == 0

val Int.isOdd get() = this % 2 != 0

val EditText.string get() = text.toString()

val EditText.int get() = string.toInt()

private var toast: Toast? = null

fun Context.toast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, LENGTH_SHORT)
    toast?.show()
}

fun String.isDigit(): Boolean {
    for (char in this) {
        if (!char.isDigit()) return false
    }
    return true
}

inline fun <reified Model : ViewModel> FragmentActivity.viewModel(): Lazy<Model> = lazy {
    ViewModelProviders.of(this).get(Model::class.java)
}

inline fun <reified Model : ViewModel> Fragment.viewModel(): Model =
        ViewModelProviders.of(this).get(Model::class.java)

fun <T : Comparable<T>> listOfRange(iterable: Iterable<T>): MutableList<T> {
    val list = mutableListOf<T>()
    iterable.forEach { list.add(it) }
    return list
}

@Suppress("UNCHECKED_CAST") // Converts Pixel value to DensityPixel value
val <N : Number> N.dp
    get() = (toFloat() * getSystem().displayMetrics.density) as N

fun onTextSubmit(block: (String) -> Unit) = object : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(dota: String): Boolean {
        block(dota)
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}

fun Context.newButton(style: Int = 0) =
        Button(this, null, 0, style)

// reflexão é uma forma da linguagem referenciar a própria linguagem
// é uma forma de via código saber o que define uma classe
inline fun <reified ViewType : View> Context.new(
        style: Int = R.style.Button,
        setup: ViewType.() -> Unit = {}
): ViewType {
    val view = ViewType::class.java.getConstructor(
            Context::class.java,
            AttributeSet::class.java,
            Int::class.java,
            Int::class.java
    ).newInstance(this, null, 0, style)
    view.setup()
    return view
}

fun <V : View> V.onClick(function: V.() -> Unit = {}) {
    setOnClickListener { function() }
}

fun <V : View> V.onClick(function: KFunction0<*>) {
    setOnClickListener { function() }
}

val Context.inflater get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

@Suppress("UNCHECKED_CAST")
fun <B : ViewBinding> Context.bindView(klass: KClass<B>) =
        klass.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, inflater) as B

fun Context.shareText(text: String) {
    startActivity(
            Intent.createChooser(
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }, "Whiskas Sache"
            )
    )
}

infix fun ImageView.setImageFromURL(url: Any?) = Picasso.get().load(url.toString()).into(this)

@Suppress("UNCHECKED_CAST")
val <T : Any> Class<T>.klass: KClass<T>
    get() = this::class as KClass<T>



















