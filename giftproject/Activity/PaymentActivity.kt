package com.example.giftshopproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.giftshopproject.R
import com.example.giftshopproject.databinding.ActivityCategoryBinding
import com.example.giftshopproject.databinding.ActivityPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultListener
{
    private lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var i = intent
        var id = i.getIntExtra("id",100)
        var name = i.getStringExtra("name")
        var price = i.getStringExtra("price")
        var des = i.getStringExtra("desc")
        var img = i.getStringExtra("image")

        binding.paymentNameTextView.setText(name)
        binding.paymentPriceTextView.setText(price)
        binding.paymentDesTextView.setText(des)
        Picasso.get().load(img).into(binding.paymentImageView)

        binding.payment.setOnClickListener {

            val checkout = Checkout()

            checkout.setKeyID("rzp_test_y492F5KrkaJVt8")
            val  obj = JSONObject()
            try {
                obj.put("name","Plants")
                obj.put("desc","Test Payment")
                obj.put("currency","INR")
                obj.put("amount",price)
                obj.put("prefill.contact","+919023063274")
                obj.put("prefill.email","krupaliv09@gmail.com")
                checkout.open(this,obj)

            }
            catch (e: JSONException)
            {
                e.printStackTrace()
            }
        }


    }

    override fun onPaymentSuccess(p0: String?)
    {
        Toast.makeText(applicationContext, "Payment is successful : " +p0, Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?)
    {
        Toast.makeText(applicationContext, "Payment Error : " +p0, Toast.LENGTH_SHORT).show()
    }
}