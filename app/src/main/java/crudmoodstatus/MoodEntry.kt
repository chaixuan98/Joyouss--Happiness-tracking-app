package crudmoodstatus

/*Class : MoodEntry*/

class MoodEntry {
    var entryID : Int = 0
    var entryTime : String = ""
    var entryDate : String = ""
    var entryMood : Int = 0
    var entryActivityStatus : String = ""

    constructor(entryTime:String,entryDate:String,entryMood:Int,entryActivityStatus:String){
        this.entryTime = entryTime
        this.entryDate = entryDate
        this.entryMood = entryMood
        this.entryActivityStatus = entryActivityStatus
    }

    constructor(){
    }
}