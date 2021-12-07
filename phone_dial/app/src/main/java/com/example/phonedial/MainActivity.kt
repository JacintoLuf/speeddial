package com.example.phonedial

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    //var friends: FriendsController = TODO()
    var contacts: MutableList<Friend> = ArrayList()
    var dialed = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = findViewById<TextView>(R.id.editTextNumberDecimal)
        val addContact = findViewById<TextView>(R.id.addContact)
        addContact.isClickable = true
        val btn0 = findViewById<Button>(R.id.button0)
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        val btnStar = findViewById<Button>(R.id.buttonStar)
        val btnHash = findViewById<Button>(R.id.buttonHashtag)
        val btnCall = findViewById<ImageButton>(R.id.buttonCall)
        val btnDel = findViewById<ImageButton>(R.id.buttonDelete)

        btn0.setOnClickListener {
            update(btn0.text.toString())
        }
        btn1.setOnClickListener {
            update(btn1.text.toString())
        }
        btn2.setOnClickListener {
            update(btn2.text.toString())
        }
        btn3.setOnClickListener {
            update(btn3.text.toString())
        }
        btn4.setOnClickListener {
            update(btn4.text.toString())
        }
        btn5.setOnClickListener {
            update(btn5.text.toString())
        }
        btn6.setOnClickListener {
            update(btn6.text.toString())
        }
        btn7.setOnClickListener {
            update(btn7.text.toString())
        }
        btn8.setOnClickListener {
            update(btn8.text.toString())
        }
        btn9.setOnClickListener {
            update(btn9.text.toString())
        }
        btnStar.setOnClickListener {
            update(btnStar.text.toString())
        }
        btnHash.setOnClickListener {
            update(btnHash.text.toString())
        }
        btnCall.setOnClickListener {
            call()
        }
        btnDel.setOnClickListener {
            val text = number.text.toString()
            if (!text.isEmpty()){
                number.text = text.substring(0, number.length() - 1)
                dialed = number.text.toString()
            }
        }
        addContact.setOnClickListener{
            if (dialed.length > 1) {
                val myIntent = Intent(this@MainActivity, AddContact::class.java)
                myIntent.putExtra("phone", dialed)
                startActivity(myIntent)
            }else{
                val myToast = Toast.makeText(this, "No number dialed!", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val name = (intent.getSerializableExtra("name") as? String)!!
            val phone = (intent.getSerializableExtra("phone") as? String)!!
            contacts.add(Friend(name, phone))
        }
    }


    fun update(digit: String){
        val number = findViewById<TextView>(R.id.editTextNumberDecimal)
        val text = number.text.toString()

        number.text = text + digit
        dialed = number.text.toString()
    }

    private fun call(){
        if (dialed.length < 1) {
            val myToast = Toast.makeText(this, "No number dialed!", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + dialed)
            startActivity(dialIntent)
        }
    }
}