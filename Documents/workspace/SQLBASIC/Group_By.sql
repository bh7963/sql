USE practice_sql;

CREATE TABLE sale (
	sequnce_number INT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    amount INT, 
    imployee_number INT
);

INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-01', 100000, 1);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-01', 120000, 2);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-01', 60000, 1);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-03', 200000, 1);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-03', 170000, 3);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-06', 100000, 2);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-06', 160000, 3);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-07', 80000, 1);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-08', 100000, 2);
INSERT INTO sale(date, amount, imployee_number) VALUE('2024-07-08', 110000, 3);

SELECT * FROM sale;
-- 집계 함수: 여러행의 레코드를 종합하여 하나의 결과값을 반환
-- COUNT() : 특정 조건에 해당하는 레코드의 개수를 반환
SELECT COUNT(*) FROM sale;
SELECT COUNT(*) FROM sale WHERE amount <= 100000;
SELECT COUNT(amount) FROM sale;

-- SUM() : 특정 조건에 해당하는 컬럼의 값을 모든 더한 결과를 반환
SELECT SUM(amount) FROM sale;
SELECT SUM(amount) FROM sale WHERE imployee_number = 1;

-- AVG() : 특정 조건에 해당하는 컬럼의 값의 평균을 반환
SELECT AVG(amount) FROM sale;
SELECT AVG(amount) FROM sale WHERE imployee_number = 1;

-- MAX(), MIN() : 특정 조건에 해당하는 컬럼 값의 최대 최소 값을 반환
SELECT MAX(amount), MIN(amount) FROM sale;

-- 그룹화 (GROUP BY) : 조회 결과에 대해 레코드를 하나 이상의 컬럼으로 그룹화하여 결과를 도출
-- 일반적으로 집계함수와 함께 사용
SELECT AVG(amount), imployee_number 
FROM sale
GROUP BY imployee_number;

-- 집계함수가 포함되어 있는 쿼리 혹은 그룹화가 되었는 쿼리에서는 
-- 그룹화한 컬럼과 집계함수를 제외한 컬럼은 SELECT절에서 사용이 불가
SELECT AVG(amount), date
FROM sale
GROUP BY imployee_number;

SELECT AVG(amount), imployee_number, date
FROM sale
GROUP BY imployee_number, date;

-- 필터링(HAVING) : 그룹화된 결과에 필터 작업을 수행
-- 주의! WHERE절과 사용방법이 비슷하지만 WHERE절은 조회할 때 사용, 
-- HAVING절은 조회 결과에 사용
SELECT AVG(amount) '평균', imployee_number '사번'
FROM sale
GROUP BY imployee_number
HAVING AVG(amount) <= 110000;

-- 결과 테이블에 존재하지 않은 컬럼은 HAVING절에서 사용 불가
SELECT 
	AVG(amount) '평균', 
    imployee_number '사번',
    date '날짜'
FROM sale
GROUP BY imployee_number
HAVING date < '2024-07-05'