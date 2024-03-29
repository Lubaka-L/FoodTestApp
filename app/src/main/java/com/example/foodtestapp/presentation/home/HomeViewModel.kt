package com.example.foodtestapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ResultWrapper
import com.example.domain.models.Banner
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory
import com.example.domain.repository.Repository
import com.example.foodtestapp.R
import com.example.foodtestapp.presentation.home.adapters.DishCategoryAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val ALL_SECTION = "All"

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val allDishesInnerListEmptyByDefault = mutableListOf<Dish>()

    private val _dishCategoriesEmptyByDefaultInnerFlow: MutableStateFlow<List<DishCategory>> =
        MutableStateFlow(listOf())


    private val _selectedDishesCategory: MutableStateFlow<DishCategory> =
        MutableStateFlow(DishCategory(ALL_SECTION))

    private val _dishCategoryFlowToFragment: MutableStateFlow<List<DishCategoryAdapter.SelectedDishCategoryModelRv>> =
        MutableStateFlow(listOf())
    val dishCategoryFlowToFragment: StateFlow<List<DishCategoryAdapter.SelectedDishCategoryModelRv>> =
        _dishCategoryFlowToFragment.asStateFlow()


    private val _dishes: MutableStateFlow<ResultWrapper<List<Dish>>?> = MutableStateFlow(null)
    val dishes = _dishes.asStateFlow()

    val bannersList =
        listOf(Banner(R.drawable.banner1), Banner(R.drawable.banner2), Banner(R.drawable.banner3))

    init {
        loadCategories()
        loadDishes()

        _dishCategoriesEmptyByDefaultInnerFlow.combine(_selectedDishesCategory) { categories, selectedDishCategory ->
            _dishCategoryFlowToFragment.update {
                categories.map { category ->
                    DishCategoryAdapter.SelectedDishCategoryModelRv(
                        text = category.title,
                        isSelected = selectedDishCategory.title == category.title
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _dishCategoriesEmptyByDefaultInnerFlow.update {
                when (val categories = repository.getCategories()) {
                    is ResultWrapper.Success -> {
                        listOf(DishCategory(ALL_SECTION)) + categories.data
                    }

                    is ResultWrapper.Error -> {
                        listOf()
                    }
                }
            }
        }
    }

    private fun loadDishes() {
        viewModelScope.launch {
            val allD = repository.getDishes()
            _dishes.update {
                allD
            }
            allDishesInnerListEmptyByDefault.addAll(
                if (allD is ResultWrapper.Success) {
                    allD.data
                } else listOf()
            )
        }
    }

    fun showDishesByCategory(dishCategory: DishCategory) {
        var resultList =
            allDishesInnerListEmptyByDefault.filter { it.category == dishCategory.title }
        if (dishCategory.title == ALL_SECTION) {
            resultList = allDishesInnerListEmptyByDefault
        }
        _selectedDishesCategory.update {
            dishCategory
        }
        _dishes.update {
            ResultWrapper.Success(resultList)
        }
    }


}