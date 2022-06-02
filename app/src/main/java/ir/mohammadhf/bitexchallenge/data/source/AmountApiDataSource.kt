package ir.mohammadhf.bitexchallenge.data.source

import ir.mohammadhf.bitexchallenge.data.model.AmountResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AmountApiDataSource {

    @POST("order")
    suspend fun sendAmount(@Body amountResponse: AmountResponse): AmountResponse
}