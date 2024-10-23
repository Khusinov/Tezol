package uz.khusinov.karvon.presentation.home

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentHomeBinding
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.karvon.presentation.home.components.AdsAdapter
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import uz.khusinov.marjonamarketcourier2.utills.launchAndRepeatWithViewLifecycle
import uz.khusinov.marjonamarketcourier2.utills.viewBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var adsAdapter: AdsAdapter
    private var pagerList: ArrayList<String> = ArrayList()
    var adsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getAds()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupAdsObserver()
    }

    private fun setupAdsObserver() {
        launchAndRepeatWithViewLifecycle {
            homeViewModel.getAdsState.collect {
                when (it) {
                    is UiStateObject.LOADING -> showProgress()

                    is UiStateObject.SUCCESS -> {
                        hideProgress()
                        pagerList.clear()
                        pagerList.addAll(it.data.results)
                        setAds()
                    }

                    is UiStateObject.ERROR -> {
                        hideProgress()
                        showToast(it.message)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun setupUI() = with(binding) {

    }

    private fun setAds() = with(binding) {
        adsAdapter = AdsAdapter(requireContext(), pagerList)
        viewPager.adapter = adsAdapter
        viewPager.offscreenPageLimit = pagerList.size
        dotsIndicator.attachTo(viewPager)
        changeFlatImage()
    }

    private fun changeFlatImage() {
        adsCount++
        if (adsCount >= pagerList.size)
            adsCount = 0
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    binding.viewPager.currentItem = adsCount
                    changeFlatImage()
                }
            }
        }.start()
    }
}