package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Product
import com.example.ui.components.RatingBadge
import com.example.ui.components.formatInr
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary

@Composable
fun WishlistScreen(
    wishlistedProducts: List<Product>,
    onMoveToCart: (Product) -> Unit,
    onRemoveFromWishlist: (Product) -> Unit,
    onProductClick: (Product) -> Unit,
    onExploreShop: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (wishlistedProducts.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF1F3F6)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Empty Wishlist",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = FlipkartTextPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Tap heart on products you love to save them here",
                    fontSize = 13.sp,
                    color = FlipkartTextSecondary
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onExploreShop,
                    colors = ButtonDefaults.buttonColors(containerColor = FlipkartBlue),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.testTag("wishlist_shop_btn")
                ) {
                    Text(
                        text = "Continue Shopping",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF1F3F6)),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Surface(
                    color = Color.White,
                    shadowElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Wishlist (${wishlistedProducts.size})",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

            items(wishlistedProducts, key = { it.id }) { product ->
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onProductClick(product) }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFF9F9F9))
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(product.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = product.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = product.name,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = FlipkartTextPrimary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RatingBadge(rating = product.rating)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "(${product.ratingCount})",
                                        fontSize = 11.sp,
                                        color = FlipkartTextSecondary
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = formatInr(product.price),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = FlipkartTextPrimary
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    if (product.originalPrice > product.price) {
                                        Text(
                                            text = formatInr(product.originalPrice),
                                            fontSize = 12.sp,
                                            color = FlipkartTextSecondary,
                                            textDecoration = TextDecoration.LineThrough
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "${product.discountPercent}% off",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = FlipkartGreen
                                        )
                                    }
                                }
                            }

                            IconButton(
                                onClick = { onRemoveFromWishlist(product) },
                                modifier = Modifier.testTag("wishlist_delete_${product.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.DeleteOutline,
                                    contentDescription = "Delete",
                                    tint = Color.Gray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { onMoveToCart(product) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF1F5FE),
                                contentColor = FlipkartBlue
                            ),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38.dp)
                                .border(1.dp, FlipkartBlue.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                                .testTag("wishlist_move_to_cart_${product.id}")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "MOVE TO CART",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
