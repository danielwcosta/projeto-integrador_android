package custom.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.ListPopupWindow.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintSet.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class RecyclerViewHolder(val builder: ItemViewBuilder<*, *>) :
    RecyclerView.ViewHolder(builder.build())

abstract class ItemViewBuilder<Data, Binding : ViewBinding> : InflateBinding {

    abstract val binding: Binding
    lateinit var collection: Collection<Data>
    lateinit var context: Context
    lateinit var viewGroup: ViewGroup

    @Suppress("UNCHECKED_CAST")
    fun init(group: ViewGroup, coll: Collection<*>) = apply {
        viewGroup = group
        collection = coll as Collection<Data>
        context = group.context
    }

    fun build() = binding.root.apply {
        layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun onBind(position: Int) = binding.onBind(position)

    abstract fun Binding.onBind(position: Int)
}