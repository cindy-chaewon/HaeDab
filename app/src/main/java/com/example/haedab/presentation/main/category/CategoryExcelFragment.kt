package com.example.haedab.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryExcelBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryExcelFragment: BaseFragment<FragmentCategoryExcelBinding>(FragmentCategoryExcelBinding::bind, R.layout.fragment_category_excel) {

    lateinit var mAdView : AdView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.excel1), R.drawable.ic_excel_1),
            Category(getString(R.string.excel2), R.drawable.ic_excel_2),
            Category(getString(R.string.excel3), R.drawable.ic_excel_3),
            Category(getString(R.string.excel4), R.drawable.ic_excel_4),
            Category(getString(R.string.excel5), R.drawable.ic_excel_5),
            Category(getString(R.string.excel6), R.drawable.ic_excel_6),
            Category(getString(R.string.excel7), R.drawable.ic_excel_7),
            Category(getString(R.string.excel8), R.drawable.ic_excel_8)
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