#include <Wire.h>
#include "SPI.h" //Includes library for SPI communication of display
#include "Adafruit_GFX.h" //Includes core graphics library
#include "Adafruit_SSD1351.h" //Includes hardware specific library

byte REyeHoldingRegister[5];

extern const uint16_t R1[];
extern const uint16_t R2[];
extern const uint16_t R3[];
extern const uint16_t R4[];
extern const uint16_t R5[];
extern const uint16_t RN[];

int REyeAddress = 0x0B; // Left Eye Controller Hex Address

int buttonState0 = 0;        // current state of the button
int lastButtonState0 = 0;    // previous state of the button
int buttonState1 = 0;        // current state of the button
int lastButtonState1 = 0;    // previous state of the button
int buttonState2 = 0;        // current state of the button
int lastButtonState2 = 0;    // previous state of the button
int buttonState3 = 0;        // current state of the button
int lastButtonState3 = 0;    // previous state of the button

int Button_Function_A = 0;
int Button_Function_B = 0;
int Button_Function_C = 0;
int Eye_State = 0;
int Blink = 0;

long randNumber;

//screen dimensions
#define SCREEN_WIDTH 128 //pixel width
#define SCREEN_HEIGHT 128 //pixel height

//pin definitions
#define SCLK_PIN 52 //defines s clock pin
#define MOSI_PIN 51 //defines master-out slave-in SDA pin
#define RST_PIN   8 //defines reset pin
#define DC_PIN    9 //defines master-in slave-out pin
#define CS_PIN    10 //defines chip select pin

// Colour definitions
#define BLACK           0x0000
#define BLUE            0x001F
#define RED             0xF800
#define GREEN           0x07E0
#define CYAN            0x07FF
#define MAGENTA         0xF81F
#define YELLOW          0xFFE0  
#define WHITE           0xFFFF
#define GREY            0x8410
#define ORANGE          0xE880

Adafruit_SSD1351 display = Adafruit_SSD1351(SCREEN_WIDTH, SCREEN_HEIGHT, &SPI, CS_PIN, DC_PIN, RST_PIN);

void setup() {
  Serial.begin(9600);
  
  Wire.begin(REyeAddress);
  Wire.setClock(100000);

  Wire.onReceive(receiveEvent);
  
//  pinMode(SpeakingInput, INPUT_PULLUP);
  display.begin();
  display.fillScreen(BLACK);
  
SystemCheckScreen();  // System Check Screen

  display.fillScreen(BLACK);
  
}

