# api-aggregator

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

> [!IMPORTANT]  
> This project relies on setting up environment variables. Please map the values for `EXTERNAL_CONTACT_API_ENDPOINT` and `EXTERNAL_CONTACT_API_TOKEN` accordingly.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Running using docker

The application can be packaged using:

```shell script
./mvnw package
```

The image can be built using:

```shell script
docker build -t contact-aggregator . 
```

The container can then be started by executing:

```shell script
docker run -p 8080:8080 -e EXTERNAL_CONTACT_API_ENDPOINT=<redacted> -e EXTERNAL_CONTACT_API_TOKEN=<redacted> contact-aggregator
```
