package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryTaskBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryTaskFragment: BaseFragment<FragmentCategoryTaskBinding>(FragmentCategoryTaskBinding::bind, R.layout.fragment_category_task) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("에세이", R.drawable.ic_task_1),
        Category("레포트양식", R.drawable.ic_task_2),
        Category("보고서", R.drawable.ic_task_3),
        Category("논문검색", R.drawable.ic_task_4),
        Category("자료조사", R.drawable.ic_task_5),
        Category("설문조사", R.drawable.ic_task_6),
        Category("통계자료", R.drawable.ic_task_7),
        Category("독후감", R.drawable.ic_task_8)
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