package id.alimasudd.qr_code

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class MainActivity : AppCompatActivity() {

    lateinit var generateQRBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateQRBtn = findViewById(R.id.button)

        // on below line we are adding on click
        // listener for our generate QR button.
        generateQRBtn.setOnClickListener {
        }


    }

    @SuppressLint("SetTextI18n")
    fun scanCode() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_EAN_13)
            .build()
        val scanner = GmsBarcodeScanning.getClient(this, options)
        val barcodeValue = findViewById<TextView>(R.id.barcode_value)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val rawValue: String? = barcode.rawValue
                barcodeValue.text = rawValue
                if(barcode.valueType == Barcode.TYPE_URL) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rawValue))
                    startActivity(browserIntent)
                }
            }

    }

}