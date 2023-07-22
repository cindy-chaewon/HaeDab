package haedab.haedab.haedab.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentCategorySelfBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class CategorySelfFragment: BaseFragment<FragmentCategorySelfBinding>(FragmentCategorySelfBinding::bind, R.layout.fragment_category_self) {

    lateinit var mAdView : AdView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.self1), R.drawable.ic_self_1),
            Category(getString(R.string.self2), R.drawable.ic_self_2),
            Category(getString(R.string.self3), R.drawable.ic_self_3),
            Category(getString(R.string.self4), R.drawable.ic_self_4),
            Category(getString(R.string.self5), R.drawable.ic_self_5),
            Category(getString(R.string.self6), R.drawable.ic_self_6)
        )

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
                activity?.let {
                    val intent = Intent(context, ChattingActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


    }
}