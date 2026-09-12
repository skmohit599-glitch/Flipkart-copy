package com.example.data

import com.example.model.Product
import com.example.model.ProductCategory

object ProductCatalog {
    val products = listOf(
        Product(
            id = "mob_001",
            name = "Apple iPhone 15 (Black, 128 GB)",
            brand = "Apple",
            category = ProductCategory.MOBILES,
            price = 64999,
            originalPrice = 79900,
            rating = 4.7f,
            ratingCount = 48512,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=600&q=80",
            description = "Dynamic Island bubbles up alerts and Live Activities. 48MP Main camera with 2x Telephoto. Durable color-infused glass and aluminum design.",
            highlights = listOf(
                "128 GB ROM",
                "15.49 cm (6.1 inch) Super Retina XDR Display",
                "48MP + 12MP Dual Rear Camera | 12MP Front Camera",
                "A16 Bionic Chip, 6 Core Processor",
                "All-day battery life with USB-C connector"
            ),
            specs = listOf(
                "In The Box" to "iPhone, USB-C Charge Cable, Documentation",
                "Model Number" to "MTP03HN/A",
                "Network Type" to "5G, 4G VOLTE, 4G, 3G, 2G",
                "Warranty" to "1 Year Manufacturer Warranty for Device"
            ),
            bankOffers = listOf(
                "Bank Offer: 5% Unlimited Cashback on Flipkart Axis Bank Card",
                "Special Price: Get extra ₹14,901 off (price inclusive of discount)",
                "No Cost EMI available starting from ₹5,417/month"
            ),
            tag = "Bestseller"
        ),
        Product(
            id = "mob_002",
            name = "SAMSUNG Galaxy S24 Ultra 5G (Titanium Gray, 256 GB)",
            brand = "Samsung",
            category = ProductCategory.MOBILES,
            price = 119999,
            originalPrice = 144999,
            rating = 4.8f,
            ratingCount = 12903,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=600&q=80",
            description = "Welcome to the era of mobile AI. With Galaxy AI, step into whole new levels of creativity, productivity and possibility.",
            highlights = listOf(
                "12 GB RAM | 256 GB ROM",
                "17.27 cm (6.8 inch) Dynamic AMOLED 2X Display",
                "200MP + 50MP + 12MP + 10MP Quad Rear | 12MP Front",
                "Snapdragon 8 Gen 3 Processor",
                "5000 mAh Battery with Embedded S-Pen"
            ),
            specs = listOf(
                "Operating System" to "Android 14, One UI 6.1",
                "Processor Core" to "Octa Core Snapdragon 8 Gen 3",
                "Primary Camera Features" to "Nightography, 100x Space Zoom",
                "Warranty" to "1 Year Brand Warranty"
            ),
            bankOffers = listOf(
                "Flat ₹5,000 Instant Discount with HDFC Credit Cards",
                "Extra ₹10,000 off on Exchange of Old Phone"
            ),
            tag = "Flagship Deal"
        ),
        Product(
            id = "mob_003",
            name = "OnePlus Nord CE4 (Celadon Marble, 128 GB)",
            brand = "OnePlus",
            category = ProductCategory.MOBILES,
            price = 24999,
            originalPrice = 27999,
            rating = 4.5f,
            ratingCount = 31200,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=600&q=80",
            description = "Raw power meets eye-catching elegance. Super-fast 100W SUPERVOOC charging, Sony LYT-600 OIS camera sensor.",
            highlights = listOf(
                "8 GB RAM | 128 GB ROM | Expandable Upto 1 TB",
                "17.02 cm (6.7 inch) Full HD+ AMOLED 120Hz Display",
                "50MP (OIS) + 8MP | 16MP Front Camera",
                "5500 mAh Battery with 100W SUPERVOOC Charging",
                "Qualcomm Snapdragon 7 Gen 3"
            ),
            specs = listOf(
                "In The Box" to "Handset, 100W Adapter, Type-C Cable, Case",
                "Audio Features" to "Dual Stereo Speakers with Hi-Res Audio",
                "Warranty" to "1 Year Manufacturer Warranty"
            ),
            bankOffers = listOf(
                "10% Instant Discount on SBI Credit Card EMI Transactions",
                "Free boAt Earphones on bundled purchase"
            ),
            tag = "Trending"
        ),
        Product(
            id = "elec_001",
            name = "Apple 2022 MacBook Air M2 - (8 GB/256 GB SSD/macOS)",
            brand = "Apple",
            category = ProductCategory.ELECTRONICS,
            price = 82990,
            originalPrice = 99900,
            rating = 4.8f,
            ratingCount = 8940,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=600&q=80",
            description = "Redesigned around the next-generation M2 chip, MacBook Air is strikingly thin and brings exceptional speed and power efficiency.",
            highlights = listOf(
                "Apple M2 Chip with 8-Core CPU and 8-Core GPU",
                "8 GB Unified RAM | 256 GB SSD Storage",
                "34.54 cm (13.6 inch) Liquid Retina Display with True Tone",
                "Up to 18 Hours Battery Life",
                "1080p FaceTime HD Camera & MagSafe 3 Charging"
            ),
            specs = listOf(
                "Graphic Processor" to "Apple 8-core GPU",
                "Weight" to "1.24 kg Ultra-light",
                "Operating System" to "macOS Sonoma",
                "Warranty" to "1 Year Limited Hardware Warranty"
            ),
            bankOffers = listOf(
                "₹5,000 Instant Discount on ICICI Bank Cards",
                "No Cost EMI for up to 12 months"
            ),
            tag = "Hot Deal"
        ),
        Product(
            id = "elec_002",
            name = "Sony WH-1000XM5 Wireless Active Noise Cancelling Headphones",
            brand = "Sony",
            category = ProductCategory.ELECTRONICS,
            price = 26990,
            originalPrice = 34990,
            rating = 4.6f,
            ratingCount = 6420,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&q=80",
            description = "Industry-leading noise canceling with two processors and eight microphones. Exceptional sound quality engineered to perfection.",
            highlights = listOf(
                "Industry Leading ANC with 8 Microphones & Auto Optimizer",
                "30 Hours Battery Life (with ANC ON), 3 min quick charge for 3 hrs",
                "Multipoint connection: Pair two Bluetooth devices simultaneously",
                "Ultra-comfortable lightweight design with soft fit leather"
            ),
            specs = listOf(
                "Headphone Type" to "Over-Ear Wireless",
                "Bluetooth Version" to "v5.2 with LDAC support",
                "Warranty" to "1 Year Brand Domestic Warranty"
            ),
            bankOffers = listOf(
                "Flat ₹2,000 Off on Kotak Mahindra Credit Cards",
                "Extra 5% Cashback with Flipkart SuperCoins"
            ),
            tag = "Audio Star"
        ),
        Product(
            id = "elec_003",
            name = "boAt Airdopes 141 Bluetooth TWS Earbuds with 42H Playtime",
            brand = "boAt",
            category = ProductCategory.ELECTRONICS,
            price = 1199,
            originalPrice = 4490,
            rating = 4.2f,
            ratingCount = 184590,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&q=80",
            description = "Equipped with 8mm drivers, ENx tech noise isolation, Beast Mode for low latency gaming, and ASAP charge.",
            highlights = listOf(
                "Up to 42 Hours Total Playback time",
                "ENx Environmental Noise Cancellation for crystal calls",
                "Beast Mode with 80ms Low Latency",
                "ASAP Charge: 5 mins charge = 75 mins playtime",
                "IPX4 Water & Sweat Resistance"
            ),
            specs = listOf(
                "Color" to "Bold Black",
                "Driver Size" to "8 mm Dynamic Drivers",
                "Warranty" to "1 Year Warranty from boAt"
            ),
            bankOffers = listOf(
                "Special Price: Extra ₹3,291 off applied",
                "Pay using UPI and get ₹50 cashback coupon"
            ),
            tag = "Super Saver"
        ),
        Product(
            id = "fash_001",
            name = "Nike Air Jordan 1 Low Retro Men Sneakers",
            brand = "Nike",
            category = ProductCategory.FASHION,
            price = 7495,
            originalPrice = 9995,
            rating = 4.6f,
            ratingCount = 3890,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600&q=80",
            description = "Inspired by the 1985 original, the Air Jordan 1 Low offers a clean, classic look that's familiar yet fresh with encapsulated Air-Sole unit.",
            highlights = listOf(
                "Genuine leather upper for durability and premium look",
                "Encapsulated Air-Sole unit provides lightweight cushioning",
                "Solid rubber outsole enhances traction on a variety of surfaces",
                "Iconic Wings logo on heel and stitched Swoosh design"
            ),
            specs = listOf(
                "Ideal For" to "Men",
                "Occasion" to "Casual / Streetwear",
                "Closure" to "Lace-Ups",
                "Outer Material" to "Genuine Leather"
            ),
            bankOffers = listOf(
                "10% Instant Discount on Axis Bank Credit Cards",
                "Flat ₹500 off for Flipkart Plus Members"
            ),
            tag = "Fashion Pick"
        ),
        Product(
            id = "fash_002",
            name = "Levi's Men 511 Slim Fit Mid Rise Stretch Denim Jeans",
            brand = "Levi's",
            category = ProductCategory.FASHION,
            price = 2199,
            originalPrice = 3999,
            rating = 4.4f,
            ratingCount = 18450,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=600&q=80",
            description = "A modern slim with room to move, the 511 Slim Fit Jeans are a classic since right now. Cut close without being too tight.",
            highlights = listOf(
                "Fabric: 99% Cotton, 1% Elastane stretch denim",
                "Fit: Slim Fit through seat and thigh with slim leg",
                "Rise: Mid Rise with zipper fly",
                "Wash Care: Machine wash cold with like colors"
            ),
            specs = listOf(
                "Style Code" to "04511-2856",
                "Shade" to "Dark Indigo Wash",
                "Return Policy" to "14 Days Free Return & Exchange"
            ),
            bankOffers = listOf(
                "Buy 2 Get Extra 10% Off",
                "Flipkart Pay Later instant credit up to ₹1,00,000"
            ),
            tag = "Top Rated"
        ),
        Product(
            id = "app_001",
            name = "Mi Smart Air Fryer 3.5L with OLED Display & Wi-Fi",
            brand = "Xiaomi",
            category = ProductCategory.APPLIANCES,
            price = 4999,
            originalPrice = 9999,
            rating = 4.5f,
            ratingCount = 8720,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1556911220-e15b29be8c8f?w=600&q=80",
            description = "Healthy cooking with less oil and low fat. 360-degree hot air circulation with smart voice scheduling via Google Assistant.",
            highlights = listOf(
                "3.5 Liter Capacity with dual-layer non-stick coating",
                "40°C to 200°C wide-range temperature control",
                "OLED touch screen display with custom recipes",
                "Smart Cloud Recipes via Xiaomi Home App",
                "1500W heating power for quick crispiness"
            ),
            specs = listOf(
                "Power Requirement" to "220 - 240V, 50Hz",
                "Body Material" to "Heat Resistant ABS Plastic",
                "Warranty" to "1 Year Brand Warranty"
            ),
            bankOffers = listOf(
                "Special Price: Flat 50% Off",
                "Extra ₹250 off on UPI transactions"
            ),
            tag = "Best Seller"
        ),
        Product(
            id = "app_002",
            name = "Dyson V8 Absolute Cordless Vacuum Cleaner",
            brand = "Dyson",
            category = ProductCategory.APPLIANCES,
            price = 29900,
            originalPrice = 43900,
            rating = 4.7f,
            ratingCount = 3180,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1558317374-067fb5f30001?w=600&q=80",
            description = "Engineered for homes with pets. Up to 40 minutes of fade-free suction with whole-machine advanced filtration.",
            highlights = listOf(
                "Powered by Dyson digital motor V8 spinning at 110,000 rpm",
                "De-tangling Motorbar cleaner head deep cleans carpets and hard floors",
                "Advanced whole-machine filtration captures 99.99% of microscopic particles",
                "Transforms to handheld vacuum with single click"
            ),
            specs = listOf(
                "Run Time" to "Up to 40 mins",
                "Bin Volume" to "0.54 L with hygienic point-and-shoot emptying",
                "Warranty" to "2 Years On-site Brand Warranty"
            ),
            bankOffers = listOf(
                "Flat ₹3,000 off on all major Bank Credit Cards",
                "Free Home Installation and Demo within 48 Hours"
            ),
            tag = "Premium"
        ),
        Product(
            id = "groc_001",
            name = "Tata Tea Gold Premium Assam Tea 1 kg Pouch",
            brand = "Tata",
            category = ProductCategory.GROCERY,
            price = 549,
            originalPrice = 699,
            rating = 4.6f,
            ratingCount = 42100,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&q=80",
            description = "An exquisite blend of gentle rolled aromatic long leaves and CTC tea from Assam, promising an irresistible aroma and taste.",
            highlights = listOf(
                "Net Quantity: 1 kg",
                "Speciality: 15% Long Aromatic Assam Leaves Blend",
                "Rich Golden Liquor with strong briskness",
                "100% Pure Indian Tea"
            ),
            specs = listOf(
                "Container Type" to "Pouch",
                "Shelf Life" to "12 Months from Packaging",
                "FSSAI Certified" to "Yes"
            ),
            bankOffers = listOf(
                "Buy 2 get 10% additional discount",
                "Grocery Super Saver deal: ₹1 deal on orders above ₹1,200"
            ),
            tag = "Daily Essential"
        ),
        Product(
            id = "fash_003",
            name = "Fastrack Limitless FS1 Pro Smartwatch (1.96\" AMOLED)",
            brand = "Fastrack",
            category = ProductCategory.FASHION,
            price = 1999,
            originalPrice = 7995,
            rating = 4.3f,
            ratingCount = 54100,
            isAssured = true,
            freeDelivery = true,
            imageUrl = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&q=80",
            description = "1.96\" Super AMOLED Arched Display with 410x502 pixel resolution. SingleSync BT Calling with advanced chipset.",
            highlights = listOf(
                "1.96 inch Super AMOLED Curved Display with Always-On feature",
                "SingleSync Bluetooth Calling with mic & speaker",
                "110+ Sports Modes & Auto Multisport Recognition",
                "Comprehensive Health Suite: 24/7 Heart Rate, SpO2, Stress Monitor",
                "Up to 7 Days Battery Life"
            ),
            specs = listOf(
                "Strap Material" to "Silicone Quick Release",
                "Water Resistance" to "IP68 Dust & Water Proof",
                "Warranty" to "1 Year Brand Warranty"
            ),
            bankOffers = listOf(
                "Special Price: 75% Off",
                "Extra 5% off on Axis Bank Cards"
            ),
            tag = "Mega Deal"
        )
    )

    fun getByCategory(category: ProductCategory): List<Product> {
        return if (category == ProductCategory.ALL) {
            products
        } else {
            products.filter { it.category == category }
        }
    }

    fun searchProducts(query: String, category: ProductCategory = ProductCategory.ALL): List<Product> {
        val base = getByCategory(category)
        if (query.isBlank()) return base
        val q = query.trim().lowercase()
        return base.filter {
            it.name.lowercase().contains(q) ||
                    it.brand.lowercase().contains(q) ||
                    it.category.displayName.lowercase().contains(q) ||
                    it.description.lowercase().contains(q)
        }
    }
}
