package ir.mohammadhf.bitexchallenge.data.repo

import ir.mohammadhf.bitexchallenge.data.model.TradeUi
import javax.inject.Inject

class FakeDataRepository @Inject constructor() {

    suspend fun getSellList(): ArrayList<TradeUi> =
        arrayListOf(
            TradeUi(0.223, "7,887.75"),
            TradeUi(3.164, "7,887.59"),
            TradeUi(0.348, "7,887.54"),
            TradeUi(0.236, "7,887.31"),
            TradeUi(0.692, "7,886.09"),
            TradeUi(0.180, "7,886.00")
        )

    suspend fun getBuyList(): ArrayList<TradeUi> =
        arrayListOf(
            TradeUi(1.950, "7,885.75"),
            TradeUi(2.328, "7,885.59"),
            TradeUi(0.010, "7,885.54"),
            TradeUi(21.206, "7,885.31"),
            TradeUi(0.360, "7,884.09"),
            TradeUi(1.971, "7,884.00")
        )
}