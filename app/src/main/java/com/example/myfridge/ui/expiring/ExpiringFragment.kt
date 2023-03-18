package com.example.myfridge.ui.expiring

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.databinding.FragmentExpiringBinding

class ExpiringFragment: Fragment() {
    private var _binding: FragmentExpiringBinding? = null
    private lateinit var expiringAdapter: ExpiringAdapter
    private lateinit var expiringRv: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val expiringViewModel =
            ViewModelProvider(this).get(ExpiringViewModel::class.java)

        _binding = FragmentExpiringBinding.inflate(inflater, container, false)
        val root: View = binding.root

        expiringRv = binding.rvExpiring
        expiringRv.layoutManager = LinearLayoutManager(container?.context)
        expiringAdapter = ExpiringAdapter()
        expiringRv.adapter = expiringAdapter
        expiringViewModel.expiringContent.observe(viewLifecycleOwner){
            expiringAdapter.updateExpiringList(it)
        }
        expiringViewModel.loadExpiringContent()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}