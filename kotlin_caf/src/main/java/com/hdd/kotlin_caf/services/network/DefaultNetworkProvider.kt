package com.hdd.kotlin_caf.services.network

import android.content.Context
import android.util.Log
import com.hdd.kotlin_caf.services.common.RestMessageResponse
import com.hdd.kotlin_caf.services.filter.ApiThrowableFilter
import com.hdd.kotlin_caf.services.filter.InterceptFilter
import com.hdd.kotlin_caf.services.filter.NetworkFilter
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.schedulers.Schedulers
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit

/**
 * Created on 2/27/2018.
 * @author duonghd
 */

open class DefaultNetworkProvider(context: Context, baseUrl: String, netTimeout: Long = 120) : AbstractNetworkProvider(context), NetworkProvider {

    private val UTF8 = Charset.forName("UTF-8")
    private var retrofit: Retrofit
    private var apiErrorFilter: InterceptFilter? = null

    init {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(netTimeout, TimeUnit.SECONDS)
        builder.readTimeout(netTimeout, TimeUnit.SECONDS)
        builder.writeTimeout(netTimeout, TimeUnit.SECONDS)

        //Enable log
        builder.addInterceptor { chain ->
            val request = chain.request()
            val connection = chain.connection()
            val protocol = if (connection != null) connection.protocol() else Protocol.HTTP_1_1

            val requestBuilder = request.newBuilder().build()
            val bufferRequest = Buffer()
            requestBuilder.body()?.writeTo(bufferRequest)

            var requestStartMessage = "--> ${request.method()} ${request.url()} $protocol"

            val requestBody = request.body()
            if (requestBody != null) {
                requestStartMessage = String.format("%s\nContent-Header:: %sContent-Type:: %s\nContent-Length:: %s\nContent-Body:: %s",
                        requestStartMessage,
                        if (request.headers().toString().isEmpty()) "\n" else request.headers(),
                        requestBody.contentType(),
                        requestBody.contentLength(),
                        if (bufferRequest.readUtf8().isEmpty()) null else bufferRequest.readUtf8())
            }
            Log.i("INFO", requestStartMessage)

            val startNs = System.nanoTime()
            val response = chain.proceed(request)
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

            val responseBody = response.body()
            val contentLength = responseBody!!.contentLength()
            val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"

            Log.i("INFO", response.headers().toString())
            Log.i("INFO", "<-- ${response.code()} ${response.message()} ${response.request().url()} (${tookMs}ms, $bodySize)")

            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()

            var charset: Charset? = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8)
                } catch (e: UnsupportedCharsetException) {
                    Log.i("INFO", "")
                    Log.i("INFO", "Couldn't decode the response body; charset is likely malformed.")
                    Log.i("INFO", "<-- END HTTP")
                }
            }
            if (contentLength != 0L) {
                Log.i("INFO", "")
                Log.i("INFO", buffer.clone().readString(charset!!))
            }
            Log.i("INFO", "<-- END HTTP (${buffer.size()}-byte body)")


            response
        }

        retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build()
    }

    override fun <T> provideApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    override fun setApiErrorFilter(apiErrorFilter: InterceptFilter): NetworkProvider {
        this.apiErrorFilter = apiErrorFilter
        return this
    }

    override fun <TResponse : RestMessageResponse<TResult>, TResult> transformResponse(call: Observable<TResponse>): Observable<TResult> {

        val res: Observable<TResult> = call
                .observeOn(Schedulers.computation())
                .onErrorResumeNext { throwable -> NetworkFilter<TResponse>(this).execute(throwable) }
                .onErrorResumeNext { throwable -> ApiThrowableFilter<TResponse>().execute(throwable) }
                .compose(apiErrorFilter?.execute())
                .flatMap { tResponse -> Observable.just(tResponse.data) }

        return res.onExceptionResumeNext(Observable.empty())
    }
}
