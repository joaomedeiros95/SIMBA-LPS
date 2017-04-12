#include <Bridge.h>
#include <BridgeServer.h>
#include <BridgeClient.h>

BridgeServer server;

void setup() {
  // Bridge startup
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
  Bridge.begin();
  digitalWrite(13, HIGH);

//libera apenas conexao localhost
  //server.listenOnLocalhost();
  server.begin();
}

void loop() {
  // aceita novo cliente
  BridgeClient client = server.accept();

  // novo cliente ?
  if (client) {
   
    //inicia novo cliente
    process(client);

    
    client.stop();
  }

  delay(50); // espera 50ms para uma nova requisicao
}

void process(BridgeClient client) {
 
  // recebe novo comando apartir da /
  String command = client.readStringUntil('/');

  // comando digital?
  if (command == "digital") {
    digitalCommand(client);
  }

  // comando analogico ?
  if (command == "analog") {
    analogCommand(client);
  }

  // comando de modo para pinos ?
  if (command == "mode") {
    modeCommand(client);
  }
}

void digitalCommand(BridgeClient client) {
  int pin, value;

  // coloca em pin o pino passado pelo comando
  pin = client.parseInt();

  // se apos o pino for / escreva, se nao, leia
  if (client.read() == '/') {
    value = client.parseInt();
    if(pin == 5){
      tone(pin,261); //aciona o buzzer
      delay(2000);
      noTone(pin);
    }
    else{
      digitalWrite(pin, value);
    }
  } else {
    value = digitalRead(pin);
  }

  // resposta ao cliente
 // client.print(F("Pino Digital"));
  //client.print(pin);
  //client.print(F(" valor: "));
  client.println(value);

  // atualiza no arduino o valor atual do pino passado
  String key = "D";
  key += pin;
  Bridge.put(key, String(value));
}

void analogCommand(BridgeClient client) {
  int pin, value;

  // coloca em pin o pino passado pelo comando
  pin = client.parseInt();

  
  // se apos o pino for uma / entao tem comando de escrita, se nao, de leitura
  if (client.read() == '/') {
    // recebe o valor passado no comando
    value = client.parseInt();
    analogWrite(pin, value);

    // resposta ao cliente
   // client.print(F("Pino Analogico "));
    //client.print(pin);
    //client.print(F(" valor analogico: "));
    client.println(value);

    // atualiza no arduino o valor do pino
    String key = "D";
    key += pin;
    Bridge.put(key, String(value));
  } else {
    // recebe o valor do pino pelo arduino
    value = analogRead(pin);

    // resposta ao cliente
    //client.print(F("Pin Analogico"));
    //client.print(pin);
    //client.print(F(" valor analogico: "));
    client.println(value);

    // atualiza o valor do pino no arduino
    String key = "A";
    key += pin;
    Bridge.put(key, String(value));
  }
}

void modeCommand(BridgeClient client) {
  int pin;

  // recebe o pino do cliente
  pin = client.parseInt();

  // se apos o pino nao for uma barra, entao erro pois a url nao esta bem escrita
  if (client.read() != '/') {
    client.println(F("erro"));
    return;
  }

  //recebe apos o pino o tipo de modo
  String mode = client.readStringUntil('\r');

 
  // se for input configure o pino como entrada de dados
  if (mode == "input") {
    pinMode(pin, INPUT);
    // resposta
    client.print(F("Pino"));
    client.print(pin);
    client.print(F(" configurado como entrada (INPUT)"));
    return;
  }

  // se for output configure o pino como saida de dados
  if (mode == "output") {
    pinMode(pin, OUTPUT);
    // resposta ao cliente
    client.print(F("Pino"));
    client.print(pin);
    client.print(F(" configurado como saida (OUTPUT)"));
    return;
  }

  client.print(F("erro, url mal formada "));
  client.print(mode);
}
