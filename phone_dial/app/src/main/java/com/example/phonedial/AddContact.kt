package com.example.phonedial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContact : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)
        val contacts = intent.extras?.get("friends") as FriendsController
        val extra_number = intent.extras?.get("phone") as String
        val name = findViewById<EditText>(R.id.editTextTextPersonName2)
        val number = findViewById<EditText>(R.id.editTextNumberDecimal2)
        number.setText(extra_number)
        val btnAdd = findViewById<Button>(R.id.buttonAddContact)
        val btnCancel = findViewById<Button>(R.id.buttonCancel)
        btnAdd.setOnClickListener{
            if (name.text.toString().length < 3 || number.text.toString().length < 1){
                val myToast = Toast.makeText(this, "Invalid Name or Number!", Toast.LENGTH_SHORT)
                myToast.show()
            }else{
                contacts.add_friend(name.text.toString(), number.text.toString())
                val intent = Intent()
                intent.putExtra("res", contacts)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
        btnCancel.setOnClickListener{
            val intent = Intent()
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }
}
