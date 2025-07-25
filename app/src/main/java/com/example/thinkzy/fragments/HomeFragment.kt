package com.example.thinkzy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thinkzy.R
import com.example.thinkzy.adapter.CategoryAdapter
import com.example.thinkzy.databinding.FragmentHomeBinding
import com.example.thinkzy.models.Category
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.recaptcha.internal.zzrq

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var categoryList = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryList.add(Category(R.drawable.science, "Science"))
        categoryList.add(Category(R.drawable.english, "English"))
        categoryList.add(Category(R.drawable.maths, "Mathematics"))
        categoryList.add(Category(R.drawable.programming, "Programming"))
        categoryList.add(Category(R.drawable.islamichistory, "Islamic History"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dollarImg.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }
        binding.coinTV.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }




        binding.categoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.categoryRecyclerview.adapter = CategoryAdapter(categoryList, requireActivity())
        binding.categoryRecyclerview.setHasFixedSize(true)

        fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

}