package com.example.mustaqil_ish


import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.example.contactlistapp.databinding.DialogAddContactBinding

class MainActivity : AppCompatActivity() {

    private val contactList = mutableListOf<Contact>()
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        contactAdapter = ContactAdapter(contactList)
        binding.recyclerView.adapter = contactAdapter

        binding.fab.setOnClickListener { showAddContactDialog() }
    }

    private fun showAddContactDialog() {
        val dialogBinding = DialogAddContactBinding.inflate(LayoutInflater.from(this))

        AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val name = dialogBinding.editTextName.text.toString()
                val phone = dialogBinding.editTextPhone.text.toString()
                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    contactList.add(Contact(name, phone))
                    contactAdapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}