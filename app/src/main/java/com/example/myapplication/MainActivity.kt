package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var db: DataHelper
    lateinit var etid: EditText
    lateinit var etName: EditText
    lateinit var etpassword: EditText
    lateinit var btn_login: Button
    lateinit var btn_view: Button
    lateinit var btn_update: Button
    lateinit var btn_delete: Button
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DataHelper(this)

        FindViewBYID()
        Body()
    }

    private fun Body() {

        fun displayData() {

            val cursor = db.getAllData()

            val list = ArrayList<String>()

            if (cursor.count == 0) {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
                return
            }

            while (cursor.moveToNext()) {

                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val pass = cursor.getString(2)

                list.add("ID: $id\nName: $name\nPassword: $pass")
            }

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                list
            )

            listView.adapter = adapter
        }

        btn_login.setOnClickListener {

            val result = db.insertData(
                etName.text.toString(),
                etpassword.text.toString()
            )

            if (result)
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            displayData()
        }

        btn_view.setOnClickListener {
            displayData()
        }

        btn_update.setOnClickListener {

            val result = db.updateData(
                etid.text.toString(),
                etName.text.toString(),
                etpassword.text.toString()
            )

            if (result)
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()

            displayData()
        }

        btn_delete.setOnClickListener {

            val result = db.deleteData(etid.text.toString())

            if (result > 0)
                Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()

            displayData()
        }

    }
    private fun FindViewBYID() {
        etid=findViewById(R.id.edt_id)
        etName = findViewById(R.id.edt_username)
        etpassword=findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_view=findViewById(R.id.btn_view)
        btn_update=findViewById(R.id.btn_update)
        btn_delete=findViewById(R.id.btn_delete)
        listView=findViewById(R.id.listView)
    }
}