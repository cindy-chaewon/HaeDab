package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryContentsBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryContentsFragment: BaseFragment<FragmentCategoryContentsBinding>(FragmentCategoryContentsBinding::bind, R.layout.fragment_category_contents) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("마케팅", R.drawable.ic_contents_1),
        Category("SNS광고", R.drawable.ic_contents_2),
        Category("SNS이벤트", R.drawable.ic_contents_3),
        Category("카드뉴스", R.drawable.ic_contents_4),
        Category("바이럴", R.drawable.ic_contents_5),
        Category("체험단", R.drawable.ic_contents_6),
        Category("브랜딩", R.drawable.ic_contents_7),
        Category("스크립트", R.drawable.ic_contents_8)
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
                parentFragmentManager.beginTransaction().replace(R.id.main_frame, ChattingFragment()).commit()
            }
        })

    }
}