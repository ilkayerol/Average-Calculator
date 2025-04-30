package com.example.averagecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val CLASSES = arrayOf("Calculus", "Physics", "C++", "Algorithms", "Programming Lab")
    private var allClassInfos: ArrayList<Classes> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnAddNewClass: ImageButton = findViewById(R.id.btnAddNewClass)
        val mainScrollView: ScrollView = findViewById(R.id.mainScrollView)
        val rootLayout: LinearLayout = findViewById(R.id.rootLayout)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)

        var etClassName: AutoCompleteTextView = findViewById(R.id.etClassName)
        val spnClassCredit: Spinner = findViewById(R.id.spnClassCredit)
        val spnClassPoint: Spinner = findViewById(R.id.spnClassPoint)

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,CLASSES)
        etClassName.setAdapter(adapter)

        if(rootLayout.childCount == 0){btnCalculate.visibility=View.INVISIBLE}

        btnAddNewClass.setOnClickListener {
            if(!etClassName.text.isNullOrEmpty()){

                //var inf2 = layoutInflater
                //var inf3 = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater ->typeCasting
                val inf = LayoutInflater.from(this)
                val newClassView = inf.inflate(R.layout.new_class_layout,null)

                val newClassName: EditText = newClassView.findViewById(R.id.etNewClassName)
                val newClassCredit: Spinner = newClassView.findViewById(R.id.spnNewClassCredit)
                val newClassPoint: Spinner = newClassView.findViewById(R.id.spnNewClassPoint)
                val btnDeleteClass: ImageButton = newClassView.findViewById(R.id.btnNewAddNewClass)

                // take the user input values from static section
                var className = etClassName.text.toString()
                var classCredit = spnClassCredit.selectedItem.toString()
                var classPoint = spnClassPoint.selectedItem.toString()

                //set the new static view with user inputs
                newClassName.setText(className)
                newClassCredit.setSelection(findSpinnerIndex(spnClassCredit, classCredit))
                newClassPoint.setSelection(findSpinnerIndex(spnClassPoint,classPoint))

                btnDeleteClass.setOnClickListener {
                    rootLayout.removeView(newClassView)
                    if(rootLayout.childCount == 0){btnCalculate.visibility=View.INVISIBLE}
                }


                rootLayout.addView(newClassView)
                if(rootLayout.childCount != 0){btnCalculate.visibility=View.VISIBLE}

                // Reset all selected values
                etClassName.text.clear()
                spnClassCredit.setSelection(0)
                spnClassPoint.setSelection(0)
                //

            }else{
                Toast.makeText(this, "Please write name of class.", Toast.LENGTH_SHORT).show()
            }

            btnCalculate.setOnClickListener {
                ortalamaHesapla(rootLayout)
            }
        }
    }
    fun findSpinnerIndex(spinner: Spinner, value: String): Int{
        var index = 33
        for(i in 0..spinner.count){
            if(spinner.getItemAtPosition(i).toString().equals(value)){
                index = i
                break
            }
        }
        return index;
    }

    fun ortalamaHesapla(rootLayout: LinearLayout){
        var sumPoints = 0.0
        var sumCredits = 0.0

        for(i in 0..rootLayout.childCount -1){
            var oneLine = rootLayout.getChildAt(i) //oneline is every child of linearlayout
            var newClassName = oneLine.findViewById<EditText>(R.id.etNewClassName).toString()
            var newClassCredit = ((oneLine.findViewById<Spinner>(R.id.spnNewClassCredit).selectedItemPosition)+1).toString()
            var newClassPoint = oneLine.findViewById<Spinner>(R.id.spnNewClassPoint).selectedItem.toString()

            var tempClass = Classes(newClassName,newClassCredit,newClassPoint)
            allClassInfos.add(tempClass)
        }

        for(currentClass in allClassInfos){
            sumPoints += creditToPointConverter(currentClass.classAlphabeticNote) * (currentClass.classCredit.toDouble())
            sumCredits += currentClass.classCredit.toDouble()
        }

        Toast.makeText(this, "${sumPoints / sumCredits}", Toast.LENGTH_SHORT).show()
        allClassInfos.clear()
        //return sumPoints / sumCredits

    }

    fun creditToPointConverter(str:String) : Double {
        var point = 0.0
        when(str){
            "AA" -> point = 4.0
            "BA" -> point = 3.5
            "BB" -> point = 3.0
            "BC" -> point = 2.5
            "CC" -> point = 2.0
            "DC" -> point = 1.5
            "DD" -> point = 1.0
            "FF" -> point = 0.0
        }
        return point
    }
}