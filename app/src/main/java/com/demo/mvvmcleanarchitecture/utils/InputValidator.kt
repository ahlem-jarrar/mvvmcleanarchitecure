/*
 * *
 *  * Created by Yosra brahem on 2/4/20 3:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/4/20 3:03 PM
 *
 */

package com.proxym.quarantracking.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern


object InputValidator {

    fun validateemail(context: Context, layout: TextInputEditText, input:String, specialerror:Int) : String
    {
        // var  emailstring : String  = email.getText().toString()
        val EMAIL_REGEX = "^\"[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+\""
        val pattern = Patterns.EMAIL_ADDRESS
        if (TextUtils.isEmpty(input)) {
          //  layout.error = context.getString(R.string.input_error_required)
            return ""

        } else if (!pattern.matcher(input).matches()) {
            layout.error = context.getString(specialerror)
            return ""
        }

        return input
    }


    fun validatPassword(context: Context,layout: TextInputEditText, input:String) : String{
        val PASSWORD_REGEX = "^(?=.*[\\w])(?=.*[\\W])[\\w\\W]{8,}\$"

        val pattern = Pattern.compile(PASSWORD_REGEX)

        if (TextUtils.isEmpty(input)) {
           // layout.error = context.getString(R.string.input_error_required)
            return ""
        }
        /*      else if(input.length<8){
                  layout.error = context.getString(R.string.error_password_length)
              }

              else if (!pattern.matcher(input).matches()) {
                  layout.error = context.getString(R.string.error_password_invalide)
                  return ""
              }
      */
        return input
    }



    fun validategeneralInputwithlength(context: Context, layout: TextInputEditText, input:String, specialerror:Int,maxlengthInput: Int,minlengthInput: Int) : String
    {
        if (TextUtils.isEmpty(input)) {
            //layout.error = context.getString(R.string.input_error_required)
            return ""
        } else if (input.length<minlengthInput||input.length>maxlengthInput) {
            layout.error = context.getString(specialerror)
            return ""
        }

        return input
    }


    fun validategeneralInput(context: Context, layout: TextInputEditText, input:String) : String
    {
        if (TextUtils.isEmpty(input)) {
           // layout.error = context.getString(R.string.input_error_required)
            return ""
        }

        return input
    }





}
