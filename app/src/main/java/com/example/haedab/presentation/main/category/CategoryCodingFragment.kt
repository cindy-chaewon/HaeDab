package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryCodingFragment: BaseFragment<FragmentCategoryCodingBinding>(FragmentCategoryCodingBinding::bind, R.layout.fragment_category_coding) {


    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("파이썬", R.drawable.ic_coding_1),
        Category("자바", R.drawable.ic_coding_2),
        Category("루비", R.drawable.ic_coding_3),
        Category("HTML", R.drawable.ic_coding_4),
        Category("SQL", R.drawable.ic_coding_5),
        Category("C", R.drawable.ic_coding_6),
        Category("자바스크립트", R.drawable.ic_coding_7),
        Category("C++", R.drawable.ic_coding_8),
        Category("XML", R.drawable.ic_coding_9),
        Category("JSON", R.drawable.ic_coding_10),
        Category("BASH", R.drawable.ic_coding_11),
        Category("기타언어", R.drawable.ic_coding_12)
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