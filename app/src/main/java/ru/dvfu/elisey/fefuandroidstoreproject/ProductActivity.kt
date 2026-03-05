package ru.dvfu.elisey.fefuandroidstoreproject

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.graphics.toColorInt

private const val BLACK = "#1A1A1A"
private const val ORANGE = "#FE8C00"
private const val DARK_ORANGE = "#67493D"
private const val LIGHT_GREEN = "#84B92D"
private const val DARK_GREEN = "#65830A"
private const val RED = "#F83600"
private const val WHITE = "#F6F6F6"

class ProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title") ?: "Джинсы straight fit"
        val desc = intent.getStringExtra("desc") ?: "Пять карманов."
        val price = intent.getStringExtra("price") ?: "500 ₽"
        val badge = intent.getStringExtra("badge") ?: ""
        val imageRes = intent.getIntExtra("imageRes", R.drawable.img_jeans)

        val root = RelativeLayout(this)
        root.setBackgroundColor(Color.WHITE)
        root.setPadding(dp(16), 0, dp(16), 0)

        val buyButtonId = View.generateViewId()

        val buyButton = TextView(this)
        buyButton.id = buyButtonId
        buyButton.text = "В корзину • $price"
        buyButton.gravity = Gravity.CENTER
        buyButton.setTextColor(Color.WHITE)
        buyButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        buyButton.setTypeface(null, Typeface.BOLD)
        buyButton.background = roundBg("#A47A6A".toColorInt(), dp(16))

        val buyLp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            dp(52)
        )
        buyLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        buyLp.bottomMargin = dp(16)
        root.addView(buyButton, buyLp)

        val scroll = ScrollView(this)
        scroll.overScrollMode = View.OVER_SCROLL_NEVER

        val scrollLp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        scrollLp.addRule(RelativeLayout.ABOVE, buyButtonId)
        root.addView(scroll, scrollLp)

        val content = LinearLayout(this)
        content.orientation = LinearLayout.VERTICAL
        content.setPadding(0, dp(12), 0, dp(16))
        scroll.addView(
            content,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val imageBox = RelativeLayout(this)
        content.addView(
            imageBox,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(320)
            )
        )

        val img = ImageView(this)
        img.setImageResource(imageRes)
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        img.background = roundBg(Color.WHITE, dp(10))
        img.clipToOutline = true
        imageBox.addView(
            img,
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                dp(320)
            )
        )

        if (badge.isNotEmpty()) {
            val badgeTv = TextView(this)
            badgeTv.text = badge
            badgeTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            badgeTv.setTypeface(null, Typeface.BOLD)
            badgeTv.setTextColor(Color.WHITE)
            badgeTv.gravity = Gravity.CENTER
            badgeTv.setPadding(dp(6), 0, dp(6), 0)
            badgeTv.background = if (badge == "ХИТ") hitBadgeBg() else newBadgeBg()

            val badgeLp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                dp(24)
            )
            badgeLp.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            badgeLp.addRule(RelativeLayout.ALIGN_PARENT_START)
            imageBox.addView(badgeTv, badgeLp)
        }

        val tvTitle = TextView(this)
        tvTitle.text = title
        tvTitle.setTextColor(BLACK.toColorInt())
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
        tvTitle.setTypeface(null, Typeface.BOLD)
        val titleLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        titleLp.topMargin = dp(16)
        content.addView(tvTitle, titleLp)

        val tvDesc = TextView(this)
        tvDesc.text = desc
        tvDesc.setTextColor("#8C8C8C".toColorInt())
        tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        val descLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        descLp.topMargin = dp(8)
        content.addView(tvDesc, descLp)

        val sizesRow = LinearLayout(this)
        sizesRow.orientation = LinearLayout.HORIZONTAL
        val sizesLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        sizesLp.topMargin = dp(16)
        content.addView(sizesRow, sizesLp)

        val sizeXXS = makeSize("XXS", selected = true)
        val sizeXS = makeSize("XS", selected = false)
        val sizeS = makeSize("S", selected = false)
        val sizeM = makeSize("M", selected = false)
        val sizeL = makeSize("L", selected = false)
        val sizeXL = makeSize("XL", selected = false)

        val all = arrayOf(sizeXXS, sizeXS, sizeS, sizeM, sizeL, sizeXL)

        fun select(t: TextView) {
            for (x in all) {
                x.background = roundBg(WHITE.toColorInt(), dp(14))
                x.setTextColor(BLACK.toColorInt())
            }
            t.background = roundBg(DARK_ORANGE.toColorInt(), dp(14))
            t.setTextColor(Color.WHITE)
        }

        for (t in all) {
            t.setOnClickListener { select(t) }
        }

        sizesRow.addView(sizeXXS); setRightMargin(sizeXXS, dp(8))
        sizesRow.addView(sizeXS); setRightMargin(sizeXS, dp(8))
        sizesRow.addView(sizeS); setRightMargin(sizeS, dp(8))
        sizesRow.addView(sizeM); setRightMargin(sizeM, dp(8))
        sizesRow.addView(sizeL); setRightMargin(sizeL, dp(8))
        sizesRow.addView(sizeXL)

        buyButton.setOnClickListener {
            buyButton.text = "Добавлено"
        }

        setContentView(root)
    }

    private fun makeSize(text: String, selected: Boolean): TextView {
        val t = TextView(this)
        t.text = text
        t.gravity = Gravity.CENTER
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)

        val lp = LinearLayout.LayoutParams(dp(44), dp(28))
        t.layoutParams = lp

        if (selected) {
            t.background = roundBg(DARK_ORANGE.toColorInt(), dp(14))
            t.setTextColor(Color.WHITE)
        } else {
            t.background = roundBg(WHITE.toColorInt(), dp(14))
            t.setTextColor(BLACK.toColorInt())
        }
        return t
    }

    private fun setRightMargin(v: View, m: Int) {
        val lp = v.layoutParams as LinearLayout.LayoutParams
        lp.rightMargin = m
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