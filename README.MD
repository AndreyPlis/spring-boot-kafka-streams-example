### Service description page


### Modules

- service

## Переменные окружения проекта
* SPRING
    * `${SPRING_APPLICATION_PORT}`- рабочий порт приложения
    * `${SPRING_PROFILES_ACTIVE}` - активный spring-профиль
* KAFKA
    * consumer
        * `${KAFKA_CONSUMER_CROSS_REFERENCE_BOOTSTRAP_SERVERS}` - адреса bootstrap-серверов kafka для получения cross-ссылок
        * `${KAFKA_CONSUMER_FETCH_MAX_WAIT}` (ms) - fetch.wait.max.ms - максимальное количество времени, которое сервер заблокирует перед ответом на запрос выборки
        * `${KAFKA_CONSUMER_FETCH_MIN_SIZE}` (bytes) - fetch.min.bytes - минимальный объем данных, который сервер должен возвращать для запроса на извлечение
        * `${KAFKA_CONSUMER_MAX_POLL_RECORDS}` - max.poll.records - максимальное количество записей, возвращаемых за один вызов функции poll()
        * `${KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL}` - auto.commit.interval.ms - частота в миллисекундах, с которой смещения потребителя автоматически фиксируются в Kafka
        * `${KAFKA_BOOTSTRAP_SERVERS}` - адреса bootstrap-серверов kafka для записи output-сущностей и чтения input-сущностей
* DATASOURCE
    * `${DB_URL}` - полный адрес jdbc-подключения к базе данных
    * `${DB_USER}`- имя пользователя для подключения к базе данных
    * `${DB_PASSWORD}`- пароль пользователя
    * `${DB_SCHEMA}` - рабочая схема базы данных (такая же, что и в ${DB_URL})
    
### Build

``
./gradlew build
``

(if gradlew doesn't exist, run ``gradle wrapper`` first)

### SonarQube command

```./gradlew sonarqube```

https://sonarqube

## Run tests

./gradlew test

## Preconditions

### k8s
1. Install kustomize, skaffold
```
brew install kustomize skaffold
```
2. Apply kustomization for local env(postgres, kafka, redis)
```
kubectl apply -k ./k8s/base  
```
3. Run service with auto reload
```
skaffold dev
```
with manual reload
```
skaffold dev --trigger=manual
```
or debug service
```
skaffold debug
```

4. There are 3 ways to enable port forwarding.
#### 1.Install plugin https://github.com/txn2/kubefwd for port forwarding.
```
brew install txn2/tap/kubefwd
```
Enable port forwarding to localhost
```
sudo kubefwd svc -n default
```
####2.Using skaffold (uncomment block of code)
####3.Using command (example for postgres)
```
kubectl port-forward service/postgres 5432:5432
```


### Nexus access

Using env variables

```
MacOS. Add to ~/.zshrc
export ORG_GRADLE_PROJECT_nexusUser=login
export ORG_GRADLE_PROJECT_nexusPassword=password
source ~/.zshrc

Linux. Add to ~/.profile

ORG_GRADLE_PROJECT_nexusUser=login
ORG_GRADLE_PROJECT_nexusPassword=password
source ~/.profile

```

Using gradle properties

Add this params to ~/.gradle/gradle.properties

```
nexusUser=login
nexusPassword=password
```