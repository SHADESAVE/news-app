package com.example.newsapp.feature.news.list.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.feature.Injection
import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.adapter.NewsAdapter
import com.example.newsapp.feature.news.list.presentation.adapter.NewsLoadStateAdapter
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.news_list_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: ListViewModel =
            ViewModelProvider(viewModelStore, Injection.provideViewModelFactory(view.context)).get(ListViewModel::class.java)

        val adapter = NewsAdapter {
            news -> viewModel.newsItemClicked(news)
        }

        viewModel.newsClickEvent.observe(viewLifecycleOwner, Observer(::newsClicked))

        news_recycler.layoutManager = LinearLayoutManager(view.context)
        news_recycler.setHasFixedSize(true)
        news_recycler.adapter = adapter.withLoadStateFooter(
            footer = NewsLoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            viewModel.result.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun newsClicked(news: News) {
        val bundle = Bundle()
        bundle.putString("url", news.urlToArticle)
        findNavController().navigate(R.id.action_newsListFragment_to_webViewFragment, bundle)
    }
}