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
import haedab.haedab.haedab.databinding.FragmentCategoryIdeaBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class CategoryIdeaFragment: BaseFragment<FragmentCategoryIdeaBinding>(FragmentCategoryIdeaBinding::bind, R.layout.fragment_category_idea) {

    lateinit var mAdView : AdView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.idea1), R.drawable.ic_idea_1),
            Category(getString(R.string.idea2_1), R.drawable.ic_idea_2),
            Category(getString(R.string.idea3), R.drawable.ic_idea_3),
            Category(getString(R.string.idea4), R.drawable.ic_idea_4),
            Category(getString(R.string.idea5), R.drawable.ic_idea_5),
            Category(getString(R.string.idea6), R.drawable.ic_idea_6),
            Category(getString(R.string.idea7), R.drawable.ic_idea_7),
            Category(getString(R.string.idea8), R.drawable.ic_idea_8)
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