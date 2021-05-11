package com.startandroid.rssreader

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.startandroid.domain.repository.FeedRepository
import com.startandroid.domain.usecase.AddFeedUseCase
import com.startandroid.rssreader.databinding.ActivityFeedBinding
import com.startandroid.rssreader.feed.list.FeedListFragment
import com.startandroid.rssreader.item.list.ItemListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    @Inject
    lateinit var feedRepository: FeedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, ItemListFragment())
                .commit()
        }
        debug()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun debug() {
        lifecycleScope.launch {
            feedRepository.clean()
            listOf(
                //"https://antonioleiva.com/feed/",
                //"https://blog.jetbrains.com/kotlin/feed/",
                "https://meduza.io/rss/all",
                "https://www.buzzfeed.com/world.xml",
                "http://rss.cnn.com/rss/edition_world.rss",
                "http://yahoo.com/news/rss/world",
                "http://www.publicbooks.org/tag/the-big-picture/feed"
            ).forEach {
                //feedRepository.addFeed(it)
            }
        }
    }
}

