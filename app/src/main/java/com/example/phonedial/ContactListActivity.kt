package com.example.phonedial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListActivity : AppCompatActivity() {

    private lateinit var adapter: FriendsAdapter
    private var contacts: MutableList<Friend> = arrayListOf()
    private var dialed: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_list)

        var contacts = intent.extras?.get("contatcs") as MutableList<Friend>
        val dialed = intent.extras?.get("dialed") as Int

        var contactListView = findViewById<RecyclerView>(R.id.contactList)

        adapter = FriendsAdapter(contacts)
        contactListView.adapter = FriendsAdapter(contacts)
        contactListView.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = {
            select(it)
        }
    }

   fun select(pos: Int) {
       print("\n \n \n returning \n \n \n")
       val name = contacts.get(pos).name
       val phone = contacts.get(pos).phone
       intent.putExtra("friend", Friend(name, phone))
       intent.putExtra("dialed", dialed)
       setResult(RESULT_OK, intent)
       finish()
   }
}