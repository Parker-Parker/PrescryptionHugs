/*
MIDI On Messages with 0 velocity
By Amanda Ghassaei
July 2012
https://www.instructables.com/id/Send-and-Receive-MIDI-with-Arduino/

 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.

*/

#include <SoftwareSerial.h>
// Definitions
#define rxPin 5
#define txPin 6

SoftwareSerial mySerial(rxPin, txPin);
void MIDImessage(int command, int MIDInote, int MIDIvelocity);

 
 int velocity = 100;//velocity of MIDI notes, must be between 0 and 127
 //(higher velocity usually makes MIDI instruments louder)
 //int note = 50; 
 int note = 12; 
 int noteON = 144;//144 = 10010000 in binary, note on command

void setup() {
  //  Set MIDI baud rate:
  //mySerial.begin(9600);
  mySerial.begin(31250);
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
}

void loop() {
    MIDImessage(noteON, note, velocity);//turn note on
    delay(700);//hold note for 700ms
    digitalWrite(LED_BUILTIN, HIGH);
    velocity += 10;
    velocity %= 110;
    delay(100);//hold note for 700ms
    digitalWrite(LED_BUILTIN, LOW);
    
}

//send MIDI message
void MIDImessage(int command, int MIDInote, int MIDIvelocity) {
  mySerial.write(command);//send note on or note off command 
  mySerial.write(MIDInote);//send pitch data
  mySerial.write(MIDIvelocity);//send velocity data
}
