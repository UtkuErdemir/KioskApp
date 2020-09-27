package com.example.kioskapp.Tools

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Html
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned
import android.text.style.LeadingMarginSpan.LeadingMarginSpan2
import androidx.core.text.HtmlCompat
import com.example.kioskapp.R


internal class LeadingMarginSpan(private val lines: Int, private val margin: Int) :
    LeadingMarginSpan2 {

    override fun getLeadingMargin(first: Boolean): Int {
        return if (first) {
            margin
        } else {
            0
        }
    }

    override fun drawLeadingMargin(
        c: Canvas?, p: Paint?, x: Int, dir: Int,
        top: Int, baseline: Int, bottom: Int, text: CharSequence?,
        start: Int, end: Int, first: Boolean, layout: Layout?
    ) {
    }

    override fun getLeadingMarginLineCount(): Int {
        return lines
    }
}
