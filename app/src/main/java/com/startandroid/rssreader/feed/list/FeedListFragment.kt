package com.startandroid.rssreader.feed.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.startandroid.rssreader.common.uievent.ViewModelUiEventHandler
import com.startandroid.rssreader.databinding.FragmentFeedListBinding
import com.startandroid.rssreader.feed.list.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedListFragment : Fragment() {


    @Inject lateinit var viewModelUiEventHandler: ViewModelUiEventHandler
    @Inject lateinit var feedAdapter: FeedAdapter

    private val feedListModel: FeedListViewModel by viewModels()
    var binding: FragmentFeedListBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedListBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Feed list"

        binding?.fabAdd?.setOnClickListener {
            feedListModel.onAddClick()
        }

        binding?.recyclerViewData?.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = feedAdapter
        }

        feedListModel.state().observe(viewLifecycleOwner) { state ->
            binding?.run {
                textViewEmpty.visibility = state.emptyTextVisibility
                feedAdapter.setData(state.feedList)
            }
        }

        viewModelUiEventHandler.collectEvents(feedListModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}