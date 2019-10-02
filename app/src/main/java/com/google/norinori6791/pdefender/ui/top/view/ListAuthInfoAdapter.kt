package com.google.norinori6791.pdefender.ui.top.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.norinori6791.pdefender.R
import com.google.norinori6791.pdefender.databinding.AuthinfoItemBinding
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import com.google.norinori6791.pdefender.ui.show.ShowFragment
import com.google.norinori6791.pdefender.ui.top.TopViewModel

class ListAuthInfoAdapter(context:Context?, viewModel: TopViewModel): RecyclerView.Adapter<AuthInfoHolder>() {
    var items: List<AuthInfo> = emptyList()
    val viewModel = viewModel
    private val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthInfoHolder {
        val binding: AuthinfoItemBinding = DataBindingUtil.inflate(inflater, R.layout.authinfo_item, parent, false)
        binding.authinfoView.setOnClickListener(ItemClickListener(binding))
        return AuthInfoHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthInfoHolder, position: Int) {
        holder.binding.item = items[position]
    }

    private inner class ItemClickListener(val binding: AuthinfoItemBinding): View.OnClickListener{
        override fun onClick(view: View) {
//            val item = AuthInfo(
//                1,
//                binding.categoryTv.text.toString().toInt(),
//                binding.titleTv.text.toString(),
//                binding.urlTv.text.toString(),
//                binding.uidTv.text.toString(),
//                binding.passwordTv.text.toString(),
//                binding.memoTv.text.toString()
//            )

            viewModel.onShow.postValue(binding.item)
        }
    }
}