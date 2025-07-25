package com.example.thinkzy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thinkzy.R
import com.example.thinkzy.adapter.HistoryAdapter
import com.example.thinkzy.databinding.FragmentHistoryBinding
import com.example.thinkzy.models.History
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryFragment : Fragment() {

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private var historyList = ArrayList<History>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }
        binding.dollar.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }

        historyList.add(History("27 November, 2024 - 07:50 PM", "$50"))
        historyList.add(History("18 January, 2025 - 02:30 PM", "$100"))
        historyList.add(History("03 March, 2025 - 09:15 AM", "$600"))

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = HistoryAdapter(historyList)
        binding.historyRecyclerView.setHasFixedSize(true)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}