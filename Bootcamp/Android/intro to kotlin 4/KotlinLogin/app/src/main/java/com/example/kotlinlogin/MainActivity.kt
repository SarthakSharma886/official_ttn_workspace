package com.example.kotlinlogin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var etName = findViewById<EditText>(R.id.et_name)
        var etPass = findViewById<EditText>(R.id.et_pass)

        findViewById<Button>(R.id.bt_login).setOnClickListener {

            if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())) {
                intent = Intent(this@MainActivity, AfterLogin::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Fill all Details", Toast.LENGTH_SHORT).show()
            }

        }

    }

}
