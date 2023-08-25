#include <Servo.h>
#include <Wire.h>

int ServoAddress = 0x0C; // Servo Controller Hex Address

byte ServoHoldingRegister[8];

float Axis_A = 1500;
float Axis_A1 = 1500;
float Axis_B = 1500;
float Axis_C = 1500;
float Axis_D = 1500;
float Axis_E = 1500;
int Function;

float Joystick_A;
float Joystick_B;
float Joystick_C;
float Joystick_D;
float Joystick_E;

int SaveState = 1;
int Save;

float ZeroCal_A = 0;
float ZeroCal_B = 0;
float ZeroCal_C = 0;
float ZeroCal_D = 0;
float ZeroCal_E = 0;

int pot1;
int pot1A;
int pot2;
int pot3;
int pot4;
int pot5;


float pot1Scaled;
float pot1AScaled;
float pot2Scaled;
float pot3Scaled;
float pot4Scaled;
float pot5Scaled;


float pot1Smoothed;
float pot1ASmoothed;
float pot2Smoothed;
float pot3Smoothed;
float pot4Smoothed;
float pot5Smoothed;

float pot1SmoothedPrev;
float pot1ASmoothedPrev;
float pot2SmoothedPrev;
float pot3SmoothedPrev;
float pot4SmoothedPrev;
float pot5SmoothedPrev;

Servo servo1; //Yes 1
Servo servo2; //Yes 2
Servo servo3; //So So 1

Servo servo4; //No 1
Servo servo5; //Eye Up/Down
Servo servo6; //Eye Left/Right



void setup() {
  Serial.begin(9600);
                  Serial.print("  serial Enabled ");
  Wire.begin(ServoAddress);
  Wire.setClock(100000);

  Wire.onReceive(receiveEvent);

  servo1.attach(4);
  servo2.attach(5);
  servo3.attach(6);
  
  servo4.attach(7);
  servo5.attach(8);
  servo6.attach(9);

}

void loop() {

if ((Joystick_A > ZeroCal_A + 10) && (Axis_A > 600)) {
      Axis_A = Axis_A - 5;
      Axis_A1 = Axis_A1 + 5;
  }
  if ((Joystick_A < ZeroCal_A - 10) && (Axis_A < 1600)) {
      Axis_A = Axis_A + 5;
      Axis_A1 = Axis_A1 - 5;
  }
if ((Joystick_B > ZeroCal_B + 10) && (Axis_B < 1700)) {
      Axis_B = Axis_B + 5;
  }
  if ((Joystick_B < ZeroCal_B - 10) && (Axis_B > 1200)) {
      Axis_B = Axis_B - 5;
  }
if ((Joystick_C > ZeroCal_C + 10) && (Axis_C < 1800)) {
      Axis_C = Axis_C + 1;
  }
  if ((Joystick_C < ZeroCal_C - 10) && (Axis_C > 1400)) {
      Axis_C = Axis_C - 1;
  }
if ((Joystick_D > ZeroCal_D + 10) && (Axis_D < 1700)) {
      Axis_D = Axis_D + 5;
  }
  if ((Joystick_D < ZeroCal_D - 10) && (Axis_D > 1300)) {
      Axis_D = Axis_D - 5;
  }
if ((Joystick_E > ZeroCal_E + 10) && (Axis_E < 1700)) {
      Axis_E = Axis_E + 5;
  }
  if ((Joystick_E < ZeroCal_E - 10) && (Axis_E > 1300)) {
      Axis_E = Axis_E - 5;
  }
  
  pot1 = Axis_A;
  pot1A = Axis_A1;
  pot2 = Axis_B;
  pot3 = Axis_C;
  pot4 = Axis_D;
  pot5 = Axis_E; 

  // smooth pots
  
  pot1Smoothed = (pot1 * 0.05) + (pot1SmoothedPrev * 0.95);
  pot1ASmoothed = (pot1A * 0.05) + (pot1ASmoothedPrev * 0.95);
  //pot2Smoothed = (pot2 * 0.02) + (pot2SmoothedPrev * 0.98);
  pot2Smoothed = pot2;
  pot3Smoothed = (pot3 * 0.02) + (pot3SmoothedPrev * 0.98);
  pot4Smoothed = (pot4 * 0.05) + (pot4SmoothedPrev * 0.95);
  pot5Smoothed = (pot5 * 0.02) + (pot5SmoothedPrev * 0.98);

  // bookmark previous values

  pot1SmoothedPrev = pot1Smoothed;
  pot1ASmoothedPrev = pot1ASmoothed;
  pot2SmoothedPrev = pot2Smoothed;
  pot3SmoothedPrev = pot3Smoothed;
  pot4SmoothedPrev = pot4Smoothed;
  pot5SmoothedPrev = pot5Smoothed;
/*
  Serial.print(pot1Smoothed);
  Serial.print(" , ");
  Serial.print(pot1ASmoothed);
  Serial.print(" , ");
  Serial.print(pot2Smoothed);
  Serial.print(" , ");
  Serial.print(pot3Smoothed);
  Serial.print(" , ");
  Serial.print(pot4Smoothed);
  Serial.print(" , ");
  Serial.println(pot5Smoothed);
*/
  // write servos

  servo1.writeMicroseconds(pot1Smoothed);
  servo2.writeMicroseconds(pot1ASmoothed);
  
  servo3.writeMicroseconds(pot2Smoothed);
  servo4.writeMicroseconds(pot3Smoothed);
  
  servo5.writeMicroseconds(pot4Smoothed);
  servo6.writeMicroseconds(pot5Smoothed);

  

  delay(5);                      // run loop 200 times/second

}

void receiveEvent(int howmany) {
       // Serial.println("  Debug: Data Recieved ");
       //         Serial.print("  Debug: Recieved Command was ");
  for(int ia=0; ia<howmany; ia++)
  {
    ServoHoldingRegister[ia] = Wire.read();
       // Serial.print(ServoHoldingRegister[ia]);
      //  Serial.print(", ");  
  }
  if (SaveState == 1) {
    ZeroCal_A = ServoHoldingRegister[1] * 10;
    ZeroCal_B = ServoHoldingRegister[2] * 10;
    ZeroCal_C = ServoHoldingRegister[3] * 10;
    ZeroCal_D = ServoHoldingRegister[4] * 10;
    ZeroCal_E = ServoHoldingRegister[5] * 10;
    SaveState = 0;
  }
  Joystick_A = ServoHoldingRegister[1] * 10;
  Joystick_B = ServoHoldingRegister[2] * 10;
  Joystick_C = ServoHoldingRegister[3] * 10;
  Joystick_D = ServoHoldingRegister[4] * 10;
  Joystick_E = ServoHoldingRegister[5] * 10;
  Function = ServoHoldingRegister[6]; 
}
