package com.example.thinkzy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thinkzy.activities.QuizActivity
import com.example.thinkzy.databinding.CategoryItemBinding
import com.example.thinkzy.models.Category

class CategoryAdapter(
    var categoryList : ArrayList<Category>,
    var requireActivity : FragmentActivity
    ) : RecyclerView.Adapter<CategoryAdapter.MyCategoryViewHolder>() {
    class MyCategoryViewHolder(
        var binding : CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyCategoryViewHolder {
        return MyCategoryViewHolder(
            binding = CategoryItemBinding
                .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: MyCategoryViewHolder,
        position: Int
    ) {
        var dataList = categoryList[position]
        holder.binding.categoryImg.setImageResource(dataList.catImage)
        holder.binding.category.text = dataList.catText

        
        holder.binding.categoryBtn.setOnClickListener {
            var intent = Intent(requireActivity, QuizActivity::class.java)
            intent.putExtra("categoryImage", dataList.catImage)
            requireActivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int  = categoryList.size
}