package com.example.phonedial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)

        val extra_number = intent.extras?.get("phone") as String

        val name = findViewById<EditText>(R.id.editTextTextPersonName2)
        val number = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val btnAdd = findViewById<Button>(R.id.buttonAddContact)
        val btnCancel = findViewById<Button>(R.id.buttonCancel)

        number.setText(extra_number)
        btnAdd.setOnClickListener{
            if (name.text.toString().length < 3 || number.text.toString().length < 1){
                Toast.makeText(this, "Invalid Name or Number!", Toast.LENGTH_SHORT).show()
            }else{
                val friend = Friend(name.text.toString(), number.text.toString())
                val intent = Intent()
                if (name.text.toString().isBlank()) intent.putExtra("name", "sem nome")
                else intent.putExtra("friend", friend)
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
