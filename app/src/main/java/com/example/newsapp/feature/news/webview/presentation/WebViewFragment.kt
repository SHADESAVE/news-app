package com.example.newsapp.feature.news.webview.presentation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.feature.news.list.di.ListViewModelFactory
import com.example.newsapp.feature.news.list.presentation.ListViewModel
import com.example.newsapp.feature.news.webview.di.WebViewViewModelFactory
import kotlinx.android.synthetic.main.web_view_fragment.*

class WebViewFragment : Fragment(R.layout.web_view_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.get("url") as String?

        val viewModel: WebViewViewModel by viewModels {
            WebViewViewModelFactory()
        }

        viewModel.newUrlEvent.observe(viewLifecycleOwner, Observer {
            webView.loadUrl(it)
        })

        url?.let { viewModel.newUrl(it) }
    }
}