package com.utaliiguides.models

data class TourRequestListModel(
    var id: Int,
    var guideId : Int,
    var userId : Int,
    var startdate : String,
    var enddate : String,
    var stateId : String,
    var requesttype : String,
    var requeststatus : Int,
    var poolId : String,

    var tour : TourInfoModel





)