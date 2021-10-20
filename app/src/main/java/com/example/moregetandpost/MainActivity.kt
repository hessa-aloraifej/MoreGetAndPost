package com.example.moregetandpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addbtn=findViewById<Button>(R.id.button)
        var viewbtn=findViewById<Button>(R.id.button2)
        var etName=findViewById<EditText>(R.id.editTextTextPersonName)
        var text=findViewById<TextView>(R.id.textView2)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        addbtn.setOnClickListener {
            val name=etName.text.toString()

            apiInterface!!.addUsers(NameItem(name)).enqueue(object: Callback<NameItem> {
                override fun onResponse(
                    call: Call<NameItem>,
                    response: Response<NameItem>
                ) {
                    Toast.makeText(this@MainActivity, "The User Has Been Added Successfully!!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<NameItem>, t: Throwable) {
                    Toast.makeText(applicationContext, "Sorry,The User Has Not Been Added Successfully!!", Toast.LENGTH_SHORT).show()
                }

            }
            )
        }
        viewbtn.setOnClickListener {


            apiInterface!!.getUsers().enqueue(object: Callback<ArrayList<NameItem>> {

                override fun onFailure(call: Call<ArrayList<NameItem>>, t: Throwable) {
                    call.cancel()
                }
                override fun onResponse(
                    call: Call<ArrayList<NameItem>>,
                    response: Response<ArrayList<NameItem>>
                ) {
                    var users=""
                    val list = response.body()!!
                    for (user in list ){
                       users ="$users \n ${user.name}"


                        }
                    text.text=users
                    }


            })


        }

    }
}