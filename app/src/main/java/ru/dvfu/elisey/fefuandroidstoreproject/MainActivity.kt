package ru.dvfu.elisey.fefuandroidstoreproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.graphics.toColorInt

private const val BLACK = "#1A1A1A"
private const val GREY = "#C0C0C0"
private const val ORANGE = "#FE8C00"
private const val DARK_ORANGE = "#67493D"
private const val SOFT_ORANGE = "#F6EFEB"
private const val LIGHT_GREEN = "#84B92D"
private const val DARK_GREEN = "#65830A"
private const val RED = "#F83600"
private const val WHITE = "#F6F6F6"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = RelativeLayout(this)
        root.setBackgroundColor(Color.WHITE)

        val topTabsId = View.generateViewId()

        val topTabsContainer = LinearLayout(this)
        topTabsContainer.id = topTabsId
        topTabsContainer.orientation = LinearLayout.VERTICAL
        topTabsContainer.setBackgroundColor(Color.WHITE)
        topTabsContainer.elevation = dp(6).toFloat()
        topTabsContainer.setPadding(dp(16), dp(12), dp(16), dp(12))

        val topTabsLp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            dp(58)
        )
        topTabsLp.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        root.addView(topTabsContainer, topTabsLp)

        val hsv = HorizontalScrollView(this)
        hsv.isHorizontalScrollBarEnabled = false
        val hsvLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(34)
        )
        topTabsContainer.addView(hsv, hsvLp)

        val tabsRow = LinearLayout(this)
        tabsRow.orientation = LinearLayout.HORIZONTAL
        hsv.addView(
            tabsRow,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                dp(34)
            )
        )

        tabsRow.addView(makeTab("Новинки", true))
        tabsRow.addView(makeTab("Джинсы", false))
        tabsRow.addView(makeTab("Футболки", false))
        tabsRow.addView(makeTab("Пиджаки", false))
        tabsRow.addView(makeTab("Обувь", false, selectedColor = DARK_ORANGE.toColorInt()))

        val bottomNavId = View.generateViewId()

        val bottomNav = LinearLayout(this)
        bottomNav.id = bottomNavId
        bottomNav.orientation = LinearLayout.HORIZONTAL
        bottomNav.gravity = Gravity.CENTER
        bottomNav.setBackgroundColor(Color.WHITE)
        bottomNav.elevation = dp(8).toFloat()

        val bottomNavLp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            dp(98)
        )
        bottomNavLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        root.addView(bottomNav, bottomNavLp)

        bottomNav.addView(makeBottomItem("Меню", true))
        bottomNav.addView(makeBottomItem("Корзина", false))

        val scroll = ScrollView(this)
        scroll.overScrollMode = View.OVER_SCROLL_NEVER

        val scrollLp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        scrollLp.addRule(RelativeLayout.BELOW, topTabsId)
        scrollLp.addRule(RelativeLayout.ABOVE, bottomNavId)
        root.addView(scroll, scrollLp)

        val list = LinearLayout(this)
        list.orientation = LinearLayout.VERTICAL
        list.setPadding(dp(16), dp(16), dp(16), dp(16))
        scroll.addView(
            list,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val card1 = makeProductCard(
            imageRes = R.drawable.img_blazer,
            badgeText = "NEW",
            badgeType = "new",
            title = "Блейзер прямого кроя",
            desc = "Двубортный блейзер на основе лиоцелла и вискозы.",
            priceText = "от 445 ₽",
            priceType = "orange"
        )
        list.addView(card1)
        setBottomMargin(card1, dp(32))

        card1.setOnClickListener {
            openProduct(
                "Блейзер прямого кроя",
                "Двубортный блейзер на основе лиоцелла и вискозы.",
                "2 970 ₽",
                R.drawable.img_blazer,
                "NEW"
            )
        }

        val card2 = makeProductCard(
            imageRes = R.drawable.img_pants,
            badgeText = "",
            badgeType = "",
            title = "Брюки из лиоцелла",
            desc = "Брюки прямого кроя из ткани.",
            priceText = "от 14 999 ₽",
            priceType = ""
        )
        list.addView(card2)
        setBottomMargin(card2, dp(32))

        card2.setOnClickListener {
            openProduct(
                "Брюки из лиоцелла",
                "Брюки прямого кроя из ткани.",
                "16 299 ₽",
                R.drawable.img_pants,
                ""
            )
        }

        val card3 = makeProductCard(
            imageRes = R.drawable.img_cardigan,
            badgeText = "ХИТ",
            badgeType = "hit",
            title = "Кардиган из хлопка",
            desc = "Короткие рукава. Застежка на пуговицы.",
            priceText = "от 445 ₽",
            priceType = "orange"
        )
        list.addView(card3)
        setBottomMargin(card3, dp(32))

        card3.setOnClickListener {
            openProduct(
                "Кардиган из хлопка",
                "Короткие рукава. Застежка на пуговицы.",
                "500 ₽",
                R.drawable.img_cardigan,
                "ХИТ"
            )
        }

        val card4 = makeProductCard(
            imageRes = R.drawable.img_jeans,
            badgeText = "",
            badgeType = "",
            title = "Джинсы straight fit",
            desc = "Пять карманов.",
            priceText = "от 445 ₽",
            priceType = "orange"
        )
        list.addView(card4)
        setBottomMargin(card4, dp(32))

        card4.setOnClickListener {
            openProduct(
                "Джинсы straight fit",
                "Пять карманов.",
                "4 450 ₽",
                R.drawable.img_jeans,
                ""
            )
        }

        setContentView(root)
    }

    private fun openProduct(title: String, desc: String, price: String, imageRes: Int, badge: String) {
        val i = Intent(this, ProductActivity::class.java)
        i.putExtra("title", title)
        i.putExtra("desc", desc)
        i.putExtra("price", price)
        i.putExtra("imageRes", imageRes)
        i.putExtra("badge", badge)
        startActivity(i)
    }

    private fun makeTab(text: String, selected: Boolean, selectedColor: Int = BLACK.toColorInt()): TextView {
        val t = TextView(this)
        t.text = text
        t.gravity = Gravity.CENTER
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        val lp = LinearLayout.LayoutParams(dp(81), dp(34))
        lp.rightMargin = dp(8)
        t.layoutParams = lp

        if (selected) {
            t.setTextColor(Color.WHITE)
            t.background = roundBg(selectedColor, dp(30))
        } else {
            t.setTextColor(BLACK.toColorInt())
            t.background = roundBg(WHITE.toColorInt(), dp(30))
        }
        return t
    }

    private fun makeBottomItem(text: String, active: Boolean): LinearLayout {
        val item = LinearLayout(this)
        item.orientation = LinearLayout.VERTICAL
        item.gravity = Gravity.CENTER

        val lp = LinearLayout.LayoutParams(dp(88), dp(44))
        item.layoutParams = lp

        val icon = TextView(this)
        icon.text = if (text == "Меню") "≡" else "□"
        icon.gravity = Gravity.CENTER
        icon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        icon.setTextColor(if (active) BLACK.toColorInt() else GREY.toColorInt())
        item.addView(icon, LinearLayout.LayoutParams(dp(24), dp(24)))

        val label = TextView(this)
        label.text = text
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        label.setTextColor(if (active) BLACK.toColorInt() else GREY.toColorInt())

        val labelLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            dp(16)
        )
        labelLp.topMargin = dp(4)
        item.addView(label, labelLp)

        return item
    }

    private fun makeProductCard(
        imageRes: Int,
        badgeText: String,
        badgeType: String,
        title: String,
        desc: String,
        priceText: String,
        priceType: String
    ): LinearLayout {

        val card = LinearLayout(this)
        card.orientation = LinearLayout.HORIZONTAL
        card.setBackgroundColor(Color.WHITE)

        val cardLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(146)
        )
        card.layoutParams = cardLp

        val left = RelativeLayout(this)
        val leftLp = LinearLayout.LayoutParams(dp(136), dp(136))
        left.layoutParams = leftLp

        val img = ImageView(this)
        img.setImageResource(imageRes)
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        img.background = roundBg(Color.WHITE, dp(10))
        img.clipToOutline = true
        left.addView(img, RelativeLayout.LayoutParams(dp(136), dp(136)))

        if (badgeText.isNotEmpty()) {
            val badge = TextView(this)
            badge.text = badgeText
            badge.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            badge.setTypeface(null, Typeface.BOLD)
            badge.setTextColor(Color.WHITE)
            badge.gravity = Gravity.CENTER
            badge.setPadding(dp(6), 0, dp(6), 0)

            val badgeLp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                dp(24)
            )
            badgeLp.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            badgeLp.addRule(RelativeLayout.ALIGN_PARENT_START)

            badge.background = if (badgeType == "hit") hitBadgeBg() else newBadgeBg()
            left.addView(badge, badgeLp)
        }

        card.addView(left)

        val right = LinearLayout(this)
        right.orientation = LinearLayout.VERTICAL
        right.setPadding(dp(12), 0, 0, 0)

        val rightLp = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        rightLp.weight = 1f
        right.layoutParams = rightLp

        val textBlock = LinearLayout(this)
        textBlock.orientation = LinearLayout.VERTICAL

        val textBlockLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(90)
        )
        textBlock.layoutParams = textBlockLp

        val tvTitle = TextView(this)
        tvTitle.text = title
        tvTitle.setTextColor(BLACK.toColorInt())
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        tvTitle.setTypeface(null, Typeface.BOLD)
        textBlock.addView(tvTitle, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(22)
        ))

        val tvDesc = TextView(this)
        tvDesc.text = desc
        tvDesc.setTextColor(GREY.toColorInt())
        tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        textBlock.addView(tvDesc, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(60)
        ))

        right.addView(textBlock)

        val price = TextView(this)
        price.text = priceText
        price.gravity = Gravity.CENTER
        price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        price.setTypeface(null, Typeface.BOLD)

        val priceLp = LinearLayout.LayoutParams(dp(96), dp(40))
        right.addView(price, priceLp)

        if (priceType == "brown") {
            price.background = roundBg(SOFT_ORANGE.toColorInt(), dp(4))
            price.setTextColor(DARK_ORANGE.toColorInt())
        } else {
            price.background = roundBg(SOFT_ORANGE.toColorInt(), dp(20))
            price.setTextColor(ORANGE.toColorInt())
        }


        card.addView(right)

        return card
    }

    private fun setBottomMargin(v: View, m: Int) {
        val lp = v.layoutParams as LinearLayout.LayoutParams
        lp.bottomMargin = m
        v.layoutParams = lp
    }

    private fun roundBg(color: Int, radius: Int): GradientDrawable {
        val d = GradientDrawable()
        d.setColor(color)
        d.cornerRadius = radius.toFloat()
        return d
    }

    private fun newBadgeBg(): GradientDrawable {
        val d = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            intArrayOf(LIGHT_GREEN.toColorInt(), DARK_GREEN.toColorInt())
        )
        d.cornerRadius = dp(34).toFloat()
        return d
    }

    private fun hitBadgeBg(): GradientDrawable {
        val d = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            intArrayOf(ORANGE.toColorInt(), RED.toColorInt())
        )
        d.cornerRadius = dp(34).toFloat()
        return d
    }

    private fun dp(x: Int): Int {
        return (x * resources.displayMetrics.density).toInt()
    }
}