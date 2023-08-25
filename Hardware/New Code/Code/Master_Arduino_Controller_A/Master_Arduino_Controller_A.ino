#include <Wire.h>
#include <Adafruit_SSD1306.h> //contains graphix for Local Debug Display
#include <Adafruit_GFX.h>

#define SCREEN_WIDTH 128 // OLED display width, in pixels
#define SCREEN_HEIGHT 64 // OLED display height, in pixels

#define OLED_RESET     4 // Reset pin # for OLED Display
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RESET);

byte LEyeHoldingRegister[5];
byte REyeHoldingRegister[5];
byte ServoHoldingRegister[8];

int DebugScreenAddress = 0x3C; // Local Display Defulat Hex Address
int LEyeAddress = 0x0A; // Left Eye Controller Hex Address
int REyeAddress = 0x0B; // Right Eye Controller Hex Address
int ServoAddress = 0x0C; // Servo Controller Hex Address
byte slaveAddress[] = {0x0A,0x0B,0x0C}; // Left Eye Controller, Right Eye Controller, Servo Controller

byte NumberOfSlaves = 3;

int Value_A = 0;
int Value_B = 0;
int Value_C = 0;
int Value_D = 0;
int Value_E = 0;
int Value_F = 0;
int Blink = 0;

int Head_Axis_A = A0;
int Head_Axis_B = A1;
int Head_Axis_C = A2;

int Eye_Axis_D = A3;
int Eye_Axis_E = A4;

int Button_Axis_F = A5;

int Button_Function_A = 0;
int Button_Function_B = 0;
int Button_Function_C = 0;

int Eye_State = 0;

long randNumber;

void setup()
{
  Serial.begin(9600);

  
  if(!display.begin(SSD1306_SWITCHCAPVCC, DebugScreenAddress)) {
    Serial.println(F("SSD1306 allocation failed"));
   // for(;;); // Don't proceed, loop forever
  }

  Wire.begin();
  Wire.setClock(100000);
 
Serial.print("Screen Initialized");

  memset (LEyeHoldingRegister, 0, sizeof(LEyeHoldingRegister));
  memset (REyeHoldingRegister, 0, sizeof(REyeHoldingRegister));
  memset (ServoHoldingRegister, 0, sizeof(ServoHoldingRegister));

StartupScreen();      // Initial Screen Boot

delay(1000);

SystemCheckScreen();  // System Check Screen

}


void loop()
{
  LocalScreen();      // Local Diagnostics Screen

  ReadController();   // Read Local Controller Values

  EyeControl();       // I2C send controller Button Functions to Slaves
  
  PositionControl();  // I2C send controller Values to Slave
}

void ReadController() 
{
  Value_A = analogRead(Head_Axis_A); // Analog read Axis and save in corrosponding Register
    Value_B = analogRead(Head_Axis_B); // Analog read Axis and save in corrosponding Register
      Value_C = analogRead(Head_Axis_C); // Analog read Axis and save in corrosponding Register
        Value_D = analogRead(Eye_Axis_D); // Analog read Axis and save in corrosponding Register
          Value_E = analogRead(Eye_Axis_E); // Analog read Axis and save in corrosponding Register
            Value_F = analogRead(Button_Axis_F); // Analog read Axis and save in corrosponding Register 

  if (Value_F <= 350){
    Button_Function_A = 100;
    Button_Function_B = 100;
    Button_Function_C = 100;
  }
  else if ( (Value_F > 350) && (Value_F < 550) ) {
    Button_Function_A = 1;
    Button_Function_B = 0;
    Button_Function_C = 0;
  }
  else if ( (Value_F > 550) && (Value_F <= 750) ) {

    Button_Function_A = 0;
    Button_Function_B = 1;
    Button_Function_C = 0;
  }
  else if ( (Value_F > 750) && (Value_F <= 900) ) {
    Button_Function_A = 0;
    Button_Function_B = 0;
    Button_Function_C = 1;
  }
  else {
    Button_Function_A = 0;
    Button_Function_B = 0;
    Button_Function_C = 0;
  }
}

