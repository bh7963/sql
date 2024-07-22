-- 카타르 아시안컵 데이터
-- 참가국(국가별) -> 국가이름, 조, 감독, 피파랭킹
-- 선수 -> 선수이름, 나이, 포지션, 등번호, 소속 팀(국가)
-- 경기장 -> 경기장 이름, 수용인원, 주소
-- 심판 -> 이름, 나이, 국적, 포지션

-- 1. 카타르 아시안컵 데이터베이스 생성 -> qatar_asian_cup
-- 2. 개발자 계정 생성 -> 사용자명 : qatar_developer / 접근위치 : 모든곳에서 접근가능 / 비밀번호 : qatar123
-- 3. 테이블 생성 -> qatar_table
-- 4. 컬럼 생성 -> 참가국 : country (name[가변문자열 30], group_name[가변문자열 5], manager[가변문자열 30], ranking[정수])
--               선수 : player (name[가변문자열 30], age[정수], position[가변문자열 10], uniform_number[정수], contry[가변문자열 30])
--               경기장 : stadium(name[가변문자열 50], volume[정수], address[문자열])
--               심판 : referee (name[가변문자열 30], age[정수], contry[가변문자열 30], position[가변문자열 10]) 					
CREATE DATABASE qatar_aisan_cup;
USE qatar_aisan_cup;
CREATE USER 'qatar_developer'@'%' IDENTIFIED BY 'qatar123';
CREATE TABLE country(
	name VARCHAR(30),
    group_name VARCHAR(5),
    manager VARCHAR(30),
    ranking INT
);
CREATE TABLE player(
	name VARCHAR(30),
    age INT,
    position VARCHAR(10),
    uniform_number INT,
    country VARCHAR(30)
);
CREATE TABLE stadium (
	name VARCHAR(50),
    volume INT,
    address TEXT
);
CREATE TABLE referee (
	name VARCHAR(30),
    age INT,
    country VARCHAR(30),
    position VARCHAR(10)
);

