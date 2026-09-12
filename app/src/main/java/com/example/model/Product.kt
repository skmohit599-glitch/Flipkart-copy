package com.example.model

enum class ProductCategory(val displayName: String, val iconName: String) {
    ALL("All", "Category"),
    MOBILES("Mobiles", "PhoneAndroid"),
    ELECTRONICS("Electronics", "Laptop"),
    FASHION("Fashion", "Checkroom"),
    APPLIANCES("Appliances", "Kitchen"),
    GROCERY("Grocery", "LocalGroceryStore"),
    BEAUTY("Beauty", "Face")
}

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: ProductCategory,
    val price: Int,
    val originalPrice: Int,
    val rating: Float,
    val ratingCount: Int,
    val isAssured: Boolean = true,
    val freeDelivery: Boolean = true,
    val imageUrl: String,
    val description: String,
    val highlights: List<String>,
    val specs: List<Pair<String, String>>,
    val bankOffers: List<String>,
    val tag: String? = null
) {
    val discountPercent: Int
        get() = if (originalPrice > price) {
            (((originalPrice - price).toDouble() / originalPrice) * 100).toInt()
        } else 0
}

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)

data class Order(
    val orderId: String,
    val items: List<CartItem>,
    val totalAmount: Int,
    val orderDate: String,
    val deliveryEstimate: String,
    val deliveryAddress: String,
    val status: String = "Ordered"
)

enum class SortOption(val title: String) {
    POPULARITY("Popularity"),
    PRICE_LOW_HIGH("Price -- Low to High"),
    PRICE_HIGH_LOW("Price -- High to Low"),
    RATING("Customer Rating")
}

enum class NavigationTab(val label: String) {
    HOME("Home"),
    CATEGORIES("Categories"),
    WISHLIST("Wishlist"),
    CART("Cart"),
    ACCOUNT("Account")
}
