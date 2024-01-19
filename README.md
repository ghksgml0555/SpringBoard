# SpringBoard

SpringBoot+JPA를 공부하고 만든 게시판 프로젝트

## 1. 프로젝트 개요

* 프로젝트 이름 : SpringBoard
* 프로젝트 소개 : SpringBoot와 JPA를 공부하고 실제로 사용해보기 위해서 제작한 개인 프로젝트입니다.

### 2. 개발환경

* 백엔드
  JAVA 17
  SpringBoot 3.2.1
  JPA(Spring Data JPA)
  Spring Security

* 프론트엔드
  html/css
  thymeleaf
  bootstrap

* database
  MariaDB

## 3. 프로젝트 기능

* 회원가입/로그인 기능
   회원가입, 로그인시 유효성 검사를 수행한다.
   로그인한 사용자는 화면 상단에 자신의 닉네임을 표시한다.
   로그인 하지 않은 사용자는 글 검색, 글 조회만 가능하다.
  
* 게시글 작성/조회/수정/삭제 기능
   게시글의 작성자만 수정, 삭제가 가능하다.
  
* 게시글에 댓글 작성 기능
  
* 제목/카테고리별 검색 기능
   제목, 카테고리, 제목+카테고리 검색이 각각 가능하다.
  
* 게시글 조회수 표시
  
* 페이징 처리



## 3. DB 구조
![image](https://github.com/ghksgml0555/SpringBoard/assets/100823964/9e73d5c7-4a80-494b-967a-752ce1969442)


  
### 회원기능

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

