 package com.startandroid.rssreader.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.domain.usecase.FetchFeedUseCase
import com.startandroid.rssreader.R
import com.startandroid.rssreader.databinding.ActivityFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    @Inject lateinit var fetchFeedUseCase: FetchFeedUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kls: KClass<out Fragment> = AddFeedFragment::class
        val fragment = kls.constructors.first { it.parameters.isEmpty() }.call()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
}

