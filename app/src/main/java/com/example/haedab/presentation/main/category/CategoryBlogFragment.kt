package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryBlogBinding
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryBlogFragment : BaseFragment<FragmentCategoryBlogBinding>(FragmentCategoryBlogBinding::bind, R.layout.fragment_category_blog){

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("키워드", R.drawable.ic_blog_1),
        Category("원고작성", R.drawable.ic_blog_2),
        Category("트렌드", R.drawable.ic_blog_3),
        Category("콘텐츠종류", R.drawable.ic_blog_4),
        Category("디지털노마드 되기", R.drawable.ic_blog_5),
        Category("네이버블로그", R.drawable.ic_blog_6),
        Category("티스토리블로그", R.drawable.ic_blog_7),
        Category("블로그체험단", R.drawable.ic_blog_8),
        Category("블로그수익", R.drawable.ic_blog_9),
        Category("블로그광고", R.drawable.ic_blog_10)
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