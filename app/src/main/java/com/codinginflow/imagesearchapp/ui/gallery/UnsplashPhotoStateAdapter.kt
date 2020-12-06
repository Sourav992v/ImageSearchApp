package com.codinginflow.imagesearchapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.imagesearchapp.databinding.UnsplashPhotoLoadStateFooterBinding

class UnsplashPhotoStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<UnsplashPhotoStateAdapter.LoadSateViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadSateViewHolder {
        val binding = UnsplashPhotoLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return LoadSateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadSateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadSateViewHolder(private val binding: UnsplashPhotoLoadStateFooterBinding) :
            RecyclerView.ViewHolder(binding.root){

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }
                fun bind(loadState: LoadState){
                    binding.apply {
                        progressBar.isVisible = loadState is LoadState.Loading
                        buttonRetry.isVisible = loadState !is LoadState.Loading
                        textViewError.isVisible = loadState !is LoadState.Loading
                    }
                }
            }
}