package com.example.spinnerautomulticompletetextview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val role = mutableListOf(
        "Должность",
        "Фармацевт",
        "Санитарка",
        "Консультант",
        "Заведующая"
    )
    var employees:MutableList<Employee> = mutableListOf()
    private var listAdapter: ListAdapter? = null

    private lateinit var toolbarMain:Toolbar
    private lateinit var spinner:Spinner
    private lateinit var listViewLV:ListView
    private lateinit var saveBTN: Button
    private lateinit var nameET: EditText
    private lateinit var secondNameET: EditText
    private lateinit var ageET: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner = findViewById(R.id.spinner)
        toolbarMain = findViewById(R.id.toolbarMain)
        listViewLV = findViewById(R.id.listViewLV)
        saveBTN = findViewById(R.id.saveBTN)
        nameET = findViewById(R.id.nameET)
        secondNameET = findViewById(R.id.secondNameET)
        ageET = findViewById(R.id.ageET)

        setSupportActionBar(toolbarMain)
        title = "Подбор персонала"
        toolbarMain.setLogo(R.drawable.ic_person)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            role
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        listViewLV.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            val note = listAdapter!!.getItem(position)
            Snackbar.make(v,"Подтвердить удаление", Snackbar.LENGTH_LONG)
                .setAction("Удалить") {
                    listAdapter!!.remove(note)
                    Snackbar.make(v, "Данные удалены", Snackbar.LENGTH_LONG).show()
                }.show()
        }

        saveBTN.setOnClickListener {
            if (nameET.text.isEmpty() || ageET.text.isEmpty() || secondNameET.text.isEmpty() || spinner.selectedItem == "Должность") {
                Toast.makeText(
                    applicationContext,
                    "Заполнены не все поля",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val employee = Employee(nameET.text.toString(), secondNameET.text.toString(), ageET.text.toString(),
                spinner.selectedItem.toString()
            )
            employees.add(employee)
            listAdapter = ListAdapter(this@MainActivity, employees)
            listViewLV.adapter = listAdapter
            listAdapter?.notifyDataSetChanged()

            nameET.text.clear()
            secondNameET.text.clear()
            ageET.text.clear()
            spinner.setSelection(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.exitMainMenu -> {
                finishAffinity()
                Toast.makeText(
                    applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}