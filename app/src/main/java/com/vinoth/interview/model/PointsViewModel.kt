package com.vinoth.interview.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinoth.interview.dao.Api
import com.vinoth.interview.dao.FinalResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis

class PointsViewModel : ViewModel() {

    private var listMutableLiveData: MutableLiveData<FinalResponse>? = null

    val liveData: LiveData<FinalResponse>
        get() {
            if (listMutableLiveData == null) {
                listMutableLiveData = MutableLiveData()
                fireParallelRequest()
            }
            return listMutableLiveData as MutableLiveData<FinalResponse>
        }

    private fun fireParallelRequest() {

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        viewModelScope.launch {

            var response: FinalResponse? = null
            val measureTime = measureTimeMillis {

                val resultOneDeferred = async { api.posts.await() }
                val resultTwoDeferred = async { api.users.await() }

                response = FinalResponse(
                    posts = resultOneDeferred.await(),
                    users = resultTwoDeferred.await()
                )
            }
            response?.timeTaken = measureTime
            listMutableLiveData?.value = response

            Log.d("TAG", response.toString())
        }
    }
}