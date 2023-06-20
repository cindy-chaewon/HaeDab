package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryExcelBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryExcelFragment: BaseFragment<FragmentCategoryExcelBinding>(FragmentCategoryExcelBinding::bind, R.layout.fragment_category_excel) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("엑셀함수", R.drawable.ic_excel_1),
        Category("엑셀자격증 준비", R.drawable.ic_excel_2),
        Category("엑셀기초", R.drawable.ic_excel_3),
        Category("엑셀표/차트", R.drawable.ic_excel_4),
        Category("엑셀통계", R.drawable.ic_excel_5),
        Category("실무엑셀", R.drawable.ic_excel_6),
        Category("오피스꿀팁", R.drawable.ic_excel_7),
        Category("PPT", R.drawable.ic_excel_8)
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