package dev.orlovvv.art.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.widget.ImageView

fun ImageView.setBlackAndWhite() {
    val matrix: ColorMatrix = ColorMatrix()
    matrix.setSaturation(0F);
    val filter =  ColorMatrixColorFilter(matrix);
    colorFilter = filter
}