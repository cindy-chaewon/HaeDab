package com.example.haedab.presentation.main.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryCodingBinding
import com.example.haedab.databinding.FragmentCategoryStockBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment

class CategoryStockFragment: BaseFragment<FragmentCategoryStockBinding>(FragmentCategoryStockBinding::bind, R.layout.fragment_category_stock) {

    private var categoryList: ArrayList<Category> = arrayListOf(
        Category("ETF", R.drawable.ic_stock_1),
        Category("파이어족되기", R.drawable.ic_stock_2),
        Category("부동산", R.drawable.ic_stock_3),
        Category("예금상품", R.drawable.ic_stock_4),
        Category("시황", R.drawable.ic_stock_5),
        Category("재태크", R.drawable.ic_stock_6),
        Category("펀드", R.drawable.ic_stock_7),
        Category("경제트렌드", R.drawable.ic_stock_8)
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