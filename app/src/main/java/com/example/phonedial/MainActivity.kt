package com.example.phonedial

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    var contacts: MutableList<Friend> = ArrayList()
    var dialed = ""
    var dial1 = ""
    var dial2 = ""
    var dial3 = ""
    val addNContact = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val fr = it.data?.getSerializableExtra("friend") as Friend
            contacts.add(fr)
            print("\n \n \n")
            for (i in contacts.size-1 downTo 0) print(contacts.get(i).name+" \n ")
            print("\n \n \n")
        }
    }
    val assignContact = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK){
            val res = it.data?.getStringExtra("dialed") as Int
            val fr = it.data?.getSerializableExtra("friend") as Friend
            if (res == 1) {
                findViewById<TextView>(R.id.dial1).setText(fr.name)
                dial1 = fr.phone.toString()
            }else if (res == 2){
                findViewById<TextView>(R.id.dial2).setText(fr.name)
                dial2 = fr.phone.toString()
            }else if (res == 3){
                findViewById<TextView>(R.id.dial3).setText(fr.name)
                dial3 = fr.phone.toString()
            }
        }
    }

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
        val btnDel = findViewById<Button>(R.id.buttonDelete)
        val d1 = findViewById<Button>(R.id.dial1)
        val d2 = findViewById<Button>(R.id.dial2)
        val d3 = findViewById<Button>(R.id.dial3)

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
        btnDel.setOnLongClickListener {
            number.setText("")
            dialed = ""
            true
        }

        d1.setOnClickListener {
            if (dial1 == "") {
                val myToast = Toast.makeText(this, "No number assigned!", Toast.LENGTH_SHORT)
                myToast.show()
            }
            else{
                val dialIntent = Intent(Intent.ACTION_CALL)
                dialIntent.data = Uri.parse("tel:" + dial1)
                startActivity(dialIntent)
            }
        }
        d2.setOnClickListener {
            if (dial2 == "") {
                val myToast = Toast.makeText(this, "No number assigned!", Toast.LENGTH_SHORT)
                myToast.show()
            }
            else{
                val dialIntent = Intent(Intent.ACTION_CALL)
                dialIntent.data = Uri.parse("tel:" + dial2)
                startActivity(dialIntent)
            }
        }
        d3.setOnClickListener {
            if (dial3 == "") {
                val myToast = Toast.makeText(this, "No number assigned!", Toast.LENGTH_SHORT)
                myToast.show()
            }
            else{
                val dialIntent = Intent(Intent.ACTION_CALL)
                dialIntent.data = Uri.parse("tel:" + dial3)
                startActivity(dialIntent)
            }
        }
        d1.setOnLongClickListener {
            val myIntent = Intent(this@MainActivity, ContactListActivity::class.java)
            myIntent.putExtra("contatcs", contacts as Serializable)
            myIntent.putExtra("dialed", 1)
            assignContact.launch(myIntent)
            return@setOnLongClickListener true
        }
        d2.setOnLongClickListener {
            val myIntent = Intent(this@MainActivity, ContactListActivity::class.java)
            myIntent.putExtra("contatcs", contacts as Serializable)
            myIntent.putExtra("dialed", 2)
            assignContact.launch(myIntent)
            return@setOnLongClickListener true
        }
        d3.setOnLongClickListener {
            val myIntent = Intent(this@MainActivity, ContactListActivity::class.java)
            myIntent.putExtra("contatcs", contacts as Serializable)
            myIntent.putExtra("dialed", 3)
            assignContact.launch(myIntent)
            return@setOnLongClickListener true
        }
        addContact.setOnClickListener{
            if (dialed.length > 1) {
                val myIntent = Intent(this@MainActivity, AddContactActivity::class.java)
                myIntent.putExtra("phone", dialed)
                addNContact.launch(myIntent)
            }else{
                val myToast = Toast.makeText(this, "No number dialed!", Toast.LENGTH_SHORT)
                myToast.show()
            }
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