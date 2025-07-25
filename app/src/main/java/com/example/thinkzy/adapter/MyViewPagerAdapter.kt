package com.example.thinkzy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thinkzy.fragments.SigninFragment
import com.example.thinkzy.fragments.SignupFragment

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
       return when (position){
           0 -> SignupFragment()
           1 -> SigninFragment()
           else -> throw IllegalStateException("Invalid position $position")
       }
    }
}