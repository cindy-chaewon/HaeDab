package com.example.haedab.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryIdeaBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class CategoryIdeaFragment: BaseFragment<FragmentCategoryIdeaBinding>(FragmentCategoryIdeaBinding::bind, R.layout.fragment_category_idea) {

    lateinit var mAdView : AdView

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("소설쓰기", R.drawable.ic_idea_1),
        Category("트위터 콘텐츠", R.drawable.ic_idea_2),
        Category("페이스북 콘텐츠", R.drawable.ic_idea_3),
        Category("인스타그램 콘텐츠", R.drawable.ic_idea_4),
        Category("해답과 함께 놀기", R.drawable.ic_idea_5),
        Category("레시피", R.drawable.ic_idea_6),
        Category("여행꿀팁", R.drawable.ic_idea_7),
        Category("무엇이든 물어보세요!", R.drawable.ic_idea_8)
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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