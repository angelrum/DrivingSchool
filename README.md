# DrivingSchool Log
----------------------------------------

Установка
1. Скачиваем последнюю версию JDK https://jdk.java.net/15/
2. Ссылка на Tomcat: https://tomcat.apache.org/download-90.cgi Установка необязательна, в проекте используется Spring Boot DevTools
3. Настройки к БД Postgres:
a) База данных: school
b) Пользователь: user
c) Пароль: password
4. Чтобы среда разработки не ругалась на отсутствие геттеров и сеттеров, необходимо устновить в среду разработки плагин Lombok

Полезная информация
1. Spring Boot DevTools настройка: https://habr.com/ru/post/479382/
2. Функции Lombok: https://projectlombok.org/features/all
3. В случае, если не установлен Git, смотреть ссылку https://gist.github.com/flumono/6e6734334bd4e0d8187d32433430278f
4. Нужно обновить  java

17.11.2020 - Добавлен каталог openapi со draft-спецификацией api-шки. Внутри файл swagger.yaml
Установка Swagger:
1. Скачиваем и устанавливаем Nodejs (ver 14.15.1), https://nodejs.org/en/
2. Поднимаем у себя http-сервер: 
   > npm install -g http-server
3. Скачиваем архив Swagger'а и распаковываем у себя в каком-ниубдь каталоге.
   > git clone https://github.com/swagger-api/swagger-editor.git
4. Заходим в каталог, выполняем установку.
   > cd swagger-editor
   > npm install
   > npm run build
5. Запускаем npm start
6. Результат - поднятый Swagger на localhost:3001. Открываем в браузере, из интерфейса File - Import file
берем файл из openapi/swagger.yaml

https://swagger.io/docs/open-source-tools/swagger-editor/
   