void loop() {
  
if (Eye_State == 0){
    buttonState0 = 1;
      buttonState1 = 0;
        buttonState2 = 0;
          buttonState3 = 0;

            lastButtonState1 = 0;
              lastButtonState2 = 0;
                lastButtonState3 = 0;

  if (buttonState0 != lastButtonState0) {
  // if the state has changed, increment the counter
  display.fillScreen(BLACK);
  }
  // save the current state as the last state, for next time through the loop
  lastButtonState0 = buttonState0;        
          
  display.drawRGBBitmap(39,37,RN,54,70);
}

if (Eye_State ==1){
    buttonState0 = 0;
      buttonState1 = 1;
        buttonState2 = 0;
          buttonState3 = 0;
          
            lastButtonState0 = 0;
              lastButtonState2 = 0;
                lastButtonState3 = 0;

  if (buttonState1 != lastButtonState1) {
    display.fillScreen(BLACK);
  }
  lastButtonState1 = buttonState1;

  display.drawRGBBitmap(27,27,R1,74,72);
  display.drawRGBBitmap(27,27,R2,72,74);
  display.drawRGBBitmap(27,27,R3,74,72);
  display.drawRGBBitmap(27,27,R4,74,74);
  display.drawRGBBitmap(27,27,R5,72,74);
  }


if (Eye_State == 2){
    buttonState0 = 0;
      buttonState1 = 0;
        buttonState2 = 1;
          buttonState3 = 0;
                              
            lastButtonState0 = 0;
              lastButtonState1 = 0;
                lastButtonState3 = 0;

  if (buttonState2 != lastButtonState2) {
    display.fillScreen(BLACK);
  }
  lastButtonState2 = buttonState2;
  
    display.fillScreen(BLACK);
}


if (Eye_State == 3){
    buttonState0 = 0;
      buttonState1 = 0;
        buttonState2 = 0;
          buttonState3 = 1;
                    
            lastButtonState0 = 0;
              lastButtonState1 = 0;
                lastButtonState2 = 0;

  if (buttonState3 != lastButtonState3) {
    display.fillScreen(BLACK);
  }
  lastButtonState3 = buttonState3;
    display.fillScreen(BLACK);
    display.fillScreen(WHITE);
}


if ((Eye_State == 0) && (Blink == 1)){
    display.fillScreen(BLACK);
  }

  
}
void receiveEvent(int howmany) {
        Serial.println("  Debug: Data Recieved ");
                Serial.print("  Debug: Recieved Command was ");
  for(int ia=0; ia<howmany; ia++)
  {
    REyeHoldingRegister[ia] = Wire.read();
        Serial.print(REyeHoldingRegister[ia]);  
  }
  Eye_State = REyeHoldingRegister[1];
  
  Blink = REyeHoldingRegister[2];

  if (Eye_State == 1){
    Button_Function_A = 1;
    Button_Function_B = 0;
    Button_Function_C = 0;
  } 
  else if (Eye_State == 2){
    Button_Function_A = 0;
    Button_Function_B = 1;
    Button_Function_C = 0;
  }
  else if (Eye_State == 3){
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
// BOOT SEQUENCE
void SystemCheckScreen() { 
display.fillScreen(RED);
  delay(100);
display.fillScreen(GREEN);
  delay(100);
display.fillScreen(BLUE);
  delay(100);
display.fillScreen(BLACK);
  delay(500);
  display.setCursor(0, 5);
  display.setTextColor(WHITE);  
  display.setTextSize(1);
  display.println("System_Load: LeftEye");
  delay(2000);
  display.println("Program_Execute.Begin");
  delay(1000);
  display.println("HN_ Framework successfully initialized using 16384 buffer headers and 10240 cluster IO buffer headers IOAPIC: Version 0x20 Vectors 64:87 ACPI: System State [S0 S3 S4 S5] (S3) PFM64 0xf10000000, 0xf0000000");
  delay(1000);
  display.fillScreen(BLACK);
  display.setCursor(0, 5);
  display.println("[ PCI configuration begin ] HacknetIntelCPUPowerManagement: Turbo Ratios 0046 HacknetIntelCPUPowerManagement: (built 13:08:12 Jun 18 2011) initialization complete console relocated to 0xf10000000 PCI configuration changed (bridge=16 device=4 cardbus=0) [ PCI configuration end, bridges 12 devices 16 ] mbinit: done [64 MB total pool size, (42/21) split]");
  delay(1000);
  display.fillScreen(BLACK);
  display.setCursor(0, 5);
  display.println("Pthread support ABORTS when sync kernel primitives misused com.Hacknet.HacknetFSCompressionTypeZlib kmod start com.Hacknet.HacknetTrololoBootScreen kmod start com.Hacknet.HacknetFSCompressionTypeZlib load succeeded com.Hacknet.HacknetFSCompressionTypeDataless load succeeded");
  delay(1000);
  display.fillScreen(BLACK);
  display.setCursor(0, 5);
  display.println("acknetIntelCPUPowerManagementClient: ready BTCOEXIST off  wl0: Broadcom BCM4331 802.11 Wireless Controller 5.100.98.75 FireWire (OHCI) Lucent ID 5901 built-in now active, GUID c82a14fffee4a086; max speed s800. rooting via boot-uuid from /chosen: F5670083-AC74-33D3-8361-AC1977EE4AA2");
  delay(3000);
  display.fillScreen(BLACK);
  display.setTextColor(RED);    
  display.setCursor(0, 5);
  display.println("WARNING");
  display.println("AI CALIBRATION FAILED: SAFETYS DISABLED");
  display.println("RUN AT OWN RISK");
  delay(3000);
}


  
