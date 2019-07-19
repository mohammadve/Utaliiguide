package com.utaliiguides.callBack

import java.io.File

interface UploadDocumentCallBack {
    fun selectedImage(imagePath: String, selectedDocFile: File, documentType: Int)
}