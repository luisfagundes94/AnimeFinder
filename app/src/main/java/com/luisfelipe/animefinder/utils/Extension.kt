package com.luisfelipe.animefinder.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso

fun Fragment.horizontalRecyclerViewLayout() = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, getString(messageId), duration).show()
}

fun Activity.toast(messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(messageId), duration).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.load(imageUrl: String) {
    Picasso.get().load(imageUrl).resize(675, 1050).into(this)
}