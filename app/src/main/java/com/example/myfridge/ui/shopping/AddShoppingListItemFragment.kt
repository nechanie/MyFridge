package com.example.myfridge.ui.shopping

import android.R
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.data.database.ShoppingListItemInfo
import com.example.myfridge.databinding.AddFoodBinding
import com.example.myfridge.databinding.FragmentAddShoppingListItemBinding
import com.example.myfridge.ui.database.DatabaseViewModel

class AddShoppingListItemFragment : Fragment() {
    private val itemViewModel: DatabaseViewModel.ShoppingListItemInfoViewModel by viewModels()
    private val listViewModel: DatabaseViewModel.ShoppingListInfoViewModel by viewModels()

    private var _binding : FragmentAddShoppingListItemBinding? = null

    private val binding get() = _binding!!
    private lateinit var addName : EditText
    private lateinit var chooseList : Spinner
    private var names = mutableListOf<String>()
    private lateinit var listName : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddShoppingListItemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.title.text = "Add an Item to Shopping List"

        //Get shopping lists to pick from with a spinner
        chooseList = binding.shoppingListSpinner
        listViewModel.shoppingListInfoNamesAll.observe(viewLifecycleOwner) {
            it?.forEach() {
                names.add(it)
            }
        }

        //make an array of the strings to use in the spinner
        val stringArrAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, names
        )
        stringArrAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        chooseList.adapter = stringArrAdapter

        /*chooseList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("AddShoppingListItem", parent?.getItemAtPosition(position).toString())
                Log.d("AddShoppingListItem", names[position])
                listName = names[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //do nothing
                Log.d("AddShoppingListItem", "uhhhh")
            }
        }*/

        addName = binding.addName
        val submitButton = _binding!!.addShoppingListItemButton

        submitButton.setOnClickListener(){
            val name = addName.text.toString()
            listName = binding.shoppingListSpinner.selectedItem.toString()
            Log.d("AddShoppingListItem", listName)
            itemViewModel.addShoppingListItemInfo(ShoppingListItemInfo(name, listName))
            findNavController().navigateUp()
        }
        return root
    }
}