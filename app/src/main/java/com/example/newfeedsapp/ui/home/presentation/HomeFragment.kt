package com.example.newfeedsapp.ui.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newfeedsapp.R
import com.newfeeds.core.base.BaseFragment
import com.example.newfeedsapp.databinding.FragmentHomeBinding
import com.example.newfeedsapp.domain.model.NewFeed
import com.example.newfeedsapp.ui.home.adapter.NewFeedAdapter
import com.newfeeds.core.constants.Constants.Companion.NEW_FEED_ARGUMENT
import com.newfeeds.core.utils.secretB
import com.newfeeds.core.utils.showB
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private val newFeedAdapter by lazy {
        NewFeedAdapter(itemClick = { navigateToDetails(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNewFeeds()

        setupObservables()
    }

    // live data observation (loading - error - data)
    private fun setupObservables() = with(binding) {
        //loading
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) progressBar.showB() else progressBar.secretB()
        }

        //error message
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        // data
        viewModel.newFeedsLiveData.observe(viewLifecycleOwner) { data ->
            when {
                data.isEmpty() -> {
                    newFeedRecyclerView.secretB()
                    emptyView.showB()
                }
                else -> {
                    newFeedRecyclerView.showB()
                    emptyView.secretB()
                    newFeedRecyclerView.adapter = newFeedAdapter
                    newFeedAdapter.setItems(data)
                }
            }
        }
    }

    // when click on an item navigate to details screen
    private fun navigateToDetails(newFeed: NewFeed) {
        val bundle = Bundle()
        // Share item data between 2 fragments as a safe argument
        bundle.putParcelable(NEW_FEED_ARGUMENT, newFeed)
        findNavController().navigate(R.id.detailsFragment, bundle)
    }


}