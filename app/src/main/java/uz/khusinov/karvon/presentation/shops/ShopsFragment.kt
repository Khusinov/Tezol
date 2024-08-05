package uz.khusinov.karvon.presentation.shops

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentShopsBinding
import uz.khusinov.karvon.domain.model.shop.Shop
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.karvon.presentation.shops.components.ShopsAdapter
import uz.khusinov.marjonamarketcourier2.utills.UiStateList
import uz.khusinov.marjonamarketcourier2.utills.launchAndRepeatWithViewLifecycle
import uz.khusinov.marjonamarketcourier2.utills.viewBinding

class ShopsFragment : BaseFragment(R.layout.fragment_shops) {

    private val binding by viewBinding { FragmentShopsBinding.bind(it) }
    private val viewModel by viewModels<ShopsViewModel>()
    private val adapter by lazy { ShopsAdapter(::onItemClicked) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        viewModel.getShops()
    }

    private fun setupUI() {
        binding.apply {
            rvShops.adapter = adapter
        }
    }

    private fun setupObserver() {

        launchAndRepeatWithViewLifecycle {
            viewModel.shopsState.collect {

                when (it) {
                    is UiStateList.LOADING -> {
                        showProgress()
                    }

                    is UiStateList.SUCCESS -> {
                        hideProgress()
                        adapter.submitList(it.data)
                    }

                    is UiStateList.ERROR -> {
                        hideProgress()
                        showToast(it.message)
                    }

                    else -> {
                    }
                }
            }
        }
    }

    private fun onItemClicked(shop: Shop) {
        val bundle = Bundle()
        bundle.putString("id", shop.id.toString())
        bundle.putParcelable("shop", shop)

        requireActivity().findNavController(R.id.main_container).navigate(
            R.id.action_mainFragment_to_selectedShopFragment,
            bundle
        )
    }
}