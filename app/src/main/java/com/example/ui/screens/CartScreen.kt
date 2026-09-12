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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.model.CartItem
import com.example.model.NavigationTab
import com.example.model.Product
import com.example.ui.components.FlipkartAssuredBadge
import com.example.ui.components.formatInr
import com.example.ui.theme.FlipkartAmber
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartLightGreen
import com.example.ui.theme.FlipkartOrange
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary

@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    totalPrice: Int,
    originalPrice: Int,
    totalSavings: Int,
    onQuantityChange: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onPlaceOrder: () -> Unit,
    onProductClick: (Product) -> Unit,
    onExploreShop: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (cartItems.isEmpty()) {
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
                    imageVector = Icons.Filled.RemoveShoppingCart,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(90.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your Cart is empty!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = FlipkartTextPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Explore trending deals and fill your bag",
                    fontSize = 13.sp,
                    color = FlipkartTextSecondary
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onExploreShop,
                    colors = ButtonDefaults.buttonColors(containerColor = FlipkartBlue),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .height(44.dp)
                        .testTag("cart_shop_now_btn")
                ) {
                    Text(
                        text = "Shop Now",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF1F3F6))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 140.dp)
            ) {
                // Delivery Address strip
                item {
                    Surface(
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = null,
                                    tint = FlipkartBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Deliver to: Royal Palms, 560001",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = FlipkartTextPrimary
                                    )
                                    Text(
                                        text = "Flat 402, MG Road, Bengaluru",
                                        fontSize = 11.sp,
                                        color = FlipkartTextSecondary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            Text(
                                text = "Change",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartBlue,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Cart Items
                items(cartItems, key = { it.product.id }) { item ->
                    CartItemCard(
                        item = item,
                        onQuantityChange = { delta -> onQuantityChange(item.product.id, delta) },
                        onRemove = { onRemoveItem(item.product.id) },
                        onProductClick = { onProductClick(item.product) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Price Details Summary Card
                item {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "PRICE DETAILS",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartTextSecondary
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                            Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                            Spacer(modifier = Modifier.height(12.dp))

                            // Price row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Price (${cartItems.sumOf { it.quantity }} items)",
                                    fontSize = 13.sp,
                                    color = FlipkartTextPrimary
                                )
                                Text(
                                    text = formatInr(originalPrice),
                                    fontSize = 13.sp,
                                    color = FlipkartTextPrimary
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Discount row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Discount",
                                    fontSize = 13.sp,
                                    color = FlipkartTextPrimary
                                )
                                Text(
                                    text = "-${formatInr(totalSavings)}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = FlipkartGreen
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Delivery charges
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Delivery Charges",
                                    fontSize = 13.sp,
                                    color = FlipkartTextPrimary
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "₹70",
                                        fontSize = 12.sp,
                                        color = FlipkartTextSecondary,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "FREE",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = FlipkartGreen
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Secured Packaging Fee
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Secured Packaging Fee",
                                    fontSize = 13.sp,
                                    color = FlipkartTextPrimary
                                )
                                Text(
                                    text = "FREE",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartGreen
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                            Spacer(modifier = Modifier.height(12.dp))

                            // Total Amount
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Total Amount",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartTextPrimary
                                )
                                Text(
                                    text = formatInr(totalPrice),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartTextPrimary
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Savings banner
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFFE8F5E9))
                                    .padding(vertical = 8.dp, horizontal = 10.dp)
                            ) {
                                Text(
                                    text = "You will save ${formatInr(totalSavings)} on this order",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartGreen
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Safe & Secure payments guarantee
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Shield,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Safe and Secure Payments. 100% Authentic Products.",
                            fontSize = 11.sp,
                            color = FlipkartTextSecondary
                        )
                    }
                }
            }

            // Fixed Sticky Checkout Bottom Bar
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = formatInr(totalPrice),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                        Text(
                            text = "View price details",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = FlipkartBlue
                        )
                    }

                    Button(
                        onClick = onPlaceOrder,
                        colors = ButtonDefaults.buttonColors(containerColor = FlipkartAmber),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .height(44.dp)
                            .width(170.dp)
                            .testTag("place_order_btn")
                    ) {
                        Text(
                            text = "Place Order",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF212121)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    onProductClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onProductClick() }
            ) {
                // Product Image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFF9F9F9))
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.product.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = item.product.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Details
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.product.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = FlipkartTextPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Seller: RetailNet",
                            fontSize = 11.sp,
                            color = FlipkartTextSecondary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        if (item.product.isAssured) {
                            FlipkartAssuredBadge()
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Price
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = formatInr(item.product.price),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        if (item.product.originalPrice > item.product.price) {
                            Text(
                                text = formatInr(item.product.originalPrice),
                                fontSize = 12.sp,
                                color = FlipkartTextSecondary,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${item.product.discountPercent}% off",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Delivery by Tomorrow, 5 PM | Free",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FlipkartGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Action Row: Quantity and Remove
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Quantity changer (- QTY +)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .clickable { onQuantityChange(-1) }
                            .testTag("cart_decrease_${item.product.id}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = "Decrease",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                    }

                    Text(
                        text = "${item.quantity}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FlipkartTextPrimary,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .clickable { onQuantityChange(1) }
                            .testTag("cart_increase_${item.product.id}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Increase",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                    }
                }

                // Remove Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onRemove() }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("cart_remove_${item.product.id}")
                ) {
                    Icon(
                        imageVector = Icons.Filled.DeleteOutline,
                        contentDescription = "Remove Item",
                        tint = FlipkartTextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Remove",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FlipkartTextSecondary
                    )
                }
            }
        }
    }
}
