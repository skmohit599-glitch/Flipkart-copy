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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Order
import com.example.ui.components.formatInr
import com.example.ui.theme.FlipkartAmber
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartLightGreen
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary
import com.example.ui.theme.FlipkartYellow

@Composable
fun AccountScreen(
    orders: List<Order>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F3F6)),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // User Profile Header with Plus Badge
        item {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(FlipkartBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "FS",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Flipkart Shopper",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartTextPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "+91 98765 43210 • shopper@flipkart.com",
                                fontSize = 12.sp,
                                color = FlipkartTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Plus Zone Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF0F3A8A), Color(0xFF1D54BD))
                                )
                            )
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = FlipkartYellow,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Flipkart Plus Member",
                                        color = FlipkartYellow,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        text = "Free & Fast Delivery on all Assured Orders",
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Text(
                                text = "ACTIVE",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // SuperCoins & Pay Later Cards
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // SuperCoins
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.MonetizationOn,
                                contentDescription = null,
                                tint = FlipkartYellow,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "SuperCoins",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartTextSecondary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "240 Coins",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                    }
                }

                // Flipkart Pay Later
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.CreditCard,
                                contentDescription = null,
                                tint = FlipkartBlue,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Pay Later",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartTextSecondary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "₹25,000 Limit",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // My Orders Header
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
                    Text(
                        text = "My Orders (${orders.size})",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = FlipkartTextPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
        }

        // Orders List
        items(orders) { order ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Order ID: ${order.orderId}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextSecondary
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (order.status == "Delivered") Color(0xFFE8F5E9) else Color(0xFFE3F2FD))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = order.status,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (order.status == "Delivered") FlipkartGreen else FlipkartBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    order.items.forEach { cartItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFFF9F9F9))
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(cartItem.product.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = cartItem.product.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(2.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = cartItem.product.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = FlipkartTextPrimary,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Qty: ${cartItem.quantity} • ${formatInr(cartItem.product.price)}",
                                    fontSize = 11.sp,
                                    color = FlipkartTextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = order.deliveryEstimate,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = FlipkartGreen
                        )
                        Text(
                            text = "Total: ${formatInr(order.totalAmount)}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )
                    }
                }
            }
        }

        // Account Quick Actions
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    AccountActionRow(
                        icon = Icons.Filled.LocationOn,
                        title = "Saved Addresses",
                        subtitle = "1 delivery address saved"
                    )
                    Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                    AccountActionRow(
                        icon = Icons.Filled.CreditCard,
                        title = "Saved Cards & Wallets",
                        subtitle = "Manage UPI, cards & bank accounts"
                    )
                    Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                    AccountActionRow(
                        icon = Icons.AutoMirrored.Filled.HelpOutline,
                        title = "24x7 Help Center",
                        subtitle = "Help with your orders and queries"
                    )
                    Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                    AccountActionRow(
                        icon = Icons.Filled.Shield,
                        title = "Privacy & Policies",
                        subtitle = "Flipkart Terms of Use & Policies"
                    )
                }
            }
        }
    }
}

@Composable
fun AccountActionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Action */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = FlipkartBlue,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = FlipkartTextPrimary
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = FlipkartTextSecondary
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(14.dp)
        )
    }
}
