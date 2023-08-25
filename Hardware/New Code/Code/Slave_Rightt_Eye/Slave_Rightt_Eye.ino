/* This is a test to display an RGB bitmap from a picture file to
 *  be displayed onto a 1.5" 128x128 pixel OLED SS1351 screen.
 *  Contact tylerovens@me.com if you have any questions
 */

//included libraries
#include "SPI.h" //Includes library for SPI communication of display
#include "Adafruit_GFX.h" //Includes core graphics library
#include "Adafruit_SSD1351.h" //Includes hardware specific library

extern const uint16_t R1[];
extern const uint16_t R2[];
extern const uint16_t R3[];
extern const uint16_t R4[];
extern const uint16_t R5[];
extern const uint16_t RN[];

#define SpeakingInput 4  // the number of the pushbutton pin

int Speaking = 1;  // variable for reading the pushbutton status
int buttonPushCounter = 0;  // counter for the number of button presses
int buttonState = 0;        // current state of the button
int lastButtonState = 0;    // previous state of the button


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
  pinMode(SpeakingInput, INPUT_PULLUP);
  display.begin();
  display.fillScreen(BLACK);

SystemCheckScreen();  // System Check Screen

  display.fillScreen(BLACK);
  
}

void loop() {
  Speaking = digitalRead(SpeakingInput);
  buttonState = digitalRead(SpeakingInput);


  if (buttonState != lastButtonState) {
    // if the state has changed, increment the counter
    display.fillScreen(BLACK);
    delay(500);
  }
  // save the current state as the last state, for next time through the loop
  lastButtonState = buttonState;
  
  if (Speaking == 0) {
  display.drawRGBBitmap(27,27,R1,74,72);
  display.drawRGBBitmap(27,27,R2,72,74);
  display.drawRGBBitmap(27,27,R3,74,72);
  display.drawRGBBitmap(27,27,R4,74,74);
  display.drawRGBBitmap(27,27,R5,72,74);
  }
  
  if (buttonState != lastButtonState) {
    // if the state has changed, increment the counter
    display.fillScreen(BLACK);
  }
  
  if (Speaking == 1) {
  display.drawRGBBitmap(39,37,RN,54,70);
  }

  randNumber = random(10, 200);
  if (randNumber > 195) {
    display.fillScreen(BLACK);
    delay(50);
  }
}
  
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
  display.println("System_Load: RightEye");
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

  
