package haedab.haedab.haedab.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import haedab.haedab.haedab.R

class LoadingDialog(context: Context) : Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        // 취소 불가능
        setCancelable(false)

    }
}