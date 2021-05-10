package com.startandroid.rssreader.item.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.startandroid.rssreader.databinding.FragmentItemListPreviewBinding
import com.startandroid.rssreader.item.preview.adapter.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemsPreviewFragment : Fragment() {

    companion object {
        const val KEY_FEED_URL = "feedUrl"

        fun feedArgs(feedUrl: String?): Bundle? {
            return feedUrl?.let { Bundle().apply { putString(KEY_FEED_URL, it) } }
        }
    }

    @Inject lateinit var itemAdapter: ItemAdapter
    private val itemsModel: ItemsPreviewViewModel by viewModels()
    private var binding: FragmentItemListPreviewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val url = it.getString(KEY_FEED_URL)
            itemsModel.fetch(url)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItemListPreviewBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Items preview"

        binding?.recyclerViewData?.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = itemAdapter
        }

        itemsModel.state().observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loadingVisibility
            itemAdapter.setData(it.items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }



}