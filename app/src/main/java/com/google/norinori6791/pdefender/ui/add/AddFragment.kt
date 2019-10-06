package com.google.norinori6791.pdefender.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.FragmentAddBindingImpl
import com.google.norinori6791.pdefender.ui.home.HomeFragment
import com.google.norinori6791.pdefender.ui.top.TopFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class AddFragment : Fragment() {
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = AddViewModel()

        // 追加
        viewModel.onAdd.observe(this, Observer {
            moveFragment(it, R.id.nav_host_fragment)
        })

        // キャンセル
        viewModel.onCancel.observe(this, Observer {
            moveFragment(it, R.id.nav_host_fragment)
        })

        val binding: FragmentAddBindingImpl = DataBindingUtil.inflate(inflater,R.layout.fragment_add, container, false)
        binding.add = viewModel
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.toolbar.title = getString(R.string.menu_add)
    }

    private fun moveFragment(check: Boolean, viewid: Int){
        if(check){
            val transaction = fragmentManager?.beginTransaction()
            val topFragment = TopFragment()
            transaction?.replace(viewid, topFragment)
            transaction?.commit()
        }
    }
}
