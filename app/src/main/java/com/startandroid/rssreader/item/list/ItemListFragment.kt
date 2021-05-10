package com.startandroid.rssreader.item.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.startandroid.rssreader.common.uievent.ViewModelUiEventHandler
import com.startandroid.rssreader.databinding.FragmentItemListBinding
import com.startandroid.rssreader.item.list.adapter.ItemPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    @Inject lateinit var viewModelUiEventHandler: ViewModelUiEventHandler
    @Inject lateinit var itemPagingAdapter: ItemPagingAdapter

    var binding: FragmentItemListBinding? = null

    private val itemListViewModel: ItemListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Item list"

        binding?.fabFeeds?.setOnClickListener {
            itemListViewModel.onFeedsClick()
        }

        binding?.recyclerViewData?.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = itemPagingAdapter
        }

        lifecycleScope.launch {
            itemListViewModel.pagingDataFlow.collect {
                itemPagingAdapter.submitData(it)
            }
        }

        viewModelUiEventHandler.collectEvents(itemListViewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}





