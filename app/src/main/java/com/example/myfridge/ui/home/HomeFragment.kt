package com.example.myfridge.ui.home

import android.content.ClipData.Item
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.fridge.FridgeContent
import com.example.myfridge.databinding.FragmentHomeBinding
import com.example.myfridge.ui.database.DatabaseViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeRv: RecyclerView
    private val viewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater){
                menuInflater.inflate(R.menu.main_activity, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.action_add_fridge_item -> {
                        val destination = HomeFragmentDirections.actionNavHomeToAddFoodFragment()
                        findNavController().navigate(destination)
                        true
                    }
                    else -> (activity as AppCompatActivity).onOptionsItemSelected(menuItem)
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        homeRv = binding.rvHome
        homeRv.layoutManager = LinearLayoutManager(container?.context)
        homeAdapter = HomeAdapter()
        homeRv.adapter = homeAdapter
        viewModel.fridgeItemInfoAll.observe(viewLifecycleOwner) {
            homeAdapter.updateHomeList(FridgeContent(it!!))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}