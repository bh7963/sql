USE practice_sql;

CREATE TABLE employee (
	employee_number INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age INT ,
    defartment_code VARCHAR(2)
    );

CREATE TABLE defartment (
	defartment_code VARCHAR(2) PRIMARY KEY,
    name VARCHAR(30),
    tel_number VARCHAR(15)
);

ALTER TABLE employee 
ADD CONSTRAINT defartment_code_fk 
FOREIGN KEY (defartment_code)
REFERENCES defartment(department_code);

INSERT INTO defartment(defartment_code, name, tel_number) VALUES ('A', '경영부', '12345');
INSERT INTO defartment(defartment_code, name, tel_number) VALUES ('B', '영업부', '17745');
INSERT INTO defartment(defartment_code, name, tel_number) VALUES ('C', '재무부', '12865');

INSERT INTO employee(name, age, defartment_code) VALUES ('홍길동', '23', 'A');
INSERT INTO employee(name, age, defartment_code) VALUES ('이영희', '43', 'C');
INSERT INTO employee(name, age, defartment_code) VALUES ('김둘리', '29', 'D');
INSERT INTO employee(name, age, defartment_code) VALUES ('혁거세', '76', 'A');
INSERT INTO employee(name, age, defartment_code) VALUES ('박컴포즈', '45', 'C');
INSERT INTO employee(name, age, defartment_code) VALUES ('박퍼센트', '20', 'D');




-- Alias : 쿼리문에서 사용되는 별칭
-- 컬럼 및 테이블엥서 사용가능
-- 사용하는 이름을 변경하고 싶을 때 적용
SELECT 
	dpt.defartment_code '부서코드',
    dpt.name '부서명',
    dpt.tel_number '부서 전화번호'
FROM defartment dpt;

-- JOIN : 두 개 이상의 테이블을 특정 조건에 따라 
-- 		  조합하여 결과를 조회하고자 할때 사용하는 명령어
-- INNER JOIN : 두 테이블에서 조건이 일치하는 레코드만 반환
-- SELECT column, ... FROM 기준테이블 INNER JOIN 조합할테이블 ON 조인조건
SELECT E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        D.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
 FROM employee E 
INNER JOIN defartment D 
ON E.defartment_code = D.defartment_code;

-- LEFT OUTER JOIN : 기준 테이블의 모든 레코드와 조합할 테이블 중 조건에 일치하는 레코드만 반환
-- 만약에 조합할 테이블의 조건이 일치하는 레코드가 없으면 null로 표현
SELECT 
		E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        E.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
FROM employee E 
LEFT JOIN defartment D 
ON E.defartment_code = D.defartment_code;

-- RIGHT OUTER JOIN (RIGHT JOIN) : 조합할 테이블의 모든 레코드와 기준 테이블 중 조건에 일치하는 레코드만 반환
-- 만약 조건에 일치하는 레코드가 존재하지 않으면 null로 반환

SELECT E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        D.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
FROM employee E 
RIGHT JOIN defartment D 
ON E.defartment_code = D.defartment_code;

SELECT E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        D.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
FROM employee E 
RIGHT JOIN defartment D 
ON E.defartment_code = D.defartment_code;

-- FULL OUTER JOIN (FULL JOIN): 기준 테이블의 모든 레코드와 조합할 테이블의 모든 레코드를 반환
-- 기준 테이블과 조합할 테이블이 조건에 일치하는 레코드가 존재하지 않으면 null로 반환 
-- sql안에서는 full outer join을 문법상 제공하지 않음
-- FULL JOIN = LEFT JOIN + RIGHT JOIN
SELECT E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        D.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
FROM employee E 
LEFT JOIN defartment D 
ON E.defartment_code = D.defartment_code
UNION
SELECT E.employee_number '사번',
		E.name '이름',
        E.age '나이',
        D.defartment_code '부서코드',
        D.name '부서명',
        D.tel_number '전화번호'
FROM employee E 
RIGHT JOIN defartment D 
ON E.defartment_code = D.defartment_code;

-- CROSS JOIN : 기준 테이블의 각 레코드를 조합할 테이블의 모든 레코드에 조합하여 반환
-- CROSS JOIN 결과 레코드 수 = 기준 테이블의 레코드 수 * 조합 할 테이블의 레코드 수
SELECT *FROM employee E CROSS JOIN defartment D;
-- MYSQL에서 기본 조인이 CROSS JOIN형태 즉, JOIN을 사용하면 CROSS JOIN형태로 나옴(JOIN대신 ,찍어도 CROSS JOIN이 사용)
SELECT * FROM employee E JOIN defartment D;
SELECT * FROM employee E, defartment D;

-- 부서 코드가 A인 사원에대해 
-- 사번, 이름, 부서명을 조회하시오

SELECT
 E.employee_number '사번', 
 E.name '이름' , 
 D.name '부서명' 
FROM employee E INNER JOIN defartment D 
ON E.defartment_code = D.defartment_code
WHERE E.defartment_code = 'A';

-- 부서명이 '재무부'인 사원에 대해
-- 사번, 이름, 나이를 조회

SELECT 
E.employee_number '사번',
E.name '이름',
E.age '나이'
FROM employee E INNER JOIN defartment D
ON E.defartment_code = D.defartment_code
WHERE D.name = '재무부';  

-- 서브쿼리 : 쿼리 내부에 존재하는 또 다른 쿼리, 쿼리 결과를 조건이나 테이블로 사용할 수 있도록 함
-- 1. WHERE절에서 서브쿼리 : 조회 결과를 조건으로 사용하여 조건을 동적으로 지정할 수 있도록 함
-- 	  WHERE절에서 비교 연산증으로 사용할 때 조회하는 컬럼의 개수 및 레코드의 개수 주의 
SELECT employee_number, name, age 
FROM employee
WHERE defartment_code = (
	SELECT defartment_code 
    FROM defartment
    where name = '재무부'
); 

SELECT employee_number, name, age 
FROM employee
WHERE defartment_code = (
	SELECT * FROM defartment
    where name = '재무부'
); 

SELECT employee_number, name, age 
FROM employee
WHERE defartment_code = (
	SELECT defartment_code 
    FROM defartment
); 

SELECT employee_number, name, age 
FROM employee
WHERE defartment_code IN(
	SELECT *
    FROM defartment
); 


SELECT employee_number, age, name FROM employee
WHERE  defartment_code = (
	SELECT defartment_code
	FROM defartment
	WHERE name = '재무부'
);	

-- FROM절에서 서브쿼리 : 조회 결과 테이블을 다시 FROM절에서 재사용
-- 서브쿼리를 FROM절에서 사용할 땐 반드시 별칭을 사용해야함
SELECT 
Employee_number '사번',
name '이름',
age '나이'
FROM employee E INNER JOIN(
	SELECT * FROM defartment 
	WHERE name = '재무부';
) D
ON E.defartment_code = D.defartment_code;



