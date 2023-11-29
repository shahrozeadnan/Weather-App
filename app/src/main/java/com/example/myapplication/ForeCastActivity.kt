package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.myapplication.adapter.ForeCastAdapter
import com.example.myapplication.mvvm.WeatherVm

class ForeCastActivity : AppCompatActivity() {

    private lateinit var adapterForeCastAdapter: ForeCastAdapter
    lateinit var viM:WeatherVm
    lateinit var rvForeCast: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fore_cast)

        viM=ViewModelProvider(this).get(WeatherVm::class.java)
        adapterForeCastAdapter= ForeCastAdapter()
        rvForeCast=findViewById<RecyclerView>(R.id.rvForeCast)

        val sharedPrefs=SharedPrefs.getInstance(this)
        val city=sharedPrefs.getValueorNull("city")

        if(city!=null)
        {
            viM.getForecastUpcomng(city)
        }
        else
        {
            viM.getForecastUpcomng()
        }
        viM.forecastWeatherLiveData.observe(this, Observer {

            val setNewlist = it as List<WeatherList>



            Log.d("Forecast LiveData", setNewlist.toString())



            adapterForeCastAdapter.setList(setNewlist)


            rvForeCast.adapter = adapterForeCastAdapter



        })


    }
}