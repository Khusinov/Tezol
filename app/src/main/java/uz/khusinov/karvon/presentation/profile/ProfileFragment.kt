package uz.khusinov.karvon.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.khusinov.karvon.R
import uz.khusinov.karvon.SharedPref
import uz.khusinov.karvon.databinding.FragmentProfileBinding
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.marjonamarketcourier2.utills.viewBinding
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding by viewBinding { FragmentProfileBinding.bind(it) }
    @Inject
    lateinit var sharedPref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

            logoutBtn.setOnClickListener {
                sharedPref.isEntered = false

            }

        }
    }

}
