package com.example.haedab.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryCodingFragment: BaseFragment<FragmentCategoryCodingBinding>(FragmentCategoryCodingBinding::bind, R.layout.fragment_category_coding) {

    lateinit var mAdView : AdView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.coding1), R.drawable.ic_coding_1),
            Category(getString(R.string.coding2), R.drawable.ic_coding_2),
            Category(getString(R.string.coding3), R.drawable.ic_coding_3),
            Category(getString(R.string.coding4), R.drawable.ic_coding_4),
            Category(getString(R.string.coding5), R.drawable.ic_coding_5),
            Category(getString(R.string.coding6), R.drawable.ic_coding_6),
            Category(getString(R.string.coding7), R.drawable.ic_coding_7),
            Category(getString(R.string.coding8), R.drawable.ic_coding_8),
            Category(getString(R.string.coding9), R.drawable.ic_coding_9),
            Category(getString(R.string.coding10), R.drawable.ic_coding_10),
            Category(getString(R.string.coding11), R.drawable.ic_coding_11),
            Category(getString(R.string.coding12), R.drawable.ic_coding_12)
        )
        //리사이클러뷰
        val categorySecondAdapter = CategorySecondAdapter(categoryList)
        binding.categoryRv.apply {
            adapter = categorySecondAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(CategoryListVerticalItemDecoration())
        }

        binding.backBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryFragment()).commit()
        }
        categorySecondAdapter.setOnItemClickListener(object : CategorySecondAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                activity?.let {
                    val intent = Intent(context, ChattingActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

}