package com.example.smarthome

class CommonResourcesData {
    var humid = ""
    var light = ""
    var Pot1 = ""
    var sound = ""
    var tempe = ""
    var ultra = ""
    var ultra2 = ""

    constructor(){

    }

    constructor(humid:String, light:String, Pot1:String, sound:String, tempe:String, ultra:String, ultra2:String){
        this.humid = humid
        this.light = light
        this.Pot1 = Pot1
        this.sound = sound
        this.tempe = tempe
        this.ultra = ultra
        this.ultra2 = ultra2
    }
}