package ir.mohammadhf.bitexchallenge.feature.futures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mohammadhf.bitexchallenge.data.model.AmountResponseUi
import ir.mohammadhf.bitexchallenge.data.model.TradeUi
import ir.mohammadhf.bitexchallenge.data.repo.AmountRepository
import ir.mohammadhf.bitexchallenge.data.repo.FakeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuturesViewModel @Inject constructor(
    private val fakeDataRepository: FakeDataRepository,
    private val amountRepository: AmountRepository
): ViewModel() {

    private val _amountState: MutableStateFlow<AmountResponseUi?> =
        MutableStateFlow(null)

    val amountState: StateFlow<AmountResponseUi?> = _amountState

    private val _buyListState = MutableStateFlow(arrayListOf<TradeUi>())

    val buyListState: StateFlow<ArrayList<TradeUi>> = _buyListState

    private val _sellListState = MutableStateFlow(arrayListOf<TradeUi>())

    val sellListState: StateFlow<ArrayList<TradeUi>> = _sellListState

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val buyDiffered = async { fakeDataRepository.getBuyList() }
            val sellDiffered = async { fakeDataRepository.getSellList() }

            _buyListState.value = buyDiffered.await()
            _sellListState.value = sellDiffered.await()
        }
    }

    fun sendAmount(amount: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val amountResponseDiff = async { amountRepository.sendAmounts(amount) }

            _amountState.value = amountResponseDiff.await().let {
                AmountResponseUi(
                    it.amount ?: 0.0,
                    it.createdAt ?: "null date",
                    it.id ?: "-1",
                    it.token?: "null token"
                )
            }
        }
    }
}