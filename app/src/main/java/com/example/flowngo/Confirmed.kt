package com.example.flowngo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kotlinx.coroutines.*

private const val key3 = "SERVINGS"
private const val key4 = "COUNT"
private var TAG = "Confirmed"
class Confirmed : AppCompatActivity(){
    private var drinkOrder: DoubleArray = DoubleArray(10)
    private val db = Firebase.database
    private var count: Long = 0
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmed)
        imageView = findViewById(R.id.imageView)
        drinkOrder = intent.getDoubleArrayExtra(key3) as DoubleArray
        count = intent.getLongExtra(key4, -1)

        db.getReference("Amount0").setValue(drinkOrder[0])
        db.getReference("Amount1").setValue(drinkOrder[1])
        db.getReference("Amount2").setValue(drinkOrder[2])
        db.getReference("Amount3").setValue(drinkOrder[3])
        db.getReference("Amount4").setValue(drinkOrder[4])
        db.getReference("Amount5").setValue(drinkOrder[5])
        db.getReference("Amount6").setValue(drinkOrder[6])
        db.getReference("Amount7").setValue(drinkOrder[7])
        db.getReference("Amount8").setValue(drinkOrder[8])
        db.getReference("Amount9").setValue(drinkOrder[9])

        count += 1
        db.getReference("Count").setValue(count)
        val bitmap = generateQRCode(count.toString())
        imageView.setImageBitmap(bitmap);
        val homeButton = findViewById<Button>(R.id.homebutton)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            Log.d(TAG, "generateQRCode: ${e.message}")
        }
        return bitmap
    }
}
