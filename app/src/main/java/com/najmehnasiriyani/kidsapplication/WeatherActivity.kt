package com.najmehnasiriyani.kidsapplication

//import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_weather.*
//import android.widget.ListAdapter
//import kotlinx.android.synthetic.main.activity_weather.*
import okhttp3.*
//import org.json.JSONArray
import java.io.IOException
//import java.net.HttpURLConnection
//import java.net.URL


class WeatherActivity : AppCompatActivity() {
var temp = 0.0
    var humidity = 0
    var pressure = 0
    var cityName = "London,uk"
    override fun onStart() {
        super.onStart()
//        pressureText.setText(temp.toString())
//        humidityText.setText(humidity.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        weatherButton.setOnClickListener {
            if (citySpinner.selectedItemPosition > -1 ){
                val city = citySpinner.selectedItem.toString()
                //val lat =23.8103; val long = 90.4125
                getWeather(city)
            }

        }
    }

fun getWeather(city:String){
    val url = "http://api.openweathermap.org/data/2.5/weather?q="+ city + "&APPID=ec8a57ac4e3846b523a0ae52499a7985"
    //AsyncTaskHandleJSon().execute(url)
    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            println("Failed to execute request")
            runOnUiThread {
                Toast.makeText(this@WeatherActivity, "Unable to get weather details from server", Toast.LENGTH_SHORT).show()

            }
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response?.body()?.string()
            val gson = GsonBuilder().create()
            val homeFeed = gson.fromJson(body, WeatherNaj::class.java)
            println(" The current weather is: " + homeFeed)
            temp = homeFeed.main.temp - 273.15
            val myTemp = "%.2f".format(temp)
            pressure = homeFeed.main.pressure
            humidity = homeFeed.main.humidity
            runOnUiThread {
                tempText.setText(myTemp)
                pressureText.setText(pressure.toString())
                humidityText.setText(humidity.toString())

            }

        }
    })
//    pressureText.setText(temp.toString())
//    humidityText.setText(humidity.toString())
}
}

//class WeatherFeed(val reports: List<Weather>)
class Weather(val temp: Double, val pressure: Int, val humidity: Int)

class WeatherNaj(
    val main: Weather
    )
//    inner class AsyncTaskHandleJSon : AsyncTask<String, String, String>(){
//        override fun doInBackground(vararg url: String?): String {
//            var text : String
//            val connection = URL(url[0]).openConnection() as HttpURLConnection
//            try{
//                connection.connect()
//                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
//
//            }finally {
//                connection.disconnect()
//            }
//            return text
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            handleJson(result!!)
//        }
//    }
//    private fun handleJson(jsonString : String){
//        val jsonArray = JSONArray(jsonString)
//        val list = ArrayList<WeatherClass>()
//        val jsonObject = jsonArray.getJSONObject(0)
//        tempText.setText(jsonObject.getJSONObject("main").getDouble("temp").toString())
//        humidityText.setText(jsonObject.getJSONObject("main").getDouble("humidity").toString())
//        var x = 0
//        while ( x < jsonArray.length()){
//            val jsonObject = jsonArray.getJSONObject(x)
//
//            list.add(WeatherClass(
//                cityName,
//                jsonObject.getJSONObject("main").getDouble("temp"),
//                jsonObject.getJSONObject("main").getDouble("humidity"),
//                jsonObject.getJSONObject("main").getDouble("pressure"),
//                jsonObject.getJSONObject("wind").getDouble("speed")
//            ))
//            x++
//        }

//  }

