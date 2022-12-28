SELECT c.model AS "Модель", v.number AS "Вин-код" 
FROM cars AS c JOIN vincode AS v 
ON c.vincode_id = v.id;

SELECT model, number 
FROM cars JOIN vincode 
ON cars.vincode_id = vincode.id;

SELECT cc.model, vv.number 
FROM cars AS cc JOIN vincode AS vv 
ON cc.vincode_id = vv.id;