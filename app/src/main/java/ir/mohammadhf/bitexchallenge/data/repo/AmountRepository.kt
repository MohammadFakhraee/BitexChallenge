package ir.mohammadhf.bitexchallenge.data.repo

import ir.mohammadhf.bitexchallenge.data.model.AmountResponse
import ir.mohammadhf.bitexchallenge.data.source.AmountApiDataSource
import javax.inject.Inject

class AmountRepository @Inject constructor(
    private val amountApiDataSource: AmountApiDataSource
) {

    suspend fun sendAmounts(amount: Double): AmountResponse =
        amountApiDataSource.sendAmount(AmountResponse(
            "BTC", amount
        ))
}