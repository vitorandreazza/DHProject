package br.com.doghero.dhproject.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object ImageHelper {

    fun loadImage(context: Context, imageUrl: String, placeHolderResourceId: Int, imageView: ImageView) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(placeHolderResourceId)
                .into(imageView)
    }

    fun loadCircularImage(context: Context, imageUrl: String, placeHolderResourceId: Int, imageView: ImageView) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(placeHolderResourceId)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        val imageBitmap = (imageView.drawable as BitmapDrawable).bitmap
                        val imageDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap).apply {
                            isCircular = true
                            cornerRadius = maxOf(imageBitmap.width, imageBitmap.height) / 2.0f
                        }
                        imageView.setImageDrawable(imageDrawable)
                    }

                    override fun onError() {
                        imageView.setImageResource(placeHolderResourceId)
                    }
                })
    }
}
