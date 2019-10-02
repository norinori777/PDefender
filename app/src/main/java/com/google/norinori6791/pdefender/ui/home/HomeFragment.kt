package com.google.norinori6791.pdefender.ui.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.FragmentHomeBindingImpl
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val binding: FragmentHomeBindingImpl = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        var root = binding.getRoot()

        homeViewModel.text.observe(this, Observer {
            binding.homeWebview.getSettings().setJavaScriptEnabled(true)
            binding.homeWebview.addJavascriptInterface(MyJavaScriptInterface(activity!!.applicationContext), "HtmlViewer")

            // URL変更イベントを取得できる
            binding.homeWebview.setWebViewClient(object: WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    Log.v("geturl", url)
                    homeViewModel.text.postValue(url)
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.evaluateJavascript("javascript:window.HtmlViewer.showHTML" +
                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');", null)
                }
            })
            binding.homeWebview.loadUrl(it)
        })
        return root
    }
}

class MyJavaScriptInterface(val ctx: Context) {
    @JavascriptInterface
    fun showHTML(html: String){
        AlertDialog.Builder(ctx).setTitle("HTML").setMessage(html).setPositiveButton(android.R.string.ok, null).setCancelable(false).create().show()
    }
}