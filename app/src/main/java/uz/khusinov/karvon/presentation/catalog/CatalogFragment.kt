package uz.khusinov.karvon.presentation.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.khusinov.karvon.R
import uz.khusinov.karvon.databinding.FragmentCatalogBinding
import uz.khusinov.karvon.databinding.FragmentLoginBinding
import uz.khusinov.karvon.presentation.BaseFragment
import uz.khusinov.karvon.presentation.catalog.components.CatalogsAdapter
import uz.khusinov.karvon.presentation.login.LoginViewModel
import uz.khusinov.karvon.presentation.shops.components.ShopsAdapter
import uz.khusinov.marjonamarketcourier2.utills.launchAndRepeatWithViewLifecycle
import uz.khusinov.marjonamarketcourier2.utills.viewBinding

@AndroidEntryPoint
class CatalogFragment : BaseFragment(R.layout.fragment_catalog) {

    private val binding by viewBinding { FragmentCatalogBinding.bind(it) }
    private val viewModel by viewModels<CatalogViewModel>()
    private val adapter by lazy { CatalogsAdapter(::onItemClicked) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.apply {
            rvCatalog.adapter = adapter
        }
    }

    private fun setupObserver() {
        launchAndRepeatWithViewLifecycle {
            viewModel.categoryPaging.collectLatest { data ->
                adapter.submitData(data)
            }
        }
    }

    private fun onItemClicked(id: Int) {

    }
}