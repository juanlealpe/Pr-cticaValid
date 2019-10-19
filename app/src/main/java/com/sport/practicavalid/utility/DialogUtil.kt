package com.sport.practicavalid.utility

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.sport.practicavalid.R

class DialogUtil  {

companion object {
    fun progress(activity: Activity): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        val inflater= activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_loading, activity.findViewById(android.R.id.content), false))
        return builder.create()
    }
}
}