package com.google.norinori6791.pdefender.ui.show

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.FragmentShowBindingImpl
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import com.google.norinori6791.pdefender.ui.home.HomeFragment

class ShowFragment : Fragment() {

    private lateinit var viewModel: ShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ShowViewModel()

        val binding: FragmentShowBindingImpl = DataBindingUtil.inflate(inflater,R.layout.fragment_show, container, false)

        val extras: Bundle? = arguments

        val item = extras?.getSerializable("item") as AuthInfo

        viewModel.item.set(item)
        binding.viewModel = viewModel


        // WebViewに遷移
        viewModel.onMove.observe(this, Observer {
            moveFragment(it, R.id.nav_host_fragment)
        })

        // クリップボードコピー
        viewModel.onCopy.observe(this, Observer {
            var clipBordManager= this.context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipBordManager.setPrimaryClip(ClipData.newPlainText("", it.text))
            if(it.notify){
                Toast.makeText(this.context, getString(R.string.copy_ok) + "\n" + it.text, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, getString(R.string.copy_ok), Toast.LENGTH_SHORT).show()
            }
        })
        var root = binding.getRoot()
        return root

//        val BORDER_WEIGHT = 2
//        val borderDrawable = GradientDrawable()
//        borderDrawable.setStroke(BORDER_WEIGHT, 0x55ffffff)
//        val drawables: Array<Drawable> = {borderDrawable}
//        val layerDrawable = LayerDrawable(drawables)
//        layerDrawable.setLayerInset(0, 0, -BORDER_WEIGHT, 0, -BORDER_WEIGHT)
//
//        binding.showTitleValueTv.setBackground(layerDrawable)


    }
    private fun moveFragment(url: String, viewid: Int){
        val transaction = fragmentManager?.beginTransaction()
        val homeFragment = HomeFragment()

        val bundle = Bundle()
        bundle.putString("url", url)
        homeFragment.arguments = bundle
        transaction?.replace(viewid, homeFragment)
        transaction?.commit()
    }
}