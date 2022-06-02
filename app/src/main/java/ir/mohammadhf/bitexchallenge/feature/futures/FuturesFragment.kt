package ir.mohammadhf.bitexchallenge.feature.futures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.mohammadhf.bitexchallenge.data.model.AmountResponseUi
import ir.mohammadhf.bitexchallenge.databinding.FragmentFuturesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FuturesFragment : Fragment() {
    private val futuresViewModel: FuturesViewModel by viewModels()


    private val buyListAdapter = TradeListAdapter(1)
    private val sellListAdapter = TradeListAdapter(0)

    private var _binding: FragmentFuturesBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFuturesBinding.inflate(inflater, container, false)

        futuresViewModel.loadData()

        binding.apply {

            buyingRv.adapter = buyListAdapter
            sellerRv.adapter = sellListAdapter
            increaseAmountText.setOnClickListener {
                if (userSelectedAmountEt.text.toString().isNotEmpty()) {
                    userSelectedAmountEt.setText(
                        "${userSelectedAmountEt.text.toString().toDouble() + 1}"
                    )
                }
            }

            decreaseAmountText.setOnClickListener {
                if (userSelectedAmountEt.text.toString().isNotEmpty()) {
                    val amount = userSelectedAmountEt.text.toString().toDouble() - 1
                    userSelectedAmountEt.setText(
                        if (amount < 0) "0.0" else amount.toString()
                    )
                }
            }

            buyingBtn.setOnClickListener {
                if (userSelectedAmountEt.text.toString().isEmpty())
                    Toast.makeText(
                        requireContext(),
                        "Please select an amount first",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    buyingBtn.isEnabled = false
                    loading.visibility = View.VISIBLE
                    futuresViewModel.sendAmount(userSelectedAmountEt.text.toString().toDouble())
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch(Dispatchers.Main) {
                    futuresViewModel.buyListState.collect {
                        buyListAdapter.submitList(it)
                    }
                }

                launch(Dispatchers.Main) {
                    futuresViewModel.sellListState.collect {
                        sellListAdapter.submitList(it)
                    }
                }

                launch(Dispatchers.Main) {
                    futuresViewModel.amountState.collect { amountResponse: AmountResponseUi? ->
                        amountResponse?.let {
                            Toast.makeText(requireContext(), "Id is : ${it.id}", Toast.LENGTH_LONG)
                                .show()
                        }

                        binding.buyingBtn.isEnabled = true
                        binding.loading.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}