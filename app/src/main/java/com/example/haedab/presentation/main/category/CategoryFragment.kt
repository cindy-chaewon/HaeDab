package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::bind, R.layout.fragment_category) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("코딩", R.drawable.category_1),
        Category("외국어공부", R.drawable.category_1),
        Category("블로그", R.drawable.category_1),
        Category("자기소개서", R.drawable.category_1),
        Category("콘텐츠제작", R.drawable.category_1),
        Category("엑셀", R.drawable.category_1),
        Category("과제", R.drawable.category_1),
        Category("주식", R.drawable.category_1),
        Category("기타&아이디어", R.drawable.category_1),
        Category("이메일", R.drawable.category_1),

    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰
        val categoryFirstAdapter = CategoryFirstAdapter(categoryList)
        binding.categoryRv.apply {
            adapter = categoryFirstAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(CategoryHorizontalItemDecoration())
            addItemDecoration(CategoryVerticalItemDecoration())
        }

    }
}