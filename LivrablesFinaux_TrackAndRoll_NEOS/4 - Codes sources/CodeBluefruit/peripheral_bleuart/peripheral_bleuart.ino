/*********************************************************************
 This is an example for our nRF52 based Bluefruit LE modules

 Pick one up today in the adafruit shop!

 Adafruit invests time and resources providing this open source code,
 please support Adafruit and open-source hardware by purchasing
 products from Adafruit!

 MIT license, check LICENSE for more information
 All text above, and the splash screen below must be included in
 any redistribution
*********************************************************************/
#include <bluefruit.h>

// BLE Service
BLEDis  bledis;
BLEUart bleuart;
BLEBas  blebas;

// Device identifier
const uint8_t DEVICE_ID=0x1;

// Definitions  
const int HR_RX = 16;
uint8_t oldSample, sample;
uint8_t heartBeatCounter=0;
uint8_t dataToSend=0;

// Timer
SoftwareTimer timer;

/**
 * Setup method called once when device is booted
 */
void setup()
{
  Serial.begin(115200);
  Serial.println("Bluefruit52 BLEUART");
  Serial.println("---------------------------\n");

  // Initialize GPIO
  pinMode (HR_RX, INPUT);  //Signal pin to input  
  pinMode(LED_BUILTIN, OUTPUT);

  // Initialize timer (counting 10 seconds)
  timer.begin(10000, timer_callback);
  timer.start();

  Bluefruit.begin();
  // Set max power. Accepted values are: -40, -30, -20, -16, -12, -8, -4, 0, 4
  Bluefruit.setTxPower(4);
  Bluefruit.setName("Bluefruit52");
  //Bluefruit.setName(getMcuUniqueID()); // Useful testing with multiple central connections
  Bluefruit.setConnectCallback(connect_callback);
  Bluefruit.setDisconnectCallback(disconnect_callback);

  // Configure and start Device Information Service
  bledis.setManufacturer("Adafruit Industries");
  bledis.setModel("Bluefruit Feather52");
  bledis.begin();

  // Configure and start BLE Uart Service
  bleuart.begin();

  // Start BLE Battery Service
  blebas.begin();
  blebas.write(100);

  // Set up and start advertising
  startAdv();
}

/*
 * Method to build and send the Beacon advertising payload
 */
void startAdv(void)
{
  // Advertising packet
  Bluefruit.Advertising.addFlags(BLE_GAP_ADV_FLAGS_LE_ONLY_GENERAL_DISC_MODE);
  Bluefruit.Advertising.addTxPower();

  // Include bleuart 128-bit uuid for bleuart service
  Bluefruit.Advertising.addService(bleuart);

  // Secondary Scan Response packet (optional)
  // Since there is no room for 'Name' in Advertising packet
  Bluefruit.ScanResponse.addName();
  
  /* Start Advertising
   * - Enable auto advertising if disconnected
   * - Interval:  fast mode = 20 ms, slow mode = 152.5 ms
   * - Timeout for fast mode is 30 seconds
   * - Start(timeout) with timeout = 0 will advertise forever (until connected)
   * 
   * For recommended advertising interval
   * https://developer.apple.com/library/content/qa/qa1931/_index.html   
   */
  Bluefruit.Advertising.restartOnDisconnect(true);
  Bluefruit.Advertising.setInterval(32, 244);    // in unit of 0.625 ms
  Bluefruit.Advertising.setFastTimeout(30);      // number of seconds in fast mode
  Bluefruit.Advertising.start(0);                // 0 = don't stop advertising after n seconds  
}

/**
 * Method constantly called in polling mode
 */
void loop()
{
  // Wait to receive heart beat and then increment counter
  sample = digitalRead(HR_RX);  // Store signal output
  // Compare previous heart beat sample with the current one to detect an effective change 
  if (sample && (oldSample != sample)) {
    digitalWrite(LED_BUILTIN, HIGH); // Blink LED for debugging
    Serial.println("Beat");
    heartBeatCounter++;
  }
  oldSample = sample;           // Store last signal received 
  digitalWrite(LED_BUILTIN, LOW);
}

/**
 * Callback invoked when a connection is established
 * @param conn_handle : the connection handler
 */
void connect_callback(uint16_t conn_handle)
{
  char central_name[32] = { 0 };
  Bluefruit.Gap.getPeerName(conn_handle, central_name, sizeof(central_name));

  Serial.print("Connected to ");
  Serial.println(central_name);
}

/**
 * Callback invoked when a connection is dropped
 * @param conn_handle : the connection handler
 * @param reason : error code
 */
void disconnect_callback(uint16_t conn_handle, uint8_t reason)
{
  (void) conn_handle;
  (void) reason;

  Serial.println();
  Serial.println("Disconnected");
}

/*
 * This method is called when the timer finishes to count his 10 seconds
 * After 10s, we send the number of heart beats detected during this time
 * @param xTimerID : the timer's identifier code
 */
void timer_callback(TimerHandle_t xTimerID){
  (void) xTimerID;
  timer.stop();
  timer.begin(10000, timer_callback);
  timer.start();
  Serial.println(heartBeatCounter);
  uint8_t buf[2]={0};
  buf[0]=DEVICE_ID;
  buf[1]=heartBeatCounter;
  bleuart.write( buf, 2); // Send device ID and heart beat counter via Bluetooth to central
  heartBeatCounter = 0;
}
