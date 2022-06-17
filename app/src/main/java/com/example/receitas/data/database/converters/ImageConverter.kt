package com.example.receitas.data.database.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class ImageConverter {

    @TypeConverter
    fun deBitmap(image: Bitmap?): ByteArray? {
        val bytes = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        return bytes.toByteArray()
    }

    @TypeConverter
    fun paraBitmap(image: ByteArray?): Bitmap? {
        var bitmap: Bitmap? = null
        if (image != null) {
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        }
        return bitmap
    }
}