Neopets Java SDK
================
Neopets Java SDK provides Java APIs to various Neopets activities and services.
Each API wraps around a set of HTTP requests that would be made when normally playing Neopets on your web browser.

## Features
Currently, APIs for the following services are implemented:

* The National Neopian Bank
* The Neopets Stock Market

## Usage
This SDK is organized at the service level.
Each service has its own client class that provides an interface to various actions.
Account information is given during a client's instantiation by the [NeopetsCredentials](https://github.com/pslawski/np-java-sdk/tree/master/src/main/java/com/neopets/auth/NeopetsCredentials.java)
interface.

Below displays how bank interest can be collected.

```java
NeopetsCredentials credentials = new BasicNeopetsCredentials("username", "password");

NeopetsBank bank = new NeopetsBankClient(credentials);

GetBankRecordResult results = bank.getBankRecord();

if (results.getBankRecord().canCollectInterest()) {
  bank.collectInterest();
}
```

Samples to each service API can be found in [src/samples](https://github.com/pslawski/np-java-sdk/tree/master/src/samples).



