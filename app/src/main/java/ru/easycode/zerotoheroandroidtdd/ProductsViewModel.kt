package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.easycode.zerotoheroandroidtdd.data.ProductsRepository
import ru.easycode.zerotoheroandroidtdd.domain.Product

class ProductsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ProductsRepository,
    private val runAsync: RunAsync
) : ViewModel() {

    private val _productsUiListStateFlow =
        MutableStateFlow<List<ProductListUi>>(emptyList())
    val productsUiListStateFlow = _productsUiListStateFlow.asStateFlow()

    private val _ordersUiListStateFlow =
        MutableStateFlow<List<OrderUi>>(emptyList())
    val ordersUiListStateFlow = _ordersUiListStateFlow.asStateFlow()

    private val _filtersUiListStateFlow =
        MutableStateFlow<List<FilterUi>>(emptyList())
    val filtersUiListStateFlow = _filtersUiListStateFlow.asStateFlow()

    private var products = emptyList<Product>()

    private var currentOrder = "alphabet"

    init {
        runAsync.run(
            scope = viewModelScope,
            background = {
                products = repository.products()

                _ordersUiListStateFlow.value =
                    repository.orderList().mapIndexed { index, name ->
                        OrderUi(
                            name = name,
                            chosen = index == 0
                        )
                    }

                _filtersUiListStateFlow.value =
                    repository.filters().map {
                        FilterUi(
                            id = it.id,
                            category = it.category,
                            value = it.name,
                            chosen = false
                        )
                    }

                updateProducts()
            })
    }

    fun chooseOrder(name: String) {
        currentOrder = name

        _ordersUiListStateFlow.value =
            _ordersUiListStateFlow.value.map {
                it.copy(chosen = it.name == name)
            }

        updateProducts()
    }

    fun chooseFilter(id: Int) {
        val current = _filtersUiListStateFlow.value
        val target = current.first { it.id == id }

        _filtersUiListStateFlow.value = current.map {
            if (it.id == id) {
                it.copy(chosen = !it.chosen)
            } else if (it.category == target.category && it.chosen) {
                it.copy(chosen = false)
            } else {
                it
            }
        }

        updateProducts()
    }

    fun unchooseFilter(id: Int) {
        _filtersUiListStateFlow.value = _filtersUiListStateFlow.value.map {
            if (it.id == id) it.copy(chosen = false) else it
        }
        updateProducts()
    }

    private fun updateProducts() {
        var result = products

        val chosenFilters = _filtersUiListStateFlow.value.filter { it.chosen }
        if (chosenFilters.isNotEmpty()) {
            result = result.filter { product ->
                chosenFilters.all { filter ->
                    when (filter.category) {
                        "os" -> product.os == filter.value
                        "RAM" -> product.ram == filter.value.toIntOrNull()
                        else -> true
                    }
                }
            }
        }

        result = when (currentOrder) {
            "alphabet" ->
                result.sortedBy { it.name }

            "price: low to high" ->
                result.sortedBy { priceToInt(it.price) }

            "price: high to low" ->
                result.sortedByDescending { priceToInt(it.price) }

            else -> result
        }

        _productsUiListStateFlow.value =
            if (result.isEmpty()) {
                listOf(ProductListUi.Empty)
            } else {
                result.map {
                    ProductListUi.Base(
                        id = it.id,
                        name = it.name,
                        price = it.price,
                        os = it.os,
                        ram = it.ram
                    )
                }
            }
    }

    private fun priceToInt(price: String): Int {
        return price.removeSuffix("$").toIntOrNull() ?: 0
    }
}

class ProductsViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: ProductsRepository,
    private val runAsync: RunAsync,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ProductsViewModel(
            savedStateHandle = handle,
            repository = repository,
            runAsync = runAsync
        ) as T
    }
}
