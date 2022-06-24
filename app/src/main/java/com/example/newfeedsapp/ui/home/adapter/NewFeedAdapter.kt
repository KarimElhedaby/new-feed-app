package com.example.newfeedsapp.ui.home.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.newfeeds.core.base.CustomRecyclerViewAdapter
import com.example.newfeedsapp.R
import com.example.newfeedsapp.databinding.ItemNewFeedBinding
import com.example.newfeedsapp.domain.model.NewFeed
import com.newfeeds.core.utils.loadImage

// recycler view to bind articles can find the base recycler component in core module
class NewFeedAdapter(val itemClick: (NewFeed) -> Unit) : CustomRecyclerViewAdapter<NewFeed, CustomRecyclerViewAdapter.CustomViewHolder<NewFeed>>() {

    override fun getLayout(type: Int) = R.layout.item_new_feed

    override fun getViewHolder(view: View, type: Int) = SmartViewHolder(view)

    inner class SmartViewHolder(itemView: View) :
        CustomRecyclerViewAdapter.CustomViewHolder<NewFeed>(itemView) {
        private val dataBinding by lazy { DataBindingUtil.bind<ItemNewFeedBinding>(itemView) }

        override fun onBind(item: NewFeed) {
            dataBinding?.apply {
                newFeedAuthorTextView.text = "By ${item.author}"
                newFeedTitleTextView.text = item.title
                newFeedDateTextView.text = item.publishedAt
                newFeedImageView.loadImage(item.image)
            }

            // handle item click listener
            dataBinding?.newFeedItemCard?.setOnClickListener {
                itemClick.invoke(item)
            }
        }
    }
}