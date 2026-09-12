package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Product
import com.example.ui.theme.FlipkartAmber
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartLightGreen
import com.example.ui.theme.FlipkartOrange
import com.example.ui.theme.FlipkartTagBg
import com.example.ui.theme.FlipkartTagText
import com.example.ui.theme.FlipkartTextPrimary
import com.example.ui.theme.FlipkartTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailSheet(
    product: Product,
    isWishlisted: Boolean,
    onDismiss: () -> Unit,
    onWishlistToggle: () -> Unit,
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var pincode by remember { mutableStateOf("560001") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = Color(0xFFF1F3F6),
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            Surface(
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("detail_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = FlipkartTextPrimary
                        )
                    }

                    Text(
                        text = product.brand.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = FlipkartTextPrimary
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onWishlistToggle,
                            modifier = Modifier.testTag("detail_wishlist_btn")
                        ) {
                            Icon(
                                imageVector = if (isWishlisted) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Wishlist",
                                tint = if (isWishlisted) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                // Image section
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(product.imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = product.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )

                        if (!product.tag.isNullOrBlank()) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(12.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(FlipkartOrange)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = product.tag,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title, Ratings & Price card
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = product.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = FlipkartTextPrimary,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RatingBadge(rating = product.rating)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${product.ratingCount} Ratings & Reviews",
                                fontSize = 12.sp,
                                color = FlipkartBlue,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            if (product.isAssured) {
                                FlipkartAssuredBadge()
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Special Price Label
                        Text(
                            text = "Special Price",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartGreen
                        )

                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = formatInr(product.price),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartTextPrimary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            if (product.originalPrice > product.price) {
                                Text(
                                    text = formatInr(product.originalPrice),
                                    fontSize = 15.sp,
                                    color = FlipkartTextSecondary,
                                    textDecoration = TextDecoration.LineThrough
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${product.discountPercent}% off",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartGreen
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Bank Offers Card
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Available Offers",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        product.bankOffers.forEach { offer ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = FlipkartGreen,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = offer,
                                    fontSize = 12.sp,
                                    color = FlipkartTextPrimary,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Delivery & Services
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = null,
                                    tint = FlipkartBlue,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Deliver to: $pincode",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartTextPrimary
                                )
                            }
                            Text(
                                text = "Change",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlipkartBlue
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.LocalShipping,
                                contentDescription = null,
                                tint = FlipkartGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "FREE Delivery by Tomorrow, 5 PM",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = FlipkartGreen
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Security,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "7 Days Replacement Policy | Cash on Delivery Available",
                                fontSize = 11.sp,
                                color = FlipkartTextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Highlights Section
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Product Highlights",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        product.highlights.forEach { highlight ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "•",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FlipkartBlue
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = highlight,
                                    fontSize = 12.sp,
                                    color = FlipkartTextPrimary,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Specifications Section
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Specifications",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = FlipkartTextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        product.specs.forEachIndexed { index, (key, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = key,
                                    fontSize = 12.sp,
                                    color = FlipkartTextSecondary,
                                    modifier = Modifier.weight(0.4f)
                                )
                                Text(
                                    text = value,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = FlipkartTextPrimary,
                                    modifier = Modifier.weight(0.6f)
                                )
                            }
                            if (index < product.specs.size - 1) {
                                Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Fixed Flipkart Bottom Bar: Add to Cart (Amber) & Buy Now (Orange)
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onAddToCart,
                        colors = ButtonDefaults.buttonColors(containerColor = FlipkartAmber),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("detail_add_to_cart_btn")
                    ) {
                        Text(
                            text = "ADD TO CART",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF212121)
                        )
                    }

                    Button(
                        onClick = onBuyNow,
                        colors = ButtonDefaults.buttonColors(containerColor = FlipkartOrange),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("detail_buy_now_btn")
                    ) {
                        Text(
                            text = "BUY NOW",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
