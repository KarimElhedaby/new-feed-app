package com.newfeeds.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*


abstract class CustomRecyclerViewAdapter<T, V : CustomRecyclerViewAdapter.CustomViewHolder<T>>() :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder<T>>() {

    private var mData: ArrayList<T>? = null

    init {
        this.mData = ArrayList<T>()
    }


    constructor(mData: ArrayList<T>) : this() {
        this.mData = ArrayList<T>()
        addItems(mData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(
                getLayout(viewType), parent, false
            )
        return getViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: CustomViewHolder<T>, position: Int) {

        mData?.get(position)?.let { holder.onBind(it) }
        // or make in bind take any not T
    }

    override fun getItemCount(): Int {
        return mData?.size?:0
    }

    protected abstract fun getLayout(type: Int): Int
    protected abstract fun getViewHolder(view: View, type: Int): V


    fun addItems(items: List<T>?) {
        if (items != null) {
            mData?.addAll(items)
        }
        val numberOfItems = items?.size ?: 0
        val startPosition = (mData?.size ?: 0 - numberOfItems) - 1
        notifyItemRangeInserted(startPosition, numberOfItems)

    }

    fun setItems(items: List<T>?) {
        mData?.clear()
        mData?.addAll(items ?: arrayListOf())
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        mData?.add(item)
        val size = mData?.size ?: 0
        notifyItemInserted(size - 1)
    }

    fun updateItemAt(pos: Int, item: T) {
        mData?.set(pos, item)
        val size = mData?.size ?: 0
        notifyItemChanged(pos)
    }

    fun updateItem(item: T) {
        mData?.let { list ->
            val pos = list.indexOf(item)
            list[pos] = item
            notifyItemChanged(pos, item)
        }
    }

    fun getItemPos(item: T): Int? =
        mData?.indexOf(item)

    fun deleteItem(position: Int) {
        mData?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteItem(item: T) {
        mData?.remove(item)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mData?.clear()
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<T>? {
        return mData

    }

    fun getItemAt(position: Int) = mData?.get(position)

    abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun onBind(item: T)
    }
}