void EyeControl() {
  if (Button_Function_A == 1){
    Eye_State = 1;
  } 
  else if (Button_Function_B ==1){
    Eye_State = 2; 
  }
  else if (Button_Function_C == 1){
    Eye_State = 3;  
  }
  else {
    Eye_State = 0;  
  }

randNumber = random(10, 2000);
  if (randNumber > 1950) {
     Blink = 1;
  }else{
     Blink = 0;
    }

LEyeHoldingRegister[1] = Eye_State;
REyeHoldingRegister[1] = Eye_State;

LEyeHoldingRegister[2] = Blink;
REyeHoldingRegister[2] = Blink;

  Wire.beginTransmission(LEyeAddress); 
    Wire.write(LEyeHoldingRegister,5);  //Command slave to load first set of data
     Wire.endTransmission();

     Serial.println("Left Eye Data Sent: ");
     Serial.println(LEyeHoldingRegister[0]);
     Serial.println(LEyeHoldingRegister[1]);
     Serial.println(LEyeHoldingRegister[2]);
     Serial.println(LEyeHoldingRegister[3]);
     Serial.println(LEyeHoldingRegister[4]);
      Serial.println(" ");

  Wire.beginTransmission(REyeAddress); 
    Wire.write(REyeHoldingRegister,5);  //Command slave to load first set of data
     Wire.endTransmission();

     Serial.println("Right Eye Data Sent: ");
     Serial.println(REyeHoldingRegister[0]);
     Serial.println(REyeHoldingRegister[1]);
     Serial.println(REyeHoldingRegister[2]);
     Serial.println(REyeHoldingRegister[3]);
     Serial.println(REyeHoldingRegister[4]);
      Serial.println(" ");
}

  void PositionControl() 
{
   ServoHoldingRegister[1] = (Value_A / 10);
   ServoHoldingRegister[2] = (Value_B / 10);
   ServoHoldingRegister[3] = (Value_C / 10);
   ServoHoldingRegister[4] = (Value_D / 10);
   ServoHoldingRegister[5] = (Value_E / 10);
   ServoHoldingRegister[6] = Eye_State;

   Wire.beginTransmission(ServoAddress); 
    Wire.write(ServoHoldingRegister,8);  //Command slave to load first set of data
     Wire.endTransmission();

     Serial.println("Servo Data Sent: ");
     Serial.println(ServoHoldingRegister[0]);
     Serial.println(ServoHoldingRegister[1]);
     Serial.println(ServoHoldingRegister[2]);
     Serial.println(ServoHoldingRegister[3]);
     Serial.println(ServoHoldingRegister[4]);
     Serial.println(ServoHoldingRegister[5]);
     Serial.println(ServoHoldingRegister[6]);
     Serial.println(ServoHoldingRegister[7]);
      Serial.println(" ");
     
}


 void StartupScreen() {
  display.clearDisplay();
  display.setTextSize(2);             // Normal 1:1 pixel scale
  display.setTextColor(SSD1306_WHITE);        // Draw white text
  display.setCursor(0,0);             // Start at top-left corner
  
  display.println("LESHYv4.03");
  display.setTextSize(1);             // Normal 1:1 pixel scale
  display.display();
  delay(1000);
  display.println("Program_Startup.Begin");
  display.display();
  delay(1000);
  display.println("Scripts_All.Begin");
  display.display();
  delay(1000);
  display.println("USAGE:");
  display.println("    /renew [adapter] /release [adapter");
  display.println("    /setclassid adapter [classid]");
  display.display();
  delay(1000);
  display.println("WARNING: SAFETY OVERIDE ENABLED");
  display.display();
  delay(3000);
  display.setTextColor(SSD1306_WHITE);        // Draw white text
  display.println("Welcome.");  
  display.display();
  delay(5000);  
}

void SystemCheckScreen() {
  display.clearDisplay();
  display.setTextSize(1);             // Normal 1:1 pixel scale
  display.setTextColor(SSD1306_WHITE);        // Draw white text
  display.setCursor(0,0);             // Start at top-left corner

  display.println(("System_Check.Begin"));
  display.display();
  delay(4000);
  display.println(("Robotic Control V1.0"));
  display.println(("Codename:PRESHY_LESHY"));
  display.display();
  delay(2000);
  display.println("");
  display.println("114 Errors Present");  
  display.display();
  delay(2000);
  display.println("Err_Override.Begin");
  display.display();
  delay(2000);
  display.println("0 Errors Present");  
  display.display();   
  delay(6000);  
}

void LocalScreen(){
  
  display.clearDisplay();
  display.setTextSize(1);             // Normal 1:1 pixel scale
  display.setTextColor(SSD1306_WHITE);        // Draw white text
  display.setCursor(0,0);             // Start at top-left corner
  display.println("Program.Running");
  display.println("");
  display.print("Axis_A = ");
  display.println(Value_A);
  display.print("Axis_B = ");
  display.println(Value_B);
  display.print("Axis_C = ");
  display.println(Value_C);
  display.print("Axis_D = ");
  display.println(Value_D);
  display.print("Axis_E = ");
  display.println(Value_E);
  display.print("Axis_F = ");
  display.println(Value_F);
  display.display();
}
