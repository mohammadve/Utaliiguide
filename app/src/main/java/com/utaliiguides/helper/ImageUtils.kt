package com.utaliiguides.helper

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.view.Window
import android.widget.TextView
import com.utaliiguides.R

class ImageUtils {

    var mContext: Context? = null
    var mImageSelectionType: Int = 0
    var outputFileUri: Uri? = null
    val CAMERA_REQUEST = 101
    val GALLERY_REQUEST = 102
    var imageFilePath: String = ""

    fun selectImage(context: Context, selectionType: Int)
    {
        mContext = context
        mImageSelectionType = selectionType
        showChooseImageFromDialog()
    }

    private fun showChooseImageFromDialog()
    {
//        private fun onOpenDialog() {
//            requestSendDialog = Dialog(this)
//            requestSendDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            requestSendDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            requestSendDialog!!.setContentView(R.layout.dialog_request_sent_to_guide)
//            requestSendDialog!!.show()
//            requestSendDialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            var tvOKAY = requestSendDialog!!.findViewById<TextView>(R.id.tv_OKAY)
//
//            tvOKAY.setOnClickListener {
//
//                cl_requestStatus_cancle_pending_accept.visibility = View.VISIBLE
//                cl_requesType_Pool_Private.visibility = View.GONE
//
//                requestSendDialog!!.dismiss()
//            }
//        }
//        val dialog = Dialog(mContext)
//        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog .setCancelable(false)
//        dialog .setContentView(R.layout.dialog_image_selection)
//        val galleryBtn = dialog .findViewById(R.id.yesBtn) as TextView
//        val cameraBtn = dialog .findViewById(R.id.noBtn) as TextView
//        val cancelBtn = dialog .findViewById(R.id.noBtn) as TextView
//        galleryBtn.setOnClickListener {
//            dialog .dismiss()
//        }
//        cameraBtn.setOnClickListener {
//            dialog .dismiss()
//        }
//        cancelBtn.setOnClickListener{
//            dialog .dismiss()
//        }
//        dialog .show()
    }
}