package com.uma.tictaktoe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uma.tictaktoe.R
import com.uma.tictaktoe.databinding.InflateItemBinding

/**
 * Created by Umapathi on 2019-10-23.
 * Copyright Indyzen Inc, @2019
 */
class TicTakToeAdapter : RecyclerView.Adapter<TicTakToeAdapter.TicTakToeViewHolder>() {

    lateinit var inflateItemBinding: InflateItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicTakToeViewHolder {
        inflateItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.inflate_item,
            parent,
            false
        )
        return TicTakToeViewHolder(inflateItemBinding)
    }

    override fun getItemCount(): Int = 9

    override fun onBindViewHolder(holder: TicTakToeViewHolder, position: Int) {
    }

    inner class TicTakToeViewHolder(var v: InflateItemBinding) : RecyclerView.ViewHolder(v.root)
}