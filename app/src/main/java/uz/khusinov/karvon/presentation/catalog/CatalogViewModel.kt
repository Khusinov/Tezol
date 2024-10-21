package uz.khusinov.karvon.presentation.catalog

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.khusinov.karvon.domain.use_case.category.CategoryUseCases
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

}