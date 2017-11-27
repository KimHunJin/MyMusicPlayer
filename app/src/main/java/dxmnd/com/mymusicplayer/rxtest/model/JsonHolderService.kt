package dxmnd.com.mymusicplayer.rxtest.model

import io.reactivex.Observable
import retrofit2.http.POST


/**
 * Created by HunJin on 2017-11-24.
 */
interface JsonHolderService {
    @POST("posts")
    fun createUser(): Observable<JsonItem>
}