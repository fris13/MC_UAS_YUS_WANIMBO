package com.example.mcyus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mcyus.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var bolaRecyclerView: RecyclerView

    private lateinit var  bAdapter: BolaAdapter

    private lateinit var listBola: MutableList<DataBola>
    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        bolaRecyclerView = findViewById(R.id.hasil)

        bolaRecyclerView.setHasFixedSize(true)
        bolaRecyclerView.layoutManager = LinearLayoutManager(this@Home)
        binding.myDataLoaderprogressBar.visibility = View.VISIBLE

        listBola = ArrayList()
        bAdapter = BolaAdapter(this@Home,listBola)
        bolaRecyclerView.adapter = bAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("dBola")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener{

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Home,error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderprogressBar.visibility = View.VISIBLE
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                listBola.clear()
                for (teacherSnapshot in snapshot.children){
                    val  upload = teacherSnapshot.getValue(DataBola::class.java)
                    upload!!.key = teacherSnapshot.key
                    listBola.add(upload)
                }
                bAdapter.notifyDataSetChanged()
                binding.myDataLoaderprogressBar.visibility = View.GONE
            }

        })

        binding.imgout.setOnClickListener {
            firebaseAuth.signOut()

            Intent(this,  MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }


    }
}