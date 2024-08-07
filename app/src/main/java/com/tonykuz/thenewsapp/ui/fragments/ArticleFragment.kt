package com.tonykuz.thenewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tonykuz.thenewsapp.R
import com.tonykuz.thenewsapp.databinding.FragmentArticleBinding
import com.tonykuz.thenewsapp.notifications.NotificationHelper
import com.tonykuz.thenewsapp.ui.NewsActivity
import com.tonykuz.thenewsapp.ui.NewsViewModel


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var newsViewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as NewsActivity).newsViewModel
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener {
            newsViewModel.addToFavourites(article)
            Snackbar.make(view, "Добавлено в избранное", Snackbar.LENGTH_SHORT).show()
        }

        binding.fab2.setOnClickListener {
            NotificationHelper.notificationSet(requireContext(), article)
        }
    }
}