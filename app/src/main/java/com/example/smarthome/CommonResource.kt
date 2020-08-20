package com.example.smarthome

class CommonResource {
    var buzzer = ""
    var camera = ""
    var lcd = ""
    var lcdtext = ""
    var led = ""
    var relay = ""

    constructor(){

    }

    constructor(buzzer:String, camera:String, lcd:String, lcdtext:String, led:String, relay:String){
        this.buzzer = buzzer
        this.camera = camera
        this.lcd = lcd
        this.lcdtext = lcdtext
        this.led = led
        this.relay = relay
    }
}