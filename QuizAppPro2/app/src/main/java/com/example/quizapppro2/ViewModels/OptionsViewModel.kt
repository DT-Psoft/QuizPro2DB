package com.example.quizapppro2.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY

class OptionsViewModel(context: Context) : ViewModel() {


    lateinit var context: Context
    private var db: AppDatabase =  AppDatabase.getAppDatabase(context)

    var configuration: User_ConfigurationETY = AppDatabase.getCurrentConfiguration()
    lateinit var user: UserETY

    var categories: Array<Boolean> = arrayOf(true,true,true,true,true,true)
    var categoriesNumber = 6
    val arrayQuestionsNumber : Array<Int> = arrayOf(5, 6, 7, 8, 9,10)
    val arrayCluesNumber = arrayOf(1, 2, 3)
    var arrayOfPointsValue = arrayOf(3,2,1)

    init{
        getCategoriesEnabled()
    }

    private fun getCategoriesEnabled(){
        categories[0] = configuration.categories_selected[0] == '1'
        categories[1] = configuration.categories_selected[1] == '1'
        categories[2] = configuration.categories_selected[2] == '1'
        categories[3] = configuration.categories_selected[3] == '1'
        categories[4] = configuration.categories_selected[4] == '1'
        categories[5] = configuration.categories_selected[5] == '1'
    }

    fun updateOptions(){
        var string = ""
        string += if(categories[0]) "1" else "0"
        string += if(categories[1]) "1" else "0"
        string += if(categories[2]) "1" else "0"
        string += if(categories[3]) "1" else "0"
        string += if(categories[4]) "1" else "0"
        string += if(categories[5]) "1" else "0"

        configuration.categories_selected = string

        db.User_ConfigurationDAO().updateConfiguration(configuration)
    }
    fun getCheckedCategory(pos:Int): Boolean {
        return categories[pos]
    }
    fun getPointsValue() = arrayOfPointsValue[configuration.dificulty]

    fun setEnabledCategory(pos:Int, value: Boolean){
        categories[pos]= value
        if(value){
            if(categoriesNumber < 6){
                categoriesNumber++
            }
        }
        else{
            categoriesNumber--
        }
    }

}
