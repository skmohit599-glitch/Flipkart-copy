package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProductCategory
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary

data class CategoryItem(
    val category: ProductCategory,
    val subtitle: String,
    val offerTag: String
)

val categoryList = listOf(
    CategoryItem(ProductCategory.MOBILES, "iPhones, Samsung, OnePlus & More", "Min. 20% Off"),
    CategoryItem(ProductCategory.ELECTRONICS, "Laptops, Audio, Cameras & Wearables", "Up to 70% Off"),
    CategoryItem(ProductCategory.FASHION, "Footwear, Denim, T-Shirts & Formals", "50-80% Off"),
    CategoryItem(ProductCategory.APPLIANCES, "Air Fryers, Vacuums, Washers & ACs", "Up to 60% Off"),
    CategoryItem(ProductCategory.GROCERY, "Tea, Staples, Snacks & Daily Food", "Starts ₹1"),
    CategoryItem(ProductCategory.BEAUTY, "Skincare, Grooming & Fragrances", "Min. 30% Off")
)

@Composable
fun CategoriesScreen(
    onCategoryClick: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F3F6))
    ) {
        Surface(
            color = Color.White,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "All Categories",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = FlipkartTextPrimary
                )
                Text(
                    text = "Browse by category to find top deals",
                    fontSize = 12.sp,
                    color = FlipkartTextSecondary
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categoryList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onCategoryClick(item.category) }
                        .testTag("category_grid_${item.category.name}"),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5FE)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = getCategoryIcon(item.category),
                                contentDescription = item.category.displayName,
                                tint = FlipkartBlue,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = item.category.displayName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.subtitle,
                            fontSize = 11.sp,
                            color = FlipkartTextSecondary,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            lineHeight = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.offerTag,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartGreen
                            )
                        }
                    }
                }
            }
        }
    }
}
