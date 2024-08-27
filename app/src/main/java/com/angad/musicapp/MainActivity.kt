package com.angad.musicapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        myRecyclerView = findViewById(R.id.recyclerView)


//      Fetching the data from the deezer API to the ApiInterface interface
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

//        Query to fetch data
        val retrofitData = retrofitBuilder.getData("eminem")

//        display the data into the application
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

//              if the API call is success then this method is executed
                val dataList = response.body()?.data!!
//                val textView = findViewById<TextView>(R.id.helloText)
//                textView.text = dataList.toString()

                myAdapter = MyAdapter(this@MainActivity, dataList)

//                Linking the RecyclerView with the Adapter
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("onResponse","onResponse: " + response.body())
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
//              if the API call is failure then this method is executed
                Log.d("Tag: onFailure","onFailure: " + p1.message)
            }
        })

    }
}