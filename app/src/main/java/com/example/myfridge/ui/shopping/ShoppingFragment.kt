package com.example.myfridge.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.databinding.FragmentShoppingBinding
import com.example.myfridge.ui.home.HomeAdapter
import com.example.myfridge.ui.recipes.ShoppingAdapter
import com.kennyc.view.MultiStateView

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    private lateinit var shoppingAdapter: ShoppingAdapter
    private lateinit var shoppingRv: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shoppingViewModel =
            ViewModelProvider(this).get(ShoppingViewModel::class.java)

        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        shoppingRv = binding.rvShopping
        shoppingRv.layoutManager = LinearLayoutManager(container?.context)
        shoppingAdapter = ShoppingAdapter()
        shoppingRv.adapter = shoppingAdapter
        shoppingViewModel.shoppingList.observe(viewLifecycleOwner) {
            binding.multiStateView.viewState = MultiStateView.ViewState.CONTENT
            shoppingAdapter.updateShoppingList(it)
        }
        binding.multiStateView.viewState = MultiStateView.ViewState.LOADING
        shoppingViewModel.loadShoppingList()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}