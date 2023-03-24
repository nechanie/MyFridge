package com.example.myfridge.ui.fridge

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.data.fridge.FridgeContent
import com.example.myfridge.databinding.FragmentRvBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import com.kennyc.view.MultiStateView
import java.time.LocalDateTime
import java.time.ZoneOffset

class ExpiringFragment: Fragment() {
    private var _binding: FragmentRvBinding? = null
    private lateinit var expiringAdapter: FridgeAdapter
    private lateinit var expiringRv: RecyclerView
    private val viewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRvBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val prefs = PreferenceManager.getDefaultSharedPreferences((activity as AppCompatActivity))
        val prefDays = prefs.getString((activity as AppCompatActivity).getString(R.string.pref_expiration_key), "7")
        val expDate = LocalDateTime.now().plusDays(Integer.parseInt(prefDays!!).toLong()).toInstant(
            ZoneOffset.UTC).toEpochMilli()
        expiringRv = binding.rv
        expiringRv.layoutManager = LinearLayoutManager(container?.context)
        expiringAdapter = FridgeAdapter(::onShopClick, ::onDeleteClick)
        expiringRv.adapter = expiringAdapter
        viewModel.getExpiringSoon(expDate).observe(viewLifecycleOwner){
            binding.multiStateView.viewState = MultiStateView.ViewState.CONTENT
            expiringAdapter.updateFridgeList(FridgeContent(it.orEmpty()))
        }
        binding.multiStateView.viewState = MultiStateView.ViewState.LOADING
        return root
    }

    fun onShopClick(name: String){
        val destination = HomeFragmentDirections.actionNavHomeToAddShoppingFragment(name)
        val navController = findNavController()
        navController.navigate(destination)
    }

    fun onDeleteClick(name:String) {
        viewModel.deleteFridgeItemInfo(FridgeItemInfo(null, name, null))
        val navController = findNavController()
        navController.navigate(R.id.nav_expiring)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}