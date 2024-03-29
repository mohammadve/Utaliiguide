package com.utaliiguides

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView


class RobotoRegularTextView : TextView {


    constructor(context: Context) : super(context) {
        val face = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
        this.setTypeface(face)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val face = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
        this.setTypeface(face)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val face = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
        this.setTypeface(face)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


    }

}
