package dxmnd.com.mymusicplayer.rxtest.retrofit

import dxmnd.com.mymusicplayer.rxtest.model.JsonHolderService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by HunJin on 2017-11-24.
 */

class RetrofitHelper {
    companion object {
        fun getRetrofit(): JsonHolderService? {
            var retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create<JsonHolderService>(JsonHolderService::class.java!!)
        }
    }
}

