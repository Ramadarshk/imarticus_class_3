package com.example.imarticus_class_3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imarticus_class_3.database.ItemDao
import com.example.imarticus_class_3.database.item
import com.example.imarticus_class_3.database.itemRoomDB
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var dao: ItemDao
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    //private var id=0
    private lateinit var itemlist:ArrayList<item>
    private lateinit var  viewModel: MianViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }*/
        itemlist=ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(MianViewModel::class.java)
        viewModel._second.observe(this,secObserver)
        textView = findViewById(R.id.textView)
        textView.text = viewModel.count.toString()
        val database = itemRoomDB.getdatabase(this)
        dao = database.itemDao()
        val button4: Button = findViewById(R.id.button4)
        val getthem: Button = findViewById(R.id.getThem)
        val deteteAll: Button = findViewById(R.id.deleteThem)
        getthem.setOnClickListener {
            getAll1()
        }
        deteteAll.setOnClickListener {
            deleteAll()
        }
        button4.setOnClickListener {
            viewModel.startTimer()
            //val textView=findViewById<TextView>(R.id.textView)
            textView.text=viewModel._second.toString()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun deleteAll() {
        GlobalScope.launch {
            Log.d("item","start deleting...")
            dao.deleteAll()
        }
        Log.d("item","deleted")
        viewModel.count=0
    }

    val secObserver: Observer<Int> = object : Observer<Int> {
        override fun onChanged(value: Int) {
            textView.text=value.toString()
        }

    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getAll1() {
        lifecycleScope.launch {
            Log.i("item","start getting...")
            val items = dao.getItems()
                withContext(Dispatchers.Main) {
                    items.collect {
                        Log.i("item","clear")
                        itemlist.clear()
                        Log.i("items","$it")
                        itemlist.addAll(it)
                        recyclerView.adapter = adapter(itemlist)
                        textView.text=itemlist.size.toString()
                }
                    Log.i("item","$itemlist")
                   }
                Log.i("item","got")
        }
        Log.d("item","get")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun insert(view: View) {
        GlobalScope.launch {

            val data=item(id=0,itemName = "name", itemPrice = (100..90000).random().toDouble(), quantityInStock = (1..90).random())//item(1,"name",11.0,10)
            dao.insert(data)
            //id++
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getitem(view: View) {
        GlobalScope.launch {
            val item =dao.getitem(1).first()
            withContext(Dispatchers.Main){
                val textView=findViewById<TextView>(R.id.textView)
                textView.text=item.itemName
                Log.d("item",item.itemName)
            }
        }
    }
}