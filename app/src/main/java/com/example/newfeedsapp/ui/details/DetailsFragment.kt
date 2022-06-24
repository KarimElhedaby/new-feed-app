package com.example.newfeedsapp.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newfeedsapp.databinding.FragmentDetailsBinding
import com.newfeeds.core.base.BaseFragment
import com.example.newfeedsapp.databinding.FragmentHomeBinding
import com.example.newfeedsapp.domain.model.NewFeed
import com.example.newfeedsapp.ui.home.adapter.NewFeedAdapter
import com.newfeeds.core.utils.loadImage
import com.newfeeds.core.utils.openLink
import com.newfeeds.core.utils.secretB
import com.newfeeds.core.utils.showB
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val args: DetailsFragmentArgs by navArgs()
    var newFeedItem = NewFeed()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newFeedItem = args.newFeedArgsModel ?: NewFeed()

        fillNewFeedData()
        setUpController()
    }

    private fun fillNewFeedData() = binding.apply {
        with(newFeedItem) {
            newFeedAuthorTextView.text = "By $author"
            newFeedDescriptionTextView.text = description
            newFeedTitleTextView.text = title
            newFeedDateTextView.text = publishedAt
            newFeedImageView.loadImage(image)   // load image an extension to load image by Glide
        }
    }

    private fun setUpController() {
        binding.openWebsiteButton.setOnClickListener {
            this@DetailsFragment.requireContext().openLink(newFeedItem.url)
        }
    }

}