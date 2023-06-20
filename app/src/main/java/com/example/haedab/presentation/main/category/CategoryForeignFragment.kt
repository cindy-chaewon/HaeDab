package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryForeignBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryForeignFragment: BaseFragment<FragmentCategoryForeignBinding>(FragmentCategoryForeignBinding::bind, R.layout.fragment_category_foreign) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("사전", R.drawable.ic_foreign_1),
        Category("발음", R.drawable.ic_foreign_2),
        Category("작문", R.drawable.ic_foreign_3),
        Category("번역", R.drawable.ic_foreign_4),
        Category("문법", R.drawable.ic_foreign_5),
        Category("독해", R.drawable.ic_foreign_6)
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