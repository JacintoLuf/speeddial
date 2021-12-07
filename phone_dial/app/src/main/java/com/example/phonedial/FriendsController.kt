package com.example.phonedial

import java.io.Serializable

class FriendsController (val friends: MutableList<Friend> = TODO()) : Serializable{


    fun add_friend(name: String, number: String) {
        friends.add(Friend(name, number))
    }

    fun get_friend_lis() {
        return friends.sortBy { it.name }
    }
}