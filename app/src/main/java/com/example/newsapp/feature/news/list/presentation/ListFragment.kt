package com.example.newsapp.feature.news.list.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.di.ListViewModelFactory
import com.example.newsapp.feature.news.list.presentation.adapter.ListAdapter
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.news_list_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: ListViewModel by viewModels {
            ListViewModelFactory()
        }

        val pagingAdapter =
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                news: PagingData<News> ->
            }
        }

        val adapter = ListAdapter(
            { news -> viewModel.newsItemClicked(news) },
            { retryItem -> viewModel.retryButtonClicked(retryItem) }
        )

        viewModel.newsClickEvent.observe(viewLifecycleOwner, Observer(::newsClicked))
        viewModel.news.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.retryClickEvent.observe(viewLifecycleOwner, Observer {
            adapter.notifyItemChanged(it.toInt())
        })

        news_recycler.layoutManager = LinearLayoutManager(view.context)
        news_recycler.setHasFixedSize(true)
        news_recycler.adapter = adapter
    }

    private fun newsClicked(news: News) {
        val bundle = Bundle()
        bundle.putString("url", news.urlToArticle)
        findNavController().navigate(R.id.action_newsListFragment_to_webViewFragment, bundle)
    }
}