package com.example.receitas.presenter.mapper

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

class ImageMapper(val context: Context) {

    fun camCapture(image: Bitmap): String {

        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val img = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            image,
            "imagem",
            "desc"
        )
        val uri = Uri.parse(img)
        return uri.toString()
    }

    fun albumCapture(image: Uri): String {
        return image.toString()
    }

}