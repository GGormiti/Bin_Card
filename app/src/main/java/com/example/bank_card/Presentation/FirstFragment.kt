package com.example.bank_card.Presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bank_card.Data.api.api.BankCard
import com.example.bank_card.Data.api.api.RetrofitObject
import com.example.bank_card.Data.api.room.BankInfo
import com.example.bank_card.Data.api.room.BinCardInfo
import com.example.bank_card.Data.api.room.BinCardInfoDao
import com.example.bank_card.Data.api.room.MainDb
import com.example.bank_card.Entity.ApiService
import com.example.bank_card.R
import com.example.bank_card.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val retrofit: ApiService by lazy { RetrofitObject.getApiClient() }

    private lateinit var db: BinCardInfoDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MainDb.getDb(requireContext()).cardInfoDao()

        binding.cardLoad.setOnClickListener {
            retrofit.getInfo(number = binding.editText.text.toString())
                .enqueue(object : Callback<BankCard> {
                    override fun onResponse(
                        call: Call<BankCard>,
                        response: Response<BankCard>
                    ) {
                        if (response.isSuccessful) {
                            binding.cardLoad.playAnimation()
                            val bankInfo = BankInfo(
                                response.body()?.bank?.name.toString(),
                                response.body()?.bank?.url.toString(),
                                response.body()?.bank?.phone.toString(),
                                response.body()?.bank?.city.toString()
                            )
                            val bankCard = BinCardInfo(
                                null,
                                binding.editText.text.toString(),
                                response.body()?.scheme.toString(),
                                response.body()?.type.toString(),
                                response.body()?.brand.toString(),
                                response.body()?.country?.name.toString(),
                                bankInfo
                            )
                            thread {
                                db.insert(bankCard)
                            }
                            binding.city.text = response.body()?.bank?.city
                            binding.phoneNumber.text = response.body()?.bank?.phone
                            binding.bankName.text = response.body()?.bank?.name
                            binding.url.text = response.body()?.bank?.url
                            binding.brand.text = response.body()?.brand
                            binding.country.text = response.body()?.country?.name
                            binding.scheme.text = response.body()?.scheme
                            binding.type.text = response.body()?.type
                            binding.location.text =
                                "${response.body()?.country?.latitude},${response.body()?.country?.longitude}"
                        }
                    }

                    override fun onFailure(call: Call<BankCard>, t: Throwable) {
                        Log.d("MainActivity", "Error")
                    }

                })
        }

        binding.country.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:${binding.location.text}")
            startActivity(intent)
        }


        binding.historyButton.setOnClickListener {
            openHistoryPage()
        }
    }

    private fun openHistoryPage() {
        findNavController().navigate(R.id.action_first_Fragment_to_second_Fragment)
    }

}