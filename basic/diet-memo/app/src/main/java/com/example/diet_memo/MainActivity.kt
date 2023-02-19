package com.example.diet_memo

import android.app.DatePickerDialog
import android.icu.util.GregorianCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var database : FirebaseDatabase
    private val dataModelList = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database
        val myRef = database.getReference("myMemo")
            .child(Firebase.auth.currentUser!!.uid)

        val listView = findViewById<ListView>(R.id.mainLV)
        val adapter = ListViewAdapter(dataModelList)

        listView.adapter = adapter

        // database에서 값 불러오기
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataModelList.clear()

                snapshot.children.forEach {
                    Log.d("Data", it.toString())
                    dataModelList.add(it.getValue(DataModel::class.java)!!)
                }

                adapter.notifyDataSetChanged()
                Log.d("DataModelList", dataModelList.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        // 다이얼로그 띄우기 & DB 저장
        val writeButton = findViewById<ImageView>(R.id.writeBtn)

        writeButton.setOnClickListener {
            // Dialog 띄우기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("운동 메모 다이얼로그")

            val mAlertDialog = mBuilder.show()
            val dataSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)
            var dateText : String = ""

            dataSelectBtn?.findViewById<Button>(R.id.dateSelectBtn)
                ?.setOnClickListener {

                    val today = GregorianCalendar()
                    val year : Int = today.get(Calendar.YEAR)
                    val month : Int = today.get(Calendar.MONTH)
                    val date : Int = today.get(Calendar.DATE)

                    // 날짜 선택 다이얼로그
                    val dlg = DatePickerDialog(this, { p0, p1, p2, p3 ->
                        dateText = "${year}년 ${month + 1}월 ${date}일"

                        Log.d("[MainActivity]", dateText)
                        dataSelectBtn.text = dateText

                    }, year, month, date)

                    dlg.show()
                }

            // 저장하기 버튼 클릭 시 database에 저장 (firebase realtime database)
            val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)
            saveBtn?.setOnClickListener {

                val healthMemo = mAlertDialog.findViewById<EditText>(R.id.healthMemo)?.text.toString()
                val model = DataModel(
                    dateText = dateText,
                    memo = healthMemo
                )

                myRef.push().setValue(model)
                mAlertDialog.dismiss()     // 저장하기 버튼 클릭시 다이얼로그 닫힘
            }
        }
    }
}