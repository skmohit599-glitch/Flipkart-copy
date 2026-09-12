package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.ProductCatalog
import com.example.model.NavigationTab
import com.example.model.ProductCategory
import com.example.ui.components.FlipkartTopHeader
import com.example.ui.components.OrderSuccessDialog
import com.example.ui.components.ProductDetailSheet
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.WishlistScreen
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartYellow
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.ShopViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                FlipkartApp()
            }
        }
    }
}

@Composable
fun FlipkartApp(viewModel: ShopViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedSort by viewModel.selectedSort.collectAsStateWithLifecycle()
    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val wishlistIds by viewModel.wishlistIds.collectAsStateWithLifecycle()
    val orders by viewModel.orders.collectAsStateWithLifecycle()
    val lastPlacedOrder by viewModel.lastPlacedOrder.collectAsStateWithLifecycle()

    val filteredProducts = viewModel.getFilteredProducts()
    val wishlistedProducts = ProductCatalog.products.filter { wishlistIds.contains(it.id) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FlipkartTopHeader(
                cartCount = viewModel.cartCount,
                searchQuery = searchQuery,
                onSearchChange = { query ->
                    viewModel.setSearchQuery(query)
                    if (currentTab != NavigationTab.HOME && query.isNotEmpty()) {
                        viewModel.setTab(NavigationTab.HOME)
                    }
                },
                onTabSelect = { tab -> viewModel.setTab(tab) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("flipkart_bottom_navigation")
            ) {
                val tabs = listOf(
                    Triple(NavigationTab.HOME, Icons.Filled.Home, "Home"),
                    Triple(NavigationTab.CATEGORIES, Icons.Filled.Category, "Categories"),
                    Triple(NavigationTab.WISHLIST, Icons.Filled.Favorite, "Wishlist"),
                    Triple(NavigationTab.CART, Icons.Filled.ShoppingCart, "Cart"),
                    Triple(NavigationTab.ACCOUNT, Icons.Filled.Person, "Account")
                )

                tabs.forEach { (tab, icon, label) ->
                    val isSelected = currentTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.setTab(tab) },
                        icon = {
                            if (tab == NavigationTab.CART && viewModel.cartCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge(
                                            containerColor = FlipkartBlue,
                                            contentColor = Color.White,
                                            modifier = Modifier.offset(x = (-4).dp, y = 4.dp)
                                        ) {
                                            Text(
                                                text = viewModel.cartCount.toString(),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                ) {
                                    Icon(imageVector = icon, contentDescription = label)
                                }
                            } else {
                                Icon(imageVector = icon, contentDescription = label)
                            }
                        },
                        label = {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = FlipkartBlue,
                            selectedTextColor = FlipkartBlue,
                            indicatorColor = Color(0xFFE3EDFD),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF1F3F6))
        ) {
            when (currentTab) {
                NavigationTab.HOME -> {
                    HomeScreen(
                        products = filteredProducts,
                        selectedCategory = selectedCategory,
                        selectedSort = selectedSort,
                        searchQuery = searchQuery,
                        onCategorySelect = { cat -> viewModel.setCategory(cat) },
                        onSortSelect = { sort -> viewModel.setSort(sort) },
                        onProductClick = { prod -> viewModel.selectProduct(prod) },
                        onWishlistToggle = { prod -> viewModel.toggleWishlist(prod.id) },
                        onAddToCart = { prod -> viewModel.addToCart(prod) },
                        isWishlisted = { id -> viewModel.isWishlisted(id) }
                    )
                }
                NavigationTab.CATEGORIES -> {
                    CategoriesScreen(
                        onCategoryClick = { category ->
                            viewModel.setCategory(category)
                            viewModel.setTab(NavigationTab.HOME)
                        }
                    )
                }
                NavigationTab.WISHLIST -> {
                    WishlistScreen(
                        wishlistedProducts = wishlistedProducts,
                        onMoveToCart = { product ->
                            viewModel.addToCart(product)
                            viewModel.toggleWishlist(product.id)
                        },
                        onRemoveFromWishlist = { product ->
                            viewModel.toggleWishlist(product.id)
                        },
                        onProductClick = { prod -> viewModel.selectProduct(prod) },
                        onExploreShop = { viewModel.setTab(NavigationTab.HOME) }
                    )
                }
                NavigationTab.CART -> {
                    CartScreen(
                        cartItems = cartItems,
                        totalPrice = viewModel.totalCartPrice,
                        originalPrice = viewModel.totalOriginalPrice,
                        totalSavings = viewModel.totalSavings,
                        onQuantityChange = { id, delta -> viewModel.updateCartQuantity(id, delta) },
                        onRemoveItem = { id -> viewModel.removeFromCart(id) },
                        onPlaceOrder = { viewModel.placeOrder() },
                        onProductClick = { prod -> viewModel.selectProduct(prod) },
                        onExploreShop = { viewModel.setTab(NavigationTab.HOME) }
                    )
                }
                NavigationTab.ACCOUNT -> {
                    AccountScreen(orders = orders)
                }
            }
        }
    }

    // Product Detail Bottom Sheet Modal
    selectedProduct?.let { product ->
        ProductDetailSheet(
            product = product,
            isWishlisted = viewModel.isWishlisted(product.id),
            onDismiss = { viewModel.selectProduct(null) },
            onWishlistToggle = { viewModel.toggleWishlist(product.id) },
            onAddToCart = {
                viewModel.addToCart(product)
                viewModel.selectProduct(null)
            },
            onBuyNow = {
                viewModel.addToCart(product)
                viewModel.selectProduct(null)
                viewModel.setTab(NavigationTab.CART)
            }
        )
    }

    // Order Success Dialog
    lastPlacedOrder?.let { order ->
        OrderSuccessDialog(
            order = order,
            onDismiss = { viewModel.dismissOrderSuccess() },
            onViewOrders = {
                viewModel.dismissOrderSuccess()
                viewModel.setTab(NavigationTab.ACCOUNT)
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Flipkart $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme { Greeting("Android") }
}
