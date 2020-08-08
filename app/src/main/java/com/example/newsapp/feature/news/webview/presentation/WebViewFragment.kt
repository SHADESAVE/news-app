package com.example.newsapp.feature.news.webview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.newsapp.R
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