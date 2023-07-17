package com.example.chatapplicationnew

import android.provider.ContactsContract.CommonDataKinds.Email

class User {
    var name : String? = null
    var email : String? = null
    var uid : String? = null

    constructor(){} //Note to self :- Firebase needs an empty constructor to work with ;
    constructor(name : String? , email: String? , uid : String? ){
        this.name = name ;
        this.email = email ;
        this.uid = uid ;
    }

    //Note to self :- we are now using a recycler view ;





}