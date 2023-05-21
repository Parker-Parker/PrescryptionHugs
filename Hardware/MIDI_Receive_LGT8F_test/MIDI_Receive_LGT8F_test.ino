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


byte commandByte = 0;
byte noteByte = 0;
byte velocityByte = 0;
bool led = LOW;
void setup(){
  Serial.begin(31250);
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
}

void checkMIDI(){
  do{
    if (Serial.available()){
      commandByte = Serial.read();//read first byte
      noteByte = Serial.read();//read next byte
      velocityByte = Serial.read();//read final byte
    }
  }
  while (Serial.available() > 2);//when at least three bytes available
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
