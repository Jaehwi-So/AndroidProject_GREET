# GREET!
## 모바일 전자명함 거래 어플리케이션
--------------------
### 프로젝트 기간
2020/08/03 ~ 2020/08/13
* 2020/08/03 : 아이디어 구성
* 2020/08/03 - 2020/08/06 : 명함 커스터마이징, 저장 기능, 서버 구축, 데이터베이스 구축
* 2020/08/07 - 2020/08/10 : 서버 연동, 요청 전송, 응답 성공. 명함 리스트 저장기능 완료
* 2020/08/11 - 2020/08-13 : 명함 커스터마이징 디자인 완료. 전체 어플리케이션 디자인 완료, 검증, 최종 완성
   
* 2020/08/23 - 2020/09/01 : 버전(20200901ver) 업데이트. 서버 RESTful API로 재구성, 안드로이드 UI 업데이트, API 사용 업데이트

### 팀원
- 소재휘 : 서버 API 구축, 데이터 입출력 모듈 인터페이스 생성 및 기능 구현, 명함 목록 가져오기, 저장, 리스트 동적 기능 구현
- 김해인 : 명함 제작 구현, 명함 디자인 레이아웃 구현, UI 디자인
- 서지수 : 제작 명함 레이아웃 스크린샷 기능 구현
- 이지환 : 명함 목록 레이아웃, 명함 리스트 동적 기능 구현
- 박현규 : 공유 기능 구현, 뷰 페이저 동적 처리 구현, 명함 생성 후 내부 저장소 저장, 권한 처리 구현

### 사용 프로그램 및 기술
- Android Studio Oreo (8.1)
- Eclipse IDE
- Apache Tomcat 8.5
- JAVA
- (구)JSP
- Spring Framework 3.1
- Oracle Database 11g
- MyBatis
-------------------
- [1. 프로그램 소개](#1-프로그램-소개)
- [2. 제공하는 시스템 목록](#2-제공하는-시스템-목록)
- [3. 프로젝트 분석](#3-프로젝트-분석)
- [4. 사용 사례](#4-사용-사례)
- [5. 평가와 보완점](#5-평가와-보완점)
-------------------
## 1. 프로그램 소개

- ### GREET
	* 전자 명함 제작 및 공유의 목적으로 만든 어플리케이션
	* GREET : "인사하다"라는 뜻. 명함을 주고받는 것 또한 처음 만난 사람들끼리 인사하는 방법이다.

- ### Who?
	* 기업 임직원들의 비즈니스 용도는 물론 개인들도 사용하기 용이하다.

- ### Why?
	* 명함을 만드는데 드는 비용의 절감
	* 모바일로 명함을 휴대 시 관리하기 쉬우며 분실 염려가 없음
	* 원격 온라인 상에서의 명함 거래가 가능


--------------

## 2. 제공하는 시스템 목록
- 자신만의 명함을 제작하여 내부 저장소에 사진의 형태로 저장하여 명함을 생성해줌.
- 명함을 제작할 시 6가지의 디자인을 골라 커스터마이징 할 수 있음.
- 명함 생성 시 고유 검색키가 부여된다. 이 검색키의 공유를 통해 타인에게 명함을 전달 가능하다.
- 검색키 값을 통해 타인의 명함을 내 어플리케이션의 명함 목록에 저장할 수 있다.
- 문자, 이메일 등으로도 공유 가능


---------------

## 3. 프로젝트 분석

- ## Andoroid
<img src="/img_readme/project_analyze.png" width="600" height="300"></img>

- ## API 서버
### (구)JSP 서버     
<img src="/img_readme/project_analyze2.png" width="400" height="300"></img>    
JSP를 이용하여 간단하게 서버를 구축하였지만 성능 향상과 유지보수를 위해 RESTful API 서버를 재구축하였다.

### (20200901 update)RESTful API 서버    
<img src="/img_readme/project_analyze3.png" width="600" height="300"></img>    
RESTful API 서버를 구축하여 CURD 연산에 대한 안드로이드 측에서 API 사용법이 간단해졌으며    
Json 포멧 타입의 교환으로 성능과 호환성을 증가시켰다.

----------------

## 4. 사용 사례
<img src="/img_readme/project_ex1.png" width="700" height="350"></img>
<img src="/img_readme/project_ex2.png" width="700" height="350"></img>
<img src="/img_readme/project_ex3.png" width="700" height="350"></img>
<img src="/img_readme/project_ex4.png" width="700" height="350"></img>
<img src="/img_readme/project_ex5.png" width="700" height="350"></img>
<img src="/img_readme/project_ex6.png" width="700" height="350"></img>
<img src="/img_readme/project_ex7.png" width="700" height="350"></img>

-------------

## 5. 평가와 보완점
- 오프라인에서 명함을 제작하고 주고받는 행위를 대신하여 어플리케이션이라는 온라인 매체를 통해 같은 업무를 수행할 수 있다.
- 즉 이는 명함 교환이라는 본분에 충실한 어플리케이션이므로 사용자의 요구사항을 만족시켰을 것이라고 평한다.
- Open API를 통한 타 SNS와의 연동 기능을 넣으면 더욱 원활한 소셜 네트워킹 기능을 할 수 있을 것이다.
- 검색 Key를 대체하여 더 효율적인 명함 교환 방법을 사용한다면 사용자의 만족도가 더욱 올라가고 보안성이 높아질 것이라고 생각한다.
- JSP로 서버를 임의로 구축하다 보니 팀원이 API를 사용할 때 항상 설명이 필요했다. 따라서 RESTful API 서버로 바꾼다면 정해진 틀 안에서 API의 사용이 수월해질 것이다.(20200901 UPDATE 완료!)






