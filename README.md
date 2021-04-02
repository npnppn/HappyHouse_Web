# `HappyHouse_Java_서울_09반_금아현_김민지_박채린_박형민_양영진_이지훈`



# 목표

![img](images/unknown.png)



# 제출

제출 내역은 다음과 같습니다.

- 기본
  - 메인 화면
  - 실거래가 검색, 결과 : 동별 검색 처리
  - 실거래가 검색, 결과 : 아파트별 검색 처리
  - 회원 관리 : 회원정보 등록,수정,삭제,목록조회,상세조회
  - 로그인 / 로그아웃
- 심화
  - 공지사항 관리 : 공지사항 등록,수정,삭제,목록조회,상세조회



# 캡쳐

**기본**

1. 메인화면

![image-20210402205003503](images/메인화면.png) 

2. 실거래가 검색

![2(아파트 실거래 정보 출력)](images/2(%EC%95%84%ED%8C%8C%ED%8A%B8%20%EC%8B%A4%EA%B1%B0%EB%9E%98%20%EC%A0%95%EB%B3%B4%20%EC%B6%9C%EB%A0%A5).PNG)



3. 회원관리

![회원가입](images/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.PNG)

4. 로그인/로그아웃

![로그인](images/%EB%A1%9C%EA%B7%B8%EC%9D%B8.PNG) 

![로그인 성공](images/%EB%A1%9C%EA%B7%B8%EC%9D%B8%20%EC%84%B1%EA%B3%B5.PNG)



**심화** 

5. 공지사항

- 등록

![공지사항 등록](images/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%20%EB%93%B1%EB%A1%9D.png)

- 검색

![공지사항 검색](images/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%20%EA%B2%80%EC%83%89.png)

- 수정

![공지사항 수정](images/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%20%EC%88%98%EC%A0%95.png)

삭제 전

![공지사항 삭제(삭제 전)](images/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%20%EC%82%AD%EC%A0%9C(%EC%82%AD%EC%A0%9C%20%EC%A0%84).png)

삭제 후

![공지사항 삭제(삭제 후)](images/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%20%EC%82%AD%EC%A0%9C(%EC%82%AD%EC%A0%9C%20%ED%9B%84).png)





## 공지사항

**DB**

- MySQL 5.1 사용

- happyhouse 데이터베이스에 article table을 추가했다. 관련 sql 파일은 /res/happyhouse_article.sql

**Model** 

- OpenJDK8 활용. 

- 자바 클래스 ArticleService, ArticleDao, ArticleDto를 이용해 모델을 구축했다.

**View**

- JSP의 EL/JSTL, Session 활용

- /Webcontent/article/ 에 있는 jsp 파일들을 이용해 공지사항 서비스의 View를 제공한다.



참고자료

http://happyhouse.jaen.kr/