package haedab.haedab.haedab.presentation.main.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.recyclerview.widget.GridLayoutManager

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentCategoryBinding
import haedab.haedab.haedab.presentation.main.MainActivity
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class CategoryFragment: BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::bind, R.layout.fragment_category) {

    lateinit var mAdView : AdView

    private var backPressedOnce = false
    private var backOnce = false
    var mainActivity : MainActivity ?= null

    /*private var categoryList: ArrayList<Category> = arrayListOf(
        Category("코딩", R.drawable.category_1),
        Category("외국어공부", R.drawable.category_2),
        Category("블로그", R.drawable.category_3),
        Category("자기소개서", R.drawable.category_4),
        Category("콘텐츠제작", R.drawable.category_5),
        Category("엑셀", R.drawable.category_6),
        Category("과제", R.drawable.category_7),
        Category("주식", R.drawable.category_8),
        Category("기타&아이디어", R.drawable.category_9),
        Category("이메일", R.drawable.category_10),

    )*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (!backPressedOnce) {
                    backPressedOnce = true
                    showToast()
                    Handler(Looper.getMainLooper()).postDelayed({
                        backPressedOnce = false
                    }, 3000)
                } else {
                    //mainActivity!!.finishChattingActivity()
                    activity?.finish()
                    activity?.let { ActivityCompat.finishAffinity(it) }
                    //mainActivity!!.startChattingActivity()

                }
            }
        })

        binding.backBtn.setOnClickListener {
            if (!backOnce) {
                backOnce = true
                showToast()
                Handler(Looper.getMainLooper()).postDelayed({
                    backOnce = false
                }, 3000)
            } else {
                //mainActivity!!.startChattingActivity()
                activity?.finish()
                activity?.let { ActivityCompat.finishAffinity(it) }
            }
        }

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(getString(R.string.coding), R.drawable.category_1),
            Category(getString(R.string.foreign), R.drawable.category_2),
            Category(getString(R.string.blog), R.drawable.category_3),
            Category(getString(R.string.self), R.drawable.category_4),
            Category(getString(R.string.contents), R.drawable.category_5),
            Category(getString(R.string.excel), R.drawable.category_6),
            Category(getString(R.string.task), R.drawable.category_7),
            Category(getString(R.string.stock), R.drawable.category_8),
            Category(getString(R.string.idea2), R.drawable.category_9),
            Category(getString(R.string.email), R.drawable.category_10),

            )

        //리사이클러뷰
        val categoryFirstAdapter = CategoryFirstAdapter(categoryList)
        binding.categoryRv.apply {
            adapter = categoryFirstAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            //addItemDecoration(CategoryHorizontalItemDecoration())
            addItemDecoration(GridSpaceItemDecoration(2, 50, 35))
        }

        //리사이클러뷰 클릭
        categoryFirstAdapter.setOnItemClickListener(object : CategoryFirstAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                when(position){
                    0 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryCodingFragment()).commit()
                    }
                    1 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryForeignFragment()).commit()
                    }
                    2 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryBlogFragment()).commit()
                    }
                    3 ->{
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategorySelfFragment()).commit()
                    }
                    4 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryContentsFragment()).commit()
                    }
                    5 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryExcelFragment()).commit()
                    }
                    6 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryTaskFragment()).commit()
                    }
                    7 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryStockFragment()).commit()
                    }
                    8 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryIdeaFragment()).commit()
                    }
                    9 -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryEmailFragment()).commit()
                    }
                    else -> {
                        parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryCodingFragment()).commit()
                    }
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

    private fun showToast() {
        Toast.makeText(requireContext(), "한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}