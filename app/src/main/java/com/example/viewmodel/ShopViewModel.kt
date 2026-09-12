package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.ProductCatalog
import com.example.model.CartItem
import com.example.model.NavigationTab
import com.example.model.Order
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.SortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class ShopViewModel : ViewModel() {

    private val _currentTab = MutableStateFlow(NavigationTab.HOME)
    val currentTab: StateFlow<NavigationTab> = _currentTab.asStateFlow()

    private val _selectedCategory = MutableStateFlow(ProductCategory.ALL)
    val selectedCategory: StateFlow<ProductCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedSort = MutableStateFlow(SortOption.POPULARITY)
    val selectedSort: StateFlow<SortOption> = _selectedSort.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(
        listOf(
            CartItem(ProductCatalog.products[0], quantity = 1),
            CartItem(ProductCatalog.products[5], quantity = 1)
        )
    )
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _wishlistIds = MutableStateFlow<Set<String>>(
        setOf("mob_002", "elec_002")
    )
    val wishlistIds: StateFlow<Set<String>> = _wishlistIds.asStateFlow()

    private val _orders = MutableStateFlow<List<Order>>(
        listOf(
            Order(
                orderId = "OD31089421298",
                items = listOf(CartItem(ProductCatalog.products[2], 1)),
                totalAmount = 24999,
                orderDate = "10 Sep 2026",
                deliveryEstimate = "Delivered on 12 Sep 2026",
                deliveryAddress = "Flat 402, Royal Palms, MG Road, Bengaluru - 560001",
                status = "Delivered"
            )
        )
    )
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    private val _lastPlacedOrder = MutableStateFlow<Order?>(null)
    val lastPlacedOrder: StateFlow<Order?> = _lastPlacedOrder.asStateFlow()

    val totalCartPrice: Int
        get() = _cartItems.value.sumOf { it.product.price * it.quantity }

    val totalOriginalPrice: Int
        get() = _cartItems.value.sumOf { it.product.originalPrice * it.quantity }

    val totalSavings: Int
        get() = totalOriginalPrice - totalCartPrice

    val cartCount: Int
        get() = _cartItems.value.sumOf { it.quantity }

    fun setTab(tab: NavigationTab) {
        _currentTab.value = tab
    }

    fun setCategory(category: ProductCategory) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSort(sort: SortOption) {
        _selectedSort.value = sort
    }

    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
    }

    fun getFilteredProducts(): List<Product> {
        val list = ProductCatalog.searchProducts(_searchQuery.value, _selectedCategory.value)
        return when (_selectedSort.value) {
            SortOption.POPULARITY -> list.sortedByDescending { it.ratingCount }
            SortOption.PRICE_LOW_HIGH -> list.sortedBy { it.price }
            SortOption.PRICE_HIGH_LOW -> list.sortedByDescending { it.price }
            SortOption.RATING -> list.sortedByDescending { it.rating }
        }
    }

    fun addToCart(product: Product) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            current[index] = current[index].copy(quantity = current[index].quantity + 1)
        } else {
            current.add(CartItem(product, 1))
        }
        _cartItems.value = current
    }

    fun updateCartQuantity(productId: String, delta: Int) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == productId }
        if (index >= 0) {
            val newQty = current[index].quantity + delta
            if (newQty <= 0) {
                current.removeAt(index)
            } else {
                current[index] = current[index].copy(quantity = newQty)
            }
            _cartItems.value = current
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.value = _cartItems.value.filter { it.product.id != productId }
    }

    fun toggleWishlist(productId: String) {
        val current = _wishlistIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        _wishlistIds.value = current
    }

    fun isWishlisted(productId: String): Boolean {
        return _wishlistIds.value.contains(productId)
    }

    fun placeOrder(deliveryAddress: String = "Flat 402, Royal Palms, MG Road, Bengaluru - 560001") {
        if (_cartItems.value.isEmpty()) return

        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val todayStr = dateFormat.format(Date())
        val order = Order(
            orderId = "OD" + (10000000000L + (Math.random() * 89999999999L).toLong()).toString(),
            items = _cartItems.value.toList(),
            totalAmount = totalCartPrice,
            orderDate = todayStr,
            deliveryEstimate = "Expected by Tomorrow, 5 PM",
            deliveryAddress = deliveryAddress,
            status = "Order Confirmed"
        )

        _orders.value = listOf(order) + _orders.value
        _lastPlacedOrder.value = order
        _cartItems.value = emptyList()
    }

    fun dismissOrderSuccess() {
        _lastPlacedOrder.value = null
    }
}
