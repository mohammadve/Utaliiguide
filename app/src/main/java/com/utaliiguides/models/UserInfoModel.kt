package com.utaliiguides.models

class UserInfoModel (

    var id : Int,
    var u_name : String = "",
    var mobile_no : String = "",
    var verification_code : String ="",
    var is_verified : Boolean,
    var u_email : String,
    var password : String,
    var dob : String,
    var gender : String,
    var profile_img : String,
    var payment : String

)