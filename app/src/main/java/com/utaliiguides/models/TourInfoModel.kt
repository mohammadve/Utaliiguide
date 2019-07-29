package com.utaliiguides.models

data class TourInfoModel (
    var id : Int,
    var guideId : Int,
    var UserId : Int,
    var tourReqId : Int,
    var tourStartdate : String,
    var tourEnddate : String,
    var tourtype : Int,
    var tourcost : String,
    var tourdays : Int,
    var tourStatus : Int,
    var tourdates : String,
    var tour_mem_id : String = "",
    var TourNotes : String = "",
    var no_of_members : String = "",
    var user : UserInfoModel




)