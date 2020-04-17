package com.example.menuappv2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.menuappv2.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)
        //Setup the toolbar, we need it throughout the app.
        setSupportActionBar(this.findViewById(R.id.mainActivityToolbar))
        //We need to hide the action bar in the splash screen.
        supportActionBar?.hide()
    }
}
