/*Receive Midi
By Amanda Ghassaei
July 2012
<a href="https://www.instructables.com/id/Send-and-Receive-MIDI-with-Arduino/">

https://www.instructables.com/id/Send-and-Receive-...>

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

byte commandByte = 0;
byte noteByte = 0;
byte velocityByte = 0;
bool led = LOW;
void setup(){
  mySerial.begin(31250);
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
}

void checkMIDI(){
  do{
    if (mySerial.available()){
      commandByte = mySerial.read();//read first byte
      noteByte = mySerial.read();//read next byte
      velocityByte = mySerial.read();//read final byte
    }
  }
  while (mySerial.available() > 2);//when at least three bytes available
}
    

void loop(){
  checkMIDI();
  delay(20);
  if(noteByte == 12){

    if(velocityByte > 50) {
      digitalWrite(LED_BUILTIN, HIGH);
    }

    else {
      digitalWrite(LED_BUILTIN, LOW);
    }
  }
}
