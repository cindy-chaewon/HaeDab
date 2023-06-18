package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategorySelfBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategorySelfFragment: BaseFragment<FragmentCategorySelfBinding>(FragmentCategorySelfBinding::bind, R.layout.fragment_category_self) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("취업준비", R.drawable.ic_self_1),
        Category("자기소개서", R.drawable.ic_self_2),
        Category("이력서", R.drawable.ic_self_3),
        Category("1분 자기소개", R.drawable.ic_self_4),
        Category("먼졉 예상질문", R.drawable.ic_self_5),
        Category("영어 자기소개서", R.drawable.ic_self_6)
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