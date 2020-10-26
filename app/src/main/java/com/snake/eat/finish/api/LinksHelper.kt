package com.snake.eat.finish.api

import android.content.Context
import android.util.Log
import com.facebook.applinks.AppLinkData
import com.snake.eat.finish.features.Preference


class LinksHelper(val context: Context) {
    var url : String? = null
    var mainActivity : ApiResponse? = null
    var exec = false
    val sPrefUrl = Preference(context).apply { getSp("fb") }

    init{
        url = sPrefUrl.getStr("url")
        Log.e("Links", url.toString())
        if(url == null) tree()
    }

    fun attachWeb(api : ApiResponse){
        mainActivity = api
    }

    private fun tree() {
        AppLinkData.fetchDeferredAppLinkData(context
        ) { appLinkData: AppLinkData? ->
            if (appLinkData != null && appLinkData.targetUri != null) {
                if (appLinkData.argumentBundle["target_url"] != null) {
                    Log.e("DEEP", "SRABOTAL")
                    //CustomMessage().scheduleMsg(context)
                    exec = true
                    val tree = appLinkData.argumentBundle["target_url"].toString()
                    val uri = tree.split("$")
                    url = "https://" + uri[1]
                    if(url != null){
                        sPrefUrl.putStr("url", url!!)
                        mainActivity?.execResponse(url!!)
                    }
                }
            }
        }
    }
}