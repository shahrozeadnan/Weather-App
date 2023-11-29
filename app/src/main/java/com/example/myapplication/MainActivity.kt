package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.WeatherToday
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.mvvm.WeatherVm
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var vm:WeatherVm
    lateinit var adapter:WeatherToday

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        vm=ViewModelProvider(this).get(WeatherVm::class.java)
        adapter= WeatherToday()

        val sharedPrefs=SharedPrefs.getInstance(this@MainActivity)
        sharedPrefs.clearcityvalue()
        binding.lifecycleOwner=this
        binding.vm=vm

        vm.todayWeatherLiveData.observe(this, Observer {

            val setNewlist = it as List<WeatherList>


            Log.e("TODayweather list", it.toString())
            adapter.setList(setNewlist)
            binding.forecastRecyclerView.adapter = adapter

        })

        vm.closetorexactlysameweatherdata.observe(this, Observer {


            val temperatureFahrenheit = it!!.main?.temp
            val temperatureCelsius = (temperatureFahrenheit?.minus(273.15))
            val temperatureFormatted = String.format("%.2f", temperatureCelsius)

            for (i in it.weather) {


                binding.descMain.text = i.description








            }

            binding.tempMain.text = "$temperatureFormatted°"


            binding.humidityMain.text = it.main!!.humidity.toString()
            binding.windSpeed.text = it.wind?.speed.toString()


            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val date = inputFormat.parse(it.dtTxt!!)
            val outputFormat = SimpleDateFormat("d MMMM EEEE", Locale.getDefault())
            val dateanddayname = outputFormat.format(date!!)

            binding.dateDayMain.text = dateanddayname

            binding.chanceofrain.text = "${it.pop.toString()}%"


            // setting the icon
            for (i in it.weather) {


                if (i.icon == "01d") {


                    binding.imageMain.setImageResource(R.drawable.sunny)

                }

                if (i.icon == "01n") {
                    binding.imageMain.setImageResource(R.drawable.night)


                }

                if (i.icon == "02d") {

                    binding.imageMain.setImageResource(R.drawable.twod)


                }


                if (i.icon == "02n") {
                    binding.imageMain.setImageResource(R.drawable.twon)


                }


                if (i.icon == "03d" || i.icon == "03n") {


                    binding.imageMain.setImageResource(R.drawable.threedn)


                }



                if (i.icon == "10d") {

                    binding.imageMain.setImageResource(R.drawable.tend)


                }


                if (i.icon == "10n") {

                    binding.imageMain.setImageResource(R.drawable.tenn)


                }


                if (i.icon == "04d" || i.icon == "04n") {


                    binding.imageMain.setImageResource(R.drawable.fourdn)


                }


                if (i.icon == "09d" || i.icon == "09n") {


                    binding.imageMain.setImageResource(R.drawable.ninedn)


                }



                if (i.icon == "11d" || i.icon == "11n") {


                    binding.imageMain.setImageResource(R.drawable.elevend)


                }


                if (i.icon == "13d" || i.icon == "13n") {

                    binding.imageMain.setImageResource(R.drawable.thirteend)


                }

                if (i.icon == "50d" || i.icon == "50n") {


                    binding.imageMain.setImageResource(R.drawable.fiftydn)


                }

            }

        })


      if(checklocation())
      {
          getcurrentlocation()
      }
        else
      {
          requestlocation()
      }

        private fun checklocation:Boolean()
        {

        }
        private fun requestlocation()
        {

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        )
            {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults)

            }





    }
}