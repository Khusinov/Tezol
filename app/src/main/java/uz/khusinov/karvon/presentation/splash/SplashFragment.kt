package uz.khusinov.karvon.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentSplashBinding
import uz.khusinov.marjonamarketcourier2.utills.viewBinding


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding { FragmentSplashBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {




        }
    }



}