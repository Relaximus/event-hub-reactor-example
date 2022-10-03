## Reactive sending client for Azure Event Hub example

It's a very simple client, sending some mock data to the configured Event Hub in Azure.

[Azure SDK](https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/eventhubs/azure-messaging-eventhubs).

### Startup
Don't forget to create an Event Hub.
Configure `hub.connection-string`, `hub.name` respectively in the **application.yaml** file
and just start spring application:

```shell
./gradlew bootRun
```

### Main workflow
It sends 300 seconds of random data with the 1s delay between messages.