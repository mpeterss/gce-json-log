Example Project for structured logging in GCE
=================

Example Java project to test structured logging in a GCE Container.

You should have a GCE instance in GCP with the setting "Deploy a container image to this VM instance" to be able to deploy the Docker image to to the GCE instance.


Build
===============
```
./gradlew build
```


Docker
===============

```
docker build -t eu.gcr.io/${GCP_PROJECT_ID}/test-logging:1.0 .
```


Deploy to GCP
===============

Push image to a registry

```
docker push eu.gcr.io/${GCP_PROJECT_ID}/test-logging:1.0
```

Deploy to your GCE instance

```
    gcloud compute instances update-container --zone ${ZONE} ${NODE_NAME} \
    --container-image eu.gcr.io/${GCP_PROJECT_ID}/test-logging:1.0
```


