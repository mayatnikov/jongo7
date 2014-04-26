Исследование возможностей ГеоЛокации

Что надо:
    - установить MongoDB
    - IDE IDEA
    - RoboMogo - удобная IDE для работы с MongoDB
    - библиотеки для сборки и тестирования ( можно собрать и MAVEN'ом) :
-rw-r--r--   1 vitaly  staff    17717 20 дек  2011 JUnitParams-0.3.7.jar
-rw-r--r--   1 vitaly  staff    62050 17 май  2013 commons-logging-1.1.3.jar
-rw-r--r--   1 vitaly  staff    78451 22 фев  2011 fest-assert-1.4.jar
-rw-r--r--   1 vitaly  staff    80979  3 дек 20:16 jongo-1.0.jar
-rw-r--r--   1 vitaly  staff   253160 29 сен  2011 junit-4.10.jar
-rw-r--r--   1 vitaly  staff   367444 31 авг  2008 log4j-1.2.14.jar
-rw-r--r--   1 vitaly  staff  1414161 20 дек  2011 mockito-core-1.9.0.jar
-rw-r--r--   1 vitaly  staff   586570 28 мар 01:06 mongo-java-driver-2.12.0-rc3.jar
-rw-r--r--   1 vitaly  staff     8869  6 фев 02:38 slf4j-log4j12-1.7.6.jar
-rw-r--r--   1 vitaly  staff   829601 13 дек  2011 spring-context-3.1.0.RELEASE.jar
-rw-r--r--   1 vitaly  staff   464045 13 мар 15:25 spring-data-mongodb-1.4.1.RELEASE.jar

Создание базы данных:
    - генерим файл имопрт с Гео-точками, вот так:
    perl create_geo_db/generate-geo-db.pl > imp2db.dat
    - генератор создает 2 000 000 геоточек с случайными координатами в заданном диапазоне
    - с использованием RoboMongo создаем collection GEO.msk и импортируем в него imp2db.dat

проверяем состояние коллекции, вот так:
.......mongodb_v2.4/bin/mongo
MongoDB shell version: 2.4.6-pre-
> connect GEO
> use GEO;
switched to db GEO
> db.msk.find().count();
2000000
>
(поиск геоточек в радиусе)
> db.msk.aggregate([
...                       {
...                         $geoNear: {
...                                     near: [55.701, 37.709],
...                                     distanceField: "dist.calculated",
...                                     maxDistance: 0.1,
...                                     query: { "properties.ptype": "ATM" },
...                                     includeLocs: "dist.location",
...                                     uniqueDocs: false,
...                                     num: 5
...                                   }
...                       }
...                    ]);
{
	"result" : [
		{
			"_id" : ObjectId("533d140fca29c2015a6c1bbf"),
			"type" : "Feature",
			"id" : 632606,
			"geometry" : {
				"type" : "Point",
				"coordinates" : [
					55.7009257753217,
					37.7094239552929
				]
			},
......................................................
......................................................

Работа с тестовыми примерами:
mvn package
mvn test
mvn exec:java
или через bash ./runMe.bsh   ./testMe.bash

/ !!! /
Основные тесты смотреть в тетстах GeoTest.java
Интеграция со спринг и пример параметризации в  Main.java

