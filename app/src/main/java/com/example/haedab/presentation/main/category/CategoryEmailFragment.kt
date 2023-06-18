package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryEmailBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryEmailFragment: BaseFragment<FragmentCategoryEmailBinding>(FragmentCategoryEmailBinding::bind, R.layout.fragment_category_email) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("맞춤법 검사", R.drawable.ic_email_1),
        Category("외국어 메일 작성", R.drawable.ic_email_2),
        Category("업무메일 작성", R.drawable.ic_email_3),
        Category("홍보메일 작성", R.drawable.ic_email_4)
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