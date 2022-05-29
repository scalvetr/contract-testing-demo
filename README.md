# contract-testing-demo

## :gear: Build

Build the project
```shell
cd demo-producer
./gradlew build
```

Check stubs are generated
```shell
ls build/libs
# demo-producer-0.0.1-SNAPSHOT-aot.jar
# demo-producer-0.0.1-SNAPSHOT-plain.jar
# demo-producer-0.0.1-SNAPSHOT-stubs.jar
# demo-producer-0.0.1-SNAPSHOT.jar
```

## :rocket: Run

## Create projects
Create the consumer project 
[here](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.6.8&packaging=jar&jvmVersion=17&groupId=com.scalvetr&artifactId=demo-producer&name=demo-consumer&description=Demo%20project%20for%20Spring%20Boot&packageName=com.scalvetr.demo-producer&dependencies=native,cloud-contract-stub-runner,webflux,devtools,actuator,validation)

Create the producer project
[here](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.6.8&packaging=jar&jvmVersion=17&groupId=com.scalvetr&artifactId=demo-producer&name=demo-producer&description=Demo%20project%20for%20Spring%20Boot&packageName=com.scalvetr.demo-producer&dependencies=native,cloud-contract-verifier,webflux,devtools,actuator,validation)