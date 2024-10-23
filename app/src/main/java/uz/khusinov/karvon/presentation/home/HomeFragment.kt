package uz.khusinov.karvon.presentation.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentHomeBinding
import uz.khusinov.karvon.domain.model.shop.Product
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.karvon.presentation.home.components.AdsAdapter
import uz.khusinov.karvon.presentation.home.components.HomeProductAdapter
import uz.khusinov.marjonamarketcourier2.utills.UiStateObject
import uz.khusinov.marjonamarketcourier2.utills.launchAndRepeatWithViewLifecycle
import uz.khusinov.marjonamarketcourier2.utills.navigateSafe
import uz.khusinov.marjonamarketcourier2.utills.viewBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val homeViewModel by viewModels<HomeViewModel>()
    private val newProductAdapter by lazy {
        HomeProductAdapter { product ->
            navigateToProductDetail(
                product
            )
        }
    }
    private val topProductAdapter by lazy {
        HomeProductAdapter { product ->
            navigateToProductDetail(
                product
            )
        }
    }
    private val mostSellProductAdapter by lazy {
        HomeProductAdapter { product ->
            navigateToProductDetail(
                product
            )
        }
    }

    private lateinit var adsAdapter: AdsAdapter
    private var pagerList: ArrayList<String> = ArrayList()
    var adsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getAds()
        homeViewModel.getNewProducts()
        homeViewModel.getTopProducts()
        homeViewModel.getMostSellProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupAdsObserver()
        setupNewProductObserver()
        setupTopProductObserver()
        setupMostSellProductObserver()
    }

    private fun setupUI() = with(binding) {
        rvNewProducts.adapter = newProductAdapter
        topProductsRv.adapter = topProductAdapter
        mostSoldRv.adapter = mostSellProductAdapter
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

    private fun setupNewProductObserver() {
        launchAndRepeatWithViewLifecycle {
            homeViewModel.getNewProductsState.collect {
                when (it) {
                    is UiStateObject.LOADING -> Unit

                    is UiStateObject.SUCCESS -> {
                        binding.newLinear.isVisible = it.data.results.isNotEmpty()
                        binding.rvNewProducts.isVisible = it.data.results.isNotEmpty()
                        newProductAdapter.submitList(it.data.results)
                    }

                    is UiStateObject.ERROR -> {
                        binding.newLinear.isVisible = false
                        binding.rvNewProducts.isVisible = false
                        showToast(it.message)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun setupTopProductObserver() {
        launchAndRepeatWithViewLifecycle {
            homeViewModel.getTopProductsState.collect {
                when (it) {
                    is UiStateObject.LOADING -> Unit

                    is UiStateObject.SUCCESS -> {
                        binding.topLinear.isVisible = it.data.results.isNotEmpty()
                        binding.topProductsRv.isVisible = it.data.results.isNotEmpty()
                        topProductAdapter.submitList(it.data.results)
                    }

                    is UiStateObject.ERROR -> {
                        binding.topLinear.isVisible = false
                        binding.topProductsRv.isVisible = false
                        showToast(it.message)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun setupMostSellProductObserver() {
        launchAndRepeatWithViewLifecycle {
            homeViewModel.getMostSellProductsState.collect {
                when (it) {
                    is UiStateObject.LOADING -> Unit

                    is UiStateObject.SUCCESS -> {
                        binding.mostSoldLinear.isVisible = it.data.results.isNotEmpty()
                        binding.mostSoldRv.isVisible = it.data.results.isNotEmpty()
                        mostSellProductAdapter.submitList(it.data.results)
                    }

                    is UiStateObject.ERROR -> {
                        binding.mostSoldLinear.isVisible = false
                        binding.mostSoldRv.isVisible = false
                        showToast(it.message)
                    }

                    else -> Unit
                }
            }
        }
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

    private fun navigateToProductDetail(product: Product) {
//        requireActivity().findNavController(R.id.main_container).navigateSafe(
//            R.id.action_mainFragment_to_productDetailsBottomSheet,
//            bundleOf("product" to product)
//        )
    }
}