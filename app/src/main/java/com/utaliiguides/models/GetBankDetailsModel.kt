package com.utaliiguides.models

import java.io.Serializable

class GetBankDetailsModel (

    var id : Int,
    var guideId : Int,
    var account_name : String,
    var account_no : String,
    var account_swift_code : String,
    var branch_no : String,
    var account_defualt : Boolean,
    var isSelected: Boolean

):Serializable