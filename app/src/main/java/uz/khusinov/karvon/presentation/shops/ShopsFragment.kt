package uz.khusinov.karvon.presentation.shops

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.khusinov.karvon.databinding.FragmentShopsBinding
import uz.khusinov.marjonamarketcourier2.utills.viewBinding

class ShopsFragment : Fragment() {
    private val binding by viewBinding { FragmentShopsBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

        }
    }
}