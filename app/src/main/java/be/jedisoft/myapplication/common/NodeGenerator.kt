package be.jedisoft.myapplication.common

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File


class NodeExecutors () {
    companion object{
        fun ManualButtonClicked(data: String, context: Context) {


                //context.grantUriPermission()
                Log.d("Maintenance", "Manual Button clicked")
                val storageLocation = context.getFilesDir().absolutePath + "/${data}.pdf"
                Log.d("Maintenance", storageLocation)
                val file = File(storageLocation)
                val prvUri = FileProvider.getUriForFile(
                    context,
                    context.packageName + ".fileprovider",
                    file
                )

                // Open file with user selected app
                val intent = Intent(Intent.ACTION_VIEW)

                intent.setDataAndType(prvUri, "application/pdf")
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                context.startActivity(Intent.createChooser(intent, "View PDF"))
            }


        }




       }



