package com.utaliiguides.helper

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.callBack.UploadDocumentCallBack
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ImageUtils : View.OnClickListener {

    var mContext: Activity? = null
    var mImageSelectionType: Int = 0
    var outputFileUri: Uri? = null
    val CAMERA_REQUEST = 101
    val GALLERY_REQUEST = 102
    var imageFilePath: String = ""
    var mImageSelectionListener: UploadDocumentCallBack? = null
    var imageChooserDialog: Dialog? = null

    fun selectImage(context: Activity, selectionType: Int, imageSelectionListener: UploadDocumentCallBack) {
        mContext = context
        mImageSelectionListener = imageSelectionListener
        mImageSelectionType = selectionType
        if (checkPermission()) {
            showChooseImageFromDialog()
        } else {
            requestPermission()
        }
    }

    private fun showChooseImageFromDialog() {

        imageChooserDialog = Dialog(mContext)
        imageChooserDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        imageChooserDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        imageChooserDialog!!.setContentView(R.layout.dialog_image_selection)
        imageChooserDialog!!.show()
        imageChooserDialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        var selectFromGallery = imageChooserDialog!!.findViewById<TextView>(R.id.tv_gallerySelection)
        var selectFromCamera = imageChooserDialog!!.findViewById<TextView>(R.id.tv_cameraSelection)
        var selectCancel = imageChooserDialog!!.findViewById<TextView>(R.id.tv_cancel)

        selectFromGallery.setOnClickListener(this)
        selectFromCamera.setOnClickListener(this)
        selectCancel.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.tv_gallerySelection -> {
                openGallery()
                imageChooserDialog!!.dismiss()
            }
            R.id.tv_cameraSelection -> {
                openCamera()
                imageChooserDialog!!.dismiss()
            }
            R.id.tv_cancel -> {
                imageChooserDialog!!.dismiss()
            }
        }
    }

    private fun openCamera()
    {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (cameraIntent.resolveActivity(mContext!!.getPackageManager()) != null) {
            //Create a file to store the image
            var photoFile: File? = null;
            try {
                photoFile = createImageFile();
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                outputFileUri = FileProvider.getUriForFile(mContext!!,mContext!!.getPackageName() + ".provider", photoFile)
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                mContext!!.startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
    }

    private fun openGallery() {
        try {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryIntent.type = "image/*"
            mContext!!.startActivityForResult(galleryIntent, GALLERY_REQUEST)
        } catch (e: Exception) {
            Utils.showToast(mContext!!, "No Gallery Found..")
        }
    }

    fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if ((ContextCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED)
            ) {
                return true
            } else {
                return false
            }

        } else {
            return true
        }
    }

    fun requestPermission() {

        var permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(mContext!!, permissions, 123);
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (grantResults.size > 0) {

            var CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            var readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            var writeExternalStorage = grantResults[2] == PackageManager.PERMISSION_GRANTED;

            if (CameraPermission && readExternalStorage && writeExternalStorage) {
                showChooseImageFromDialog()
            } else {
                Utils.showToast(mContext!!, mContext!!.getString(R.string.msg_incomplete_permission))
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) run {

            try {
                val compressedImage = compressImage(imageFilePath)
                var file = File(compressedImage)
                mImageSelectionListener!!.selectedImage(compressedImage, file, mImageSelectionType)

            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) run {
            val uri = data!!.getData()
            try {
                val selectedFilePath = RealPathUtil.getPath(mContext, uri)

                val compressedImage = compressImage(selectedFilePath)
                var file = File(compressedImage)
                mImageSelectionListener!!.selectedImage(compressedImage, file, mImageSelectionType)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun compressImage(imageUri: String): String {

        var filePath = getRealPathFromURI(imageUri)
        var scaledBitmap: Bitmap? = null

        var options = BitmapFactory.Options()

        //      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
        //      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(filePath, options)

        var actualHeight = options.outHeight
        var actualWidth = options.outWidth

        //      max Height and width values of the compressed image is taken as 816x612

        val maxHeight = 816.0f
        val maxWidth = 612.0f

        var imgRatio = (actualWidth / actualHeight).toFloat()


        var maxRatio = maxWidth / maxHeight

        //      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight
                actualWidth = (imgRatio * actualWidth).toInt()
                actualHeight = maxHeight.toInt()
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth
                actualHeight = (imgRatio * actualHeight).toInt()
                actualWidth = maxWidth.toInt()
            } else {
                actualHeight = maxHeight.toInt()
                actualWidth = maxWidth.toInt()

            }
        }

        //      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)

        //      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false

        //      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true
        options.inInputShareable = true
        options.inTempStorage = ByteArray(16 * 1024)

        try {
            //          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()

        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        val ratioX = actualWidth / options.outWidth.toFloat()
        val ratioY = actualHeight / options.outHeight.toFloat()
        val middleX = actualWidth / 2.0f
        val middleY = actualHeight / 2.0f

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)

        val canvas = Canvas(scaledBitmap)
        canvas.setMatrix(scaleMatrix)
        canvas.drawBitmap(bmp, middleX - bmp.width / 2, middleY - bmp.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))

        //      check the rotation of the image and display it properly
        val exif: ExifInterface
        try {
            exif = ExifInterface(filePath)

            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, 0
            )
            Log.d("EXIF", "Exif: $orientation")
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90F)
                Log.d("EXIF", "Exif: $orientation")
            } else if (orientation == 3) {
                matrix.postRotate(180F)
                Log.d("EXIF", "Exif: $orientation")
            } else if (orientation == 8) {
                matrix.postRotate(270F)
                Log.d("EXIF", "Exif: $orientation")
            }
            scaledBitmap = Bitmap.createBitmap(
                scaledBitmap, 0, 0,
                scaledBitmap!!.width, scaledBitmap.height, matrix,
                true
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var out: FileOutputStream? = null
        val filename = getFilename()
        try {
            out = FileOutputStream(filename)

            //          write the compressed bitmap at the destination specified by filename.
            scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return filename

    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        var height = options.outHeight;
        var width = options.outWidth;
        var inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            var heightRatio = Math.round((height.toFloat() / reqHeight.toFloat()))
            var widthRatio = Math.round((width.toFloat() / reqWidth.toFloat()))

            if (heightRatio < widthRatio)
                inSampleSize = heightRatio
            else
                inSampleSize = widthRatio


        }
        var totalPixels = width * height;
        var totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    fun getRealPathFromURI(contentURI: String): String {
        var contentUri = Uri.parse(contentURI);
        var filePathColumn = arrayOf(MediaStore.Images.Media.DATA)


        var cursor = mContext!!.getContentResolver().query(contentUri, filePathColumn, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            var index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    fun getFilename(): String {
        var file = File(
            Environment.getExternalStorageDirectory().getPath(),
            mContext!!.resources.getString(R.string.app_name) + "/Images"
        )
        if (!file.exists()) {
            file.mkdirs()
        }
        var uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg")
        return uriSting
    }

    private fun createImageFile(): File {
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date());
        var imageFileName = "IMG_" + timeStamp + "_";
        var storageDir = mContext!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        var image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }
}