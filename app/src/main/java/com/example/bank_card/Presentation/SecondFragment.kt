package com.example.bank_card.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.bank_card.Data.api.room.BinCardInfo
import com.example.bank_card.Data.api.room.BinCardInfoDao
import com.example.bank_card.Data.api.room.MainDb
import com.example.bank_card.R
import com.example.bank_card.databinding.FragmentSecondBinding
import kotlin.concurrent.thread


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: BinCardInfoDao


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MainDb.getDb(requireContext()).cardInfoDao()

        db.getAll().asLiveData().observe(this.viewLifecycleOwner) { list ->
            binding.historyList.text = ""
            list.forEach {
                val text =
                    "BIN: ${it.bin}," +
                            " brand: ${it.brand}," +
                            " country: ${it.countryName}," +
                            " bank: ${it.bankInfo.bankName}\n"

                binding.historyList.append(text)
            }
        }

        binding.clearHistory.setOnClickListener {
            thread {
                db.deleteAll()
            }

        }


        binding.homeButton.setOnClickListener {
            openHomePage()
        }
    }

    private fun openHomePage() {
        findNavController().navigate(R.id.action_second_Fragment_to_first_Fragment)
    }
}