package com.example.imarticus_class_3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.imarticus_class_3.database.ItemDao
import com.example.imarticus_class_3.database.item
import com.example.imarticus_class_3.database.itemRoomDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dao: ItemDao
    private var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }*/
    var database= itemRoomDB.getdatabase(this)
        dao=database.itemDao()
    }

    fun insert(view: View) {
        GlobalScope.launch {
            val data=item(id,itemName = "name", itemPrice = 11.0, quantityInStock = 10)//item(1,"name",11.0,10)
            dao.insert(data)
            id++
        }
    }
}