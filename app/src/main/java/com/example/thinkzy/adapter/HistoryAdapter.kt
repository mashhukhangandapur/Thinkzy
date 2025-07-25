package com.example.thinkzy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thinkzy.databinding.HistoryItemBinding
import com.example.thinkzy.models.History

class HistoryAdapter(var ListHistory : ArrayList<History>) : RecyclerView.Adapter<HistoryAdapter.HistoryCoinViewHolder>() {
    
    class HistoryCoinViewHolder(
        var binding : HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryCoinViewHolder {
        return HistoryCoinViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {

    holder.binding.timeDate.text = ListHistory[position].timeAndDate
    holder.binding.coin.text = ListHistory[position].coin
    }

    override fun getItemCount(): Int = ListHistory.size

}