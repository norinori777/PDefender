package com.google.norinori6791.pdefender.ui.web

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.FragmentWebBindingImpl
import com.google.norinori6791.pdefender.ui.webview.WebModel
import kotlinx.android.synthetic.main.app_bar_main.*


class WebFragment : Fragment() {

    private lateinit var webModel: WebModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webModel =
            ViewModelProviders.of(this).get(WebModel::class.java)
        val binding: FragmentWebBindingImpl = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false)

        val extras: Bundle? = arguments

        val url = extras?.getString("url")

//        homeViewModel.text.observe(this, Observer {
            binding.displayWv.getSettings().setJavaScriptEnabled(true)
            binding.displayWv.addJavascriptInterface(MyJavaScriptInterface(activity!!.applicationContext), "HtmlViewer")

            // URL変更イベントを取得できる
            binding.displayWv.setWebViewClient(object: WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    Log.v("geturl", url)
                    webModel.text.postValue(url)
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.evaluateJavascript(
                        "javascript:window.HtmlViewer.showHTML" +
                                "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');",
                        null
                    )
                }
            })
            binding.displayWv.loadUrl(url)
//        })


        var root = binding.getRoot()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.toolbar.title = "WebView"
    }
}

class MyJavaScriptInterface(val ctx: Context) {
    @JavascriptInterface
    fun showHTML(html: String){
        AlertDialog.Builder(ctx).setTitle("HTML").setMessage(html).setPositiveButton(android.R.string.ok, null).setCancelable(false).create().show()
    }
}