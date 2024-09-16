package uz.khusinov.karvon.presentation.basket

import android.os.Bundle
import android.view.View
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentBasketBinding
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.marjonamarketcourier2.utills.viewBinding


class BasketFragment : BaseFragment(R.layout.fragment_basket) {
    private val binding by viewBinding { FragmentBasketBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {






        }
    }
}