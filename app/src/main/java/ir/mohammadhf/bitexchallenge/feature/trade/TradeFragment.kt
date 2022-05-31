package ir.mohammadhf.bitexchallenge.feature.trade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ir.mohammadhf.bitexchallenge.R
import ir.mohammadhf.bitexchallenge.databinding.FragmentTradeBinding
import ir.mohammadhf.bitexchallenge.feature.exchange.ExchangeFragment
import ir.mohammadhf.bitexchallenge.feature.futures.FuturesFragment
import ir.mohammadhf.bitexchallenge.feature.margin.MarginFragment

class TradeFragment : Fragment() {
    private var _binding: FragmentTradeBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTradeBinding.inflate(inflater, container, false)

        binding.run {
            tradePager.adapter = TradeFragmentsAdapter(this@TradeFragment)
            TabLayoutMediator(tradeTabs, tradePager) { tab, position ->
                tab.text = when(position) {
                    0 -> getString(R.string.fragment_exchange)
                    1 -> getString(R.string.fragment_margin)
                    else -> getString(R.string.fragment_futures)
                }
            }.attach()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class TradeFragmentsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> ExchangeFragment()
            1 -> MarginFragment()
            else -> FuturesFragment()
        }
        return fragment
    }
}