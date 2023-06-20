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
        Category("외국어공부", R.drawable.category_2),
        Category("블로그", R.drawable.category_3),
        Category("자기소개서", R.drawable.category_4),
        Category("콘텐츠제작", R.drawable.category_5),
        Category("엑셀", R.drawable.category_6),
        Category("과제", R.drawable.category_7),
        Category("주식", R.drawable.category_8),
        Category("기타&아이디어", R.drawable.category_9),
        Category("이메일", R.drawable.category_10),

    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰
        val categoryFirstAdapter = CategoryFirstAdapter(categoryList)
        binding.categoryRv.apply {
            adapter = categoryFirstAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            //addItemDecoration(CategoryHorizontalItemDecoration())
            addItemDecoration(GridSpaceItemDecoration(2, 50, 35))
        }

        //리사이클러뷰 클릭
        categoryFirstAdapter.setOnItemClickListener(object : CategoryFirstAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                when(position){
                    0 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryCodingFragment()).commit()
                    }
                    1 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryForeignFragment()).commit()
                    }
                    2 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryBlogFragment()).commit()
                    }
                    3 ->{
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategorySelfFragment()).commit()
                    }
                    4 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryContentsFragment()).commit()
                    }
                    5 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryExcelFragment()).commit()
                    }
                    6 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryTaskFragment()).commit()
                    }
                    7 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryStockFragment()).commit()
                    }
                    8 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryIdeaFragment()).commit()
                    }
                    9 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryEmailFragment()).commit()
                    }
                    else -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryCodingFragment()).commit()
                    }
                }
            }
        })

    }
}