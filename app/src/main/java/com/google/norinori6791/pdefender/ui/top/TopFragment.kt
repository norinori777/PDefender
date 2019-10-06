package com.google.norinori6791.pdefender.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.FragmentTopBindingImpl
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import com.google.norinori6791.pdefender.model.repository.AuthInfoItems
import com.google.norinori6791.pdefender.ui.show.ShowFragment
import com.google.norinori6791.pdefender.ui.top.view.ListAuthInfoAdapter
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_top.view.*

class TopFragment : Fragment() {
    private lateinit var topViewModel: TopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTopBindingImpl = DataBindingUtil.inflate(inflater,R.layout.fragment_top, container, false)
        topViewModel = TopViewModel()
        binding.items = topViewModel

        // show遷移
        // 追加
        topViewModel.onShow.observe(this, Observer {
            moveFragment(it, R.id.nav_host_fragment)
        })

        // データ取得
        val instance = AuthInfoItems()
        val listItems = instance.getItems()

        val adapter = ListAuthInfoAdapter(this.context, topViewModel)
        adapter.items = listItems
        binding.authInfoRecyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.authInfoRecyclerview.adapter = adapter

        // 線追加
        val decorator = DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation)
        binding.authInfoRecyclerview.addItemDecoration(decorator)

        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.toolbar.title = getString(R.string.menu_top)
    }

    private fun moveFragment(item: AuthInfo, viewid: Int){
        val transaction = fragmentManager?.beginTransaction()
        val showFragment = ShowFragment()

        val bundle = Bundle()
        bundle.putSerializable("item", item)
        showFragment.arguments = bundle
        transaction?.replace(viewid, showFragment)
        transaction?.commit()
    }
}