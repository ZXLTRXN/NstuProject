package com.zxltrxn.nstuproject.features.parsing.plan.data.model

data class Direction(
    var name: String,
    var code: String,
    var level: String,

    var budget: String = EMPTY,
    var contract: String = EMPTY,
    var quota: String = EMPTY,
    var target: String = EMPTY,
    var competitiveGroup: String = EMPTY,
    var accreditationPeriod: String = EMPTY,
    var special: String = EMPTY
){
    companion object{
        const val EMPTY = ""
    }
}
