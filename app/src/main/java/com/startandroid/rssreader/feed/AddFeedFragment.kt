package com.startandroid.rssreader.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.startandroid.rssreader.common.uievent.ViewModelUiEventHandler
import com.startandroid.rssreader.databinding.FragmentAddFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddFeedFragment : Fragment() {

    @Inject lateinit var viewModelUiEventHandler: ViewModelUiEventHandler
    @Inject lateinit var provider: FeedViewModelProvider

    private var binding: FragmentAddFeedBinding? = null
    private val feedModel: FeedViewModel by viewModels { provider }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = "Add feed"
            setDisplayHomeAsUpEnabled(false)
        }

        binding?.buttonCheck?.setOnClickListener { feedModel.onCheckClick(binding?.editTextUrl?.text?.toString()) }
        binding?.buttonAdd?.setOnClickListener { }
        binding?.buttonPreview?.setOnClickListener { feedModel.onPreviewClick() }

        feedModel.state().observe(viewLifecycleOwner) { binding?.state = it }

        viewModelUiEventHandler.collectEvents(feedModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

