package com.example.foodtestapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.core.ResultWrapper
import com.example.domain.models.DishCategory
import com.example.foodtestapp.databinding.FragmentHomeBinding
import com.example.foodtestapp.presentation.home.adapters.BannersAdapter
import com.example.foodtestapp.presentation.home.adapters.DishAdapter
import com.example.foodtestapp.presentation.home.adapters.DishCategoryAdapter
import com.example.foodtestapp.presentation.home.adapters.DishCategoryAdapterDelegate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), DishCategoryAdapterDelegate {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private var bannersAdapter: BannersAdapter? = null
    private var dishCategoryAdapter: DishCategoryAdapter? = null
    private var dishAdapter: DishAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannersAdapter = BannersAdapter()
        binding.bannersRV.adapter = bannersAdapter

        dishCategoryAdapter = DishCategoryAdapter(this)
        binding.categoriesRV.adapter = dishCategoryAdapter

        dishAdapter = DishAdapter()
        binding.dishesRV.adapter = dishAdapter

        bannersAdapter?.submitList(viewModel.bannersList)

        lifecycleScope.launch {
            viewModel.dishCategoryFlowToFragment.collectLatest { result ->
                dishCategoryAdapter?.submitList(result)
            }
        }

        lifecycleScope.launch {
            viewModel.dishes.collectLatest { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        dishAdapter?.submitList(result.data)
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onDishCategoryClicked(dishCategory: DishCategoryAdapter.SelectedDishCategoryModelRv) {
        viewModel.showDishesByCategory(DishCategory(dishCategory.text))
    }

}