package com.startandroid.rssreader.feed.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.common.getKSerializable
import com.startandroid.rssreader.common.putKSerializable
import com.startandroid.rssreader.databinding.FragmentItemListPreviewBinding
import com.startandroid.rssreader.feed.item.adapter.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemListPreviewFragment : Fragment() {

    @Inject
    lateinit var itemAdapter: ItemAdapter

    var binding: FragmentItemListPreviewBinding? = null

    companion object {
        const val KEY_ITEM_LIST = "itemList"

        fun createArguments(itemList: List<Item>?): Bundle? {
            return itemList?.let { Bundle().apply { putKSerializable(KEY_ITEM_LIST, itemList) } }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemAdapter.setData(it.getKSerializable(KEY_ITEM_LIST))
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItemListPreviewBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = "Preview"
            setDisplayHomeAsUpEnabled(true)
        }

        binding?.recyclerView?.run {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = itemAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

