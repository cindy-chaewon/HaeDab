package haedab.haedab.haedab.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentCategoryBlogBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class CategoryBlogFragment : BaseFragment<FragmentCategoryBlogBinding>(FragmentCategoryBlogBinding::bind, R.layout.fragment_category_blog){

    lateinit var mAdView : AdView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.blog1), R.drawable.ic_blog_1),
            Category(getString(R.string.blog2), R.drawable.ic_blog_2),
            Category(getString(R.string.blog3), R.drawable.ic_blog_3),
            Category(getString(R.string.blog4), R.drawable.ic_blog_4),
            Category(getString(R.string.blog5), R.drawable.ic_blog_5),
            Category(getString(R.string.blog6), R.drawable.ic_blog_6),
            Category(getString(R.string.blog7), R.drawable.ic_blog_7),
            Category(getString(R.string.blog8), R.drawable.ic_blog_8),
            Category(getString(R.string.blog9), R.drawable.ic_blog_9),
            Category(getString(R.string.blog10), R.drawable.ic_blog_10)
        )

        //뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryFragment()).commit()
            }
        })

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
                activity?.finish()
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