package com.example.smarthome

class CommonResource {
    var buzzer = 0
    var camera = 0
    var lcd = 0
    var lcdtext = ""
    var led = 0
    var relay = 0

    constructor(buzzer:Int, camera:Int, lcd:Int, lcdtext:String, led:Int, relay:Int){
        this.buzzer = buzzer
        this.camera = camera
        this.lcd = lcd
        this.lcdtext = lcdtext
        this.led = led
        this.relay = relay
    }
}