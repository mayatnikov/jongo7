#!/usr/bin/perl
my $id=0;

#
# при формировании записи vals для записи из тэга выбирается случайным образом
#
#
@tags = (
 {
	nm => 'address',
	vals => ['Москва, ул. Пушкина 18','Москва, ул. Смирнова 13-5','Москва, ул. Кулакова 27-5 корпус 3, кв. 98',
	'Москва, Передекино, ул. Лезюково, дом 432','Москва, центр Красная площадь','г.Москва, наб. Бережковская дом 40','Москва ул. Новикова Прибоя 12-1','Москва ул Марины Полетаевой дом 3-88','Москва, 3-я улица Ямского поля, дом 43 под 2 ','нет адреса','-','?']
},
 {
	nm => 'ptype',
	vals => ['POS','ATM','Питание','Сервис','Аптека', 'Метро','Банк']
},
 {
	nm => 'Time1',
	vals => ['8:00','9:00','11:00','7:00','8:30', '1:00', '0:00']
},
 {
	nm => 'Time2',
	vals => ['18:00','16:00','17:30','21:00','23:00','20:00','20:30']
},

 {
	nm => 'orgName',
	vals => ['Рога и Копыта','НииЧаВо','Супер Консультации','ООО Красота','Быстро и Плохо','ООО Светлый путь',
	'ЗАО ПУТЬ К СЕБЕ','Чиним Штопаем','Вход в Метро','УНИВЕРСАМ','Универмаг','Супермаркет Строгино','Новая Волна','Свежий МИР']
},

{
	nm => 'phone',
	vals => ['+7(912)111 22 33','+7(999)222 33 44','+7(910)987 65 43','+7(888)356 44 33','+7(543)654 99 55','+7(916)675 65 44','+7(915)987 33 85','+7(905)016 56 43','+7(234)000 77 44','+7(111)222 33 44','-','8(916)']
}, 

{
	nm => 'imageType',
	vals => ['flag','small-box','metro','shop','point','cicle']
},

{	
	nm => 'notes',
	vals => ['Просто позвоните нам','Здесь вас качественно обманут','Работает по предзаказу',
	'Ограниченный доступ для VIP','Настящее суши только здесь','Это интересно - заходите',
	'Все ясно без слов','советуем посетить','Мошенники','Воры и еще раз воры','Быстро вкусно и сытно','Черт знает что!']
}
);

for($tik=0;$tik<2000000;$tik++) {
	print_POI();
}

# =========================== SUB ===============================
sub  print_POI()  { 
	$tags_no  =  scalar  (@tags);  
	print_HDR(); 
	print_GEO();
	print ", \"properties\" : { ";
	for  (my $i=0;  $i  <  $tags_no;  $i++)  
		{ 
			if($i !=0)  { print ", "; }
			$sz = scalar @{$tags[$i]{'vals'}};
			$index = int($sz * rand() + 1);
			print ("\"" . $tags[$i]{'nm'}. "\":\"" . @{$tags[$i]{'vals'}}[$index]. "\"" );
		}
	print_TRAIL();
}

sub print_HDR() {
	$id++;
	print '{ "type" : "Feature","id" : '.$id.',';
}


sub print_GEO() {
	$a_min=37.4;
	$l_min=55.6;
	$a2 = 0.5;
	$l2 = 0.3;
	$lat = $l2 * rand() + $l_min;
	$apt = $a2 * rand() + $a_min;
	print '	"geometry" : { "type" : "Point","coordinates" : [' . $lat . ',' . $apt . "]}";
	
}

sub print_TRAIL() {
print ' } }
';	
}

__END__

квадрат Москва:
55.889317, 37.404820  ||  55.891628, 37.880666
----------------------------------------------
55.605648, 37.372548  ||  55.593234, 37.846334

Работа с Базой:
db.msk.ensureIndex({"geometry.coordinates": "2d"})
db.msk.ensureIndex({"geometry": "2dsphere"})
db.msk.find( { "geometry.coordinates": { $near: [ 55.701, 37.709 ], $maxDistance: 0.05 } },{"geometry:coordinates":1,"properties":1 } )
db.msk.find( { "geometry.coordinates": { $near: [ 55.701, 37.709 ], $maxDistance: 0.0004 } },{"geometry.coordinates":1,"properties.address":1 } ).explain() - статистика запроса

db.msk.aggregate([
                      {
                        $geoNear: {
                                    near: [55.701, 37.709],
                                    distanceField: "dist.calculated",
                                    maxDistance: 0.1,
                                    query: { "properties.ptype": "ATM" },
                                    includeLocs: "dist.location",
                                    uniqueDocs: false,
                                    num: 5
                                  }
                      }
                   ])

............................... точки с максимальным/минимальным  расстоянием 

db.msk.aggregate([
       { $geoNear: {
                    near: [55.701, 37.709],
                    distanceField: "dist.calculated",
                    maxDistance: 0.1,
                    query: { "type": "Feature" },
                    includeLocs: "dist.location",
                    uniqueDocs: true,
                    num: 5
                    }
       },
       {  $group :{ 
                  _id:{ID:"$properties.ptype"}, 
                  max_dist:{$min:"$dist.calculated"}
                  }
       }
])

db.runCommand( { geoNear : msk ,
                 near : { type : "Point" ,
                          coordinates: [55.701, 37.709] } ,
                 spherical : true } )











