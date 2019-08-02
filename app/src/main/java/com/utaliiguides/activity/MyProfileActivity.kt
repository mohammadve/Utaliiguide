package com.utaliiguides.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonObject
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.helper.RealPathUtil
import com.utaliiguides.viewModel.MyProfileViewModel
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MyProfileActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    var outputFileUri: Uri? = null
    val CAMERA_REQUEST = 101
    val GALLERY_REQUEST = 102
    var imageFilePath: String = ""
    var myProfileViewModel : MyProfileViewModel?= null
    var duty : Boolean ?= null
    var preference: AppPreference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        preference = AppPreference.getInstance(this)
        setupUI()
        setupClickListener()

        setProfileData()

    }

    private fun setupUI()
    {

        myProfileViewModel = ViewModelProviders.of(this).get(MyProfileViewModel::class.java)
        iv_profile_image.setImageResource(R.mipmap.ic_profile_placeholder)





    }

    private fun setProfileData() {
        myProfileViewModel!!.setGuideProfile(this).observe(this, androidx.lifecycle.Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data") && it.get("data") is JsonObject){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.has("name")){
                        et_user_name.setText(dataObject.get("name").asString)
                    }

                    if(dataObject.has("guide_mobile_no")){
                        et_user_number.setText(dataObject.get("guide_mobile_no").asString)
                    }

                    if(dataObject.has("guide_email")){
                        et_email_id.setText(dataObject.get("guide_email").asString)
                    }

                    if(dataObject.has("guide_address")){
                        et_address.setText(dataObject.get("guide_address").asString)
                    }

                    if(dataObject.has("emry_contact")){
                        et_emergency_contact_number.setText(dataObject.get("emry_contact").asString)
                    }

                    if(dataObject.has("guid_profile_img")){

                        Glide
                            .with(this)
                            .load(dataObject.get("guid_profile_img").asString)
                            .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                            .into(iv_profile_image)
                    }


                }


            }

        })
    }


    private fun setupClickListener()
    {
        iv_backArrow.setOnClickListener(this)
        iv_image_picker.setOnClickListener(this)
        iv_editProfile_icon.setOnClickListener(this)
        tv_save.setOnClickListener(this)

        tv_edit_cover.setOnClickListener(this)

        cl_helpAndSupport.setOnClickListener(this)
        cl_documents.setOnClickListener(this)
        tv_logout.setOnClickListener(this)


        if (!preference!!.getGuideOnlineStatus().equals("")) {
            guide_online_offline_switch.isChecked = preference!!.getGuideOnlineStatus().equals("1")
        } else {
            guide_online_offline_switch.isChecked = true
        }



           guide_online_offline_switch.setOnCheckedChangeListener { p0, value ->

               if (value) {

                   myProfileViewModel!!.changeGuideDuty(this@MyProfileActivity, 1).observe(this@MyProfileActivity, androidx.lifecycle.Observer {

                       if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                           Utils.showToast(this@MyProfileActivity, it.get("message").asString)
                       //    Utils.showToast(this@MyProfileActivity, "Onlinee")
                           preference!!.setGuideOnlineStatus("1")

                       }
                   })


               } else {

                   myProfileViewModel!!.changeGuideDuty(this@MyProfileActivity, 0).observe(this@MyProfileActivity, androidx.lifecycle.Observer {

                       if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                           Utils.showToast(this@MyProfileActivity, it.get("message").asString)

                          // Utils.showToast(this@MyProfileActivity, "Offline")

                           preference!!.setGuideOnlineStatus("0")

                       }
                   })
               }
           }


    }

    fun changeViewsEditProperty(editable: Boolean) {

        if (editable) {
            et_user_name.isEnabled = true
            et_user_number.isEnabled = true
            et_email_id.isEnabled = true
            et_address.isEnabled = true
            et_emergency_contact_number.isEnabled = true

        } else {
            et_user_name.isEnabled = false
            et_user_number.isEnabled = false
            et_email_id.isEnabled = false
            et_address.isEnabled = false
            et_emergency_contact_number.isEnabled = false
        }

    }

    fun openImagePickerMenu() {
        var popUpMenu = PopupMenu(this, iv_image_picker)
        try {
            val fields = popUpMenu.javaClass.getDeclaredFields()
            for (field in fields) {
                if ("mPopup" == field.getName()) {
                    field.setAccessible(true)
                    val menuPopupHelper = field.get(popUpMenu)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.getName())
                    val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        popUpMenu.setOnMenuItemClickListener(this)
        popUpMenu.inflate(R.menu.image_picker_menu)
        popUpMenu.gravity = Gravity.START
        popUpMenu.show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.iv_backArrow -> {
                finish()
            }

            R.id.iv_image_picker -> {
                if (checkPermission()){
                    openImagePickerMenu()
                }
                else {
                    requestPermission()
                }
            }

            R.id.iv_editProfile_icon -> {
                if (tv_save.visibility == View.VISIBLE) {

                    changeViewsEditProperty(false)
                    TransitionManager.beginDelayedTransition(cl_edit)
                    tv_save.visibility = View.GONE
                } else {
                    changeViewsEditProperty(true)
                    TransitionManager.beginDelayedTransition(cl_edit)
                    tv_save.visibility = View.VISIBLE
                }
               // changeViewsEditProperty(true)
            }

            R.id.tv_save ->{
               // tv_save.visibility = View.GONE
               // changeViewsEditProperty(false)
               // TransitionManager.beginDelayedTransition(cl_edit)
                uploadProileData()
            }

            R.id.cl_helpAndSupport -> {
                val intent = Intent(this, HelpAndSupportActivity::class.java)
                startActivity(intent)
            }

            R.id.cl_documents -> {
            }

            R.id.tv_logout -> {
                logout()

            }

        }
    }




    private fun uploadProileData() {
        myProfileViewModel!!.updateProfile(this,et_user_name.text.toString(),
            et_email_id.text.toString(),
            et_user_number.text.toString(),
            et_emergency_contact_number.text.toString(),
            et_address.text.toString()
            ).observe(this, androidx.lifecycle.Observer {


            if(it != null && it.has("status") && it.get("status").asString.equals("1")){
                Utils.showToast(this, it.get("message").asString)

                if(it.has("data") && it.get("data") is JsonObject){

                    tv_save.visibility = View.GONE
                    et_user_name.isEnabled = false
                    et_user_number.isEnabled = false
                    et_email_id.isEnabled = false
                    et_address.isEnabled = false
                    et_emergency_contact_number.isEnabled = false
                }

            }

        })

    }



    private fun logout() {
        AppPreference.getInstance(this).setUserData("")
        AppPreference.getInstance(this).setId(0)
        AppPreference.getInstance(this).setAuthToken("")
        AppPreference.getInstance(this).setDeviceToken("")

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.item_camera -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    //Create a file to store the image
                    var photoFile: File? = null;
                    try {
                        photoFile = createImageFile();
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                    }
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(this,getPackageName() + ".provider", photoFile)
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST)
                    }
                }

                return true
            }
            R.id.item_gallery -> {

                try {
                    val galleryIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryIntent.type = "image/*"
                    startActivityForResult(galleryIntent, GALLERY_REQUEST)
                } catch (e: Exception) {
                    Utils.showToast(this, "No Gallery Found..")
                }

                return true
            }
        }
        return false
    }

    fun requestPermission() {

        var permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(this, permissions, 123);
    }


    fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    this,
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (grantResults.size > 0) {

            var CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            var readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            var writeExternalStorage = grantResults[2] == PackageManager.PERMISSION_GRANTED;

            if (CameraPermission && readExternalStorage && writeExternalStorage) {
                openImagePickerMenu()
            } else {
                Utils.showToast(this, getString(R.string.msg_incomplete_permission))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) run {

            try {
                //val selectedFilePath = RealPathUtil.getPath(this@ProfileActivity, uri)

                val compressedImage = compressImage(imageFilePath)


              /*  Glide
                    .with(this)
                    .load(compressedImage)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_profile_image);*/

                uploadImage(compressedImage)


            } catch (e: IOException) {
                e.printStackTrace()
            }


        } else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) run {
            val uri = data!!.getData()
            try {
                val selectedFilePath = RealPathUtil.getPath(this@MyProfileActivity, uri)

                val compressedImage = compressImage(selectedFilePath)

          /*      Glide
                    .with(this)
                    .load(compressedImage)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_profile_image);*/

                uploadImage(compressedImage)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }





    private fun uploadImage(imageUri: String) {
        pb_image.visibility = View.VISIBLE
        var file = File(imageUri)
        var requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
        var img = MultipartBody.Part.createFormData("image", file.name,requestFile)

        myProfileViewModel!!.updateProfilePic(this, img).observe(this, androidx.lifecycle.Observer {


            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){
                  Utils.showToast(this, it.get("message").asString)

                pb_image.visibility = View.GONE

                if(it.has("data") && it.get("data") is JsonObject){

                    var dataObj = it.getAsJsonObject("data")

                    if(dataObj.has("guid_profile_img")){
                Glide
                    .with(this)
                    .load(dataObj.get("guid_profile_img").asString)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_profile_image)
                    }

                }




            } else {
                pb_image.visibility = View.GONE
            }

        })





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


        var cursor = getContentResolver().query(contentUri, filePathColumn, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            var index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    fun getFilename(): String {
        var file = File(Environment.getExternalStorageDirectory().getPath(), resources.getString(R.string.app_name) + "/Images")
        if (!file.exists()) {
            file.mkdirs()
        }
        var uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg")
        return uriSting
    }

    private fun createImageFile(): File {
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date());
        var imageFileName = "IMG_" + timeStamp + "_";
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        var image = File.createTempFile(imageFileName,  /* prefix */".jpg",         /* suffix */storageDir      /* directory */);
        imageFilePath = image.getAbsolutePath();
        return image;
    }
}