package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.FlipkartBlue
import com.example.ui.theme.FlipkartGreen
import com.example.ui.theme.FlipkartYellow
import java.text.NumberFormat
import java.util.Locale

fun formatInr(amount: Int): String {
    return try {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        format.maximumFractionDigits = 0
        format.format(amount).replace("INR", "₹").trim()
    } catch (e: Exception) {
        "₹$amount"
    }
}

@Composable
fun RatingBadge(rating: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(FlipkartGreen)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = String.format(Locale.US, "%.1f", rating),
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(10.dp)
            )
        }
    }
}

@Composable
fun FlipkartAssuredBadge(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(Color(0xFFF1F5FE))
            .padding(horizontal = 4.dp, vertical = 1.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Plus",
                color = FlipkartBlue,
                fontSize = 10.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.width(1.dp))
            Icon(
                imageVector = Icons.Filled.Verified,
                contentDescription = "Assured",
                tint = FlipkartYellow,
                modifier = Modifier.size(11.dp)
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = "Assured",
                color = FlipkartBlue,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
