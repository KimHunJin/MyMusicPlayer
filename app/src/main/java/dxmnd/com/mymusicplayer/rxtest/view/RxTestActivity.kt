package dxmnd.com.mymusicplayer.rxtest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dxmnd.com.mymusicplayer.R
import dxmnd.com.mymusicplayer.utils.logger.log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RxTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_test)


        log("hello")
        var retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

//        retrofit.create<JsonHolderService>(JsonHolderService::class.java)
//                .createUser().subscribe({},{
//            it.localizedMessage
//            log(it)
//        })
        log(null)
        log(Exception("my error"))
        log("hello world")

    }
}
