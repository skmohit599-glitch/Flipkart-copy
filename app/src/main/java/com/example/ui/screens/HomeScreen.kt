package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.SortOption
import com.example.ui.components.ProductGridCard
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartOrange
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary
import com.example.ui.theme.FlipkartYellow

fun getCategoryIcon(cat: ProductCategory): ImageVector {
    return when (cat) {
        ProductCategory.ALL -> Icons.Filled.Category
        ProductCategory.MOBILES -> Icons.Filled.PhoneAndroid
        ProductCategory.ELECTRONICS -> Icons.Filled.Laptop
        ProductCategory.FASHION -> Icons.Filled.Checkroom
        ProductCategory.APPLIANCES -> Icons.Filled.Kitchen
        ProductCategory.GROCERY -> Icons.Filled.LocalGroceryStore
        ProductCategory.BEAUTY -> Icons.Filled.Face
    }
}

@Composable
fun HomeScreen(
    products: List<Product>,
    selectedCategory: ProductCategory,
    selectedSort: SortOption,
    searchQuery: String,
    onCategorySelect: (ProductCategory) -> Unit,
    onSortSelect: (SortOption) -> Unit,
    onProductClick: (Product) -> Unit,
    onWishlistToggle: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    isWishlisted: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var showSortMenu by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F3F6)),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // 1. Horizontal Category Strip
        item {
            Surface(
                color = Color.White,
                shadowElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 8.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    ProductCategory.entries.forEach { category ->
                        val isSelected = category == selectedCategory
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { onCategorySelect(category) }
                                .padding(horizontal = 4.dp)
                                .testTag("category_chip_${category.name}")
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) FlipkartBlue else Color(0xFFF1F3F6)
                                    )
                                    .border(
                                        width = if (isSelected) 2.dp else 0.dp,
                                        color = if (isSelected) FlipkartYellow else Color.Transparent,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = getCategoryIcon(category),
                                    contentDescription = category.displayName,
                                    tint = if (isSelected) Color.White else FlipkartBlue,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = category.displayName,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) FlipkartBlue else FlipkartTextPrimary
                            )
                        }
                    }
                }
            }
        }

        // 2. Big Festive Hero Banner (Only shown when not deeply searching)
        if (searchQuery.isBlank()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .testTag("hero_sale_banner"),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banner_mega_sale_1789205144849),
                            contentDescription = "Big Billion Sale Banner",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Countdown overlay
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Timer,
                                        contentDescription = null,
                                        tint = FlipkartYellow,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Deals of the Day: 04h 19m left",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                                Text(
                                    text = "Up to 75% OFF",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = FlipkartYellow
                                )
                            }
                        }
                    }
                }
            }

            // 3. Quick Deals of the Day Row
            item {
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Bolt,
                                    contentDescription = null,
                                    tint = FlipkartOrange,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Deals of the Day",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartTextPrimary
                                )
                            }
                            Text(
                                text = "VIEW ALL",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartBlue
                            )
                        }

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            items(products.take(5)) { product ->
                                Box(modifier = Modifier.width(170.dp)) {
                                    ProductGridCard(
                                        product = product,
                                        isWishlisted = isWishlisted(product.id),
                                        onProductClick = onProductClick,
                                        onWishlistToggle = onWishlistToggle,
                                        onAddToCart = onAddToCart
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 4. Products Header & Sort Options
        item {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (searchQuery.isNotBlank()) "Search Results (${products.size})" else "${selectedCategory.displayName} Products (${products.size})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = FlipkartTextPrimary
                    )

                    // Sort button with dropdown
                    Box {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { showSortMenu = true }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .testTag("sort_filter_btn"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Sort,
                                contentDescription = "Sort",
                                tint = FlipkartBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = selectedSort.title,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = FlipkartBlue
                            )
                        }

                        DropdownMenu(
                            expanded = showSortMenu,
                            onDismissRequest = { showSortMenu = false }
                        ) {
                            SortOption.entries.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = option.title,
                                            fontWeight = if (option == selectedSort) FontWeight.Bold else FontWeight.Normal,
                                            color = if (option == selectedSort) FlipkartBlue else FlipkartTextPrimary
                                        )
                                    },
                                    onClick = {
                                        onSortSelect(option)
                                        showSortMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        // 5. Product Grid (2 columns)
        if (products.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.SearchOff,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No products found",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = FlipkartTextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Try searching for another item or category",
                        fontSize = 13.sp,
                        color = FlipkartTextSecondary
                    )
                }
            }
        } else {
            val chunked = products.chunked(2)
            items(chunked) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductGridCard(
                            product = pair[0],
                            isWishlisted = isWishlisted(pair[0].id),
                            onProductClick = onProductClick,
                            onWishlistToggle = onWishlistToggle,
                            onAddToCart = onAddToCart
                        )
                    }

                    if (pair.size > 1) {
                        Box(modifier = Modifier.weight(1f)) {
                            ProductGridCard(
                                product = pair[1],
                                isWishlisted = isWishlisted(pair[1].id),
                                onProductClick = onProductClick,
                                onWishlistToggle = onWishlistToggle,
                                onAddToCart = onAddToCart
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
