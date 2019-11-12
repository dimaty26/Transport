# Transport services 

На текущий момент проект состоит из четырех компонент:
* Сервис авторизации/аутентификации (authentication service)
* Сервис с бизнес логикой (transport service)
* База данных (Postgres)
* API тестер (Frontend service)

## Инструкции

Внимательно прочитайте все что описано ниже, это поможет избежать лишних вопросов.

### Требования

Что вам нужно установить:

Очень желательно иметь Линукс.

Минимум:
* [Java8](https://java.com/ru/download/)
* [Maven](https://maven.apache.org/) - Собирает проект и подтягивает зависимости

Желательно:
* [Docker](https://www.docker.com/) - Запускает каждый сервис в унифицированном контейнере 
* [Docker-compose](https://docs.docker.com/compose/install/) - Чтобы запускать все сервисы сразу

Опционально:
* [Postman](https://www.getpostman.com/) - Тестить апи
* [DBeaver](https://dbeaver.io/) - Тестить базу

### Установка необходимых пакетов в Ubuntu

```
    WORK IN PROGRESS
```

## Сборка и запуск

### Сборка

+ Клонируем репо:
```
    git clone https://github.com/checorone/transporteye.git
```

+ Переходим в деректорию сборки:
```
    cd transporteye
```

+ Собираем сервисы и упаковываем их в докер контейнеры:
```
    mvn clean package docker:build
```
### Запуск вручную

+ Устанавливаем, настраиваем и запускаем PostgreSQL (Зависит от дистрибутива)
    
```
    Заметка: С помощью docker-compose вы можете запускать базу, без каких либо дополнительных настроек и установок. 
    Просто выполните в консоли следующую команду:
    docker-compose -f docker/common/docker-compose.yml up database
```
    
+ Запускаем сервис Аутентификации
```
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=8901         \ 
    -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres   \
    -Dspring.profiles.active=default                                    \
    -jar authentication-service/target/authentication-service-0.0.1-SNAPSHOT.jar
```
+ Запускаем сервис Транспорта
```
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=8085 \
    -Dsecurity.oauth2.resource.userInfoUri=http://localhost:8901/user \
    -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres \
    -Dspring.profiles.active=default \
    -jar transport-service/target/transport-service-0.0.1-SNAPSHOT.jar
```
+ Открываем и тестируем фронтэнд
```
    frontend-service/src/main/web/indexman.html
```

### Запуск через Docker-compose

Запускаем сервисы:

```
    docker-compose -f docker/common/docker-compose.yml up
```

Останавливаем сервисы:

```
    Ctrl + C
```

Полностью перезапустить сервисы:

```
    docker-compose -f docker/common/docker-compose.yml rm
    docker-compose -f docker/common/docker-compose.yml up
```

## Использование

### Через Апи тестер

[localhost (порт 80)](http://localhost)

### Работа с токенами через postman
Получить токен на доступ
```
    У http://localhost:8901/oauth/token. 
    При этом выбрать авторизацию Basic Auth c логином netcracker и паролем ncpassword. 
    Метод POST и тип body установить как form-data, поля:
    * grant_type : password
    * username : john.doe
    * password : userpass
```

Отправить GET к transport service
```
    На http://localhost:8085/api/v1/transport. 
    При этом выбрать авторизацию Bearer и вставить полученный access_token.
```

Проверка access токена
```
    GET http://localhost:8901/oauth/check_token?token=токен
```

Обновить access по refresh токену
```
    POST http://localhost:8901/oauth/token
    * grant_type : refresh_token
    * refresh_token : токен
```

 Удалить токены из базы (альтернатива logout при stateless)
```
    DELETE http://localhost:8901/token/revoke
    * access_token : токен (опционально)
    * refresh_token : токен (опционально, также удаляет access токен)
```

### Работа с пользоваелями через postman
Регистрация нового пользователя. Для завершения регистрации нужно перейти по ссылке на почте. По умолчанию права USER.
```
    POST http://localhost:8901/users/register
    * username (логин для входа)
    * password 
    * email
```
Изменение прав пользователя. Требуются права ADMIN.
```
    PUT http://localhost:8901/admin/modify/roles
    Authorization: Bearer токен
    * username: изменяемый юзер
    * roles: права юзера 
    * ...
    * roles: (произвольное количество, но пока только USER И ADMIN)
```
Изменение пароля. На почту пользователя будет отправлена ссылка на страницу(в процессе)
```
    PUT http://localhost:8901/users/password/recovery
    * username: изменяемый юзер
```
Изменение пароля. 
```
    PUT http://localhost:8901/users/password/change
    * new_password: новый пароль
    * repeat_password: еще раз
    * uuid: последняя часть ссылки с почты (обрабатывается страницей)
```

## Описание структуры

```
    WORK IN PROGRESS
```
