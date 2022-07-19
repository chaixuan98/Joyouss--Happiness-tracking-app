package activity

//class object for happy activities
class Activity {
    var aID: Int = 0 //activity id
    var aName: String? = null //activity name
    var aType: Int = 0 //rank: energetic or restless

    constructor (){}

    constructor(aID:Int,aName: String, aType:Int){
        this.aID=aID
        this.aName=aName
        this.aType=aType
    }
}