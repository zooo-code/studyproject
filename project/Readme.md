
entity order, orderItem 
중복아이디회원 가입 불가
아이템 등록

- 구현 기능
  - 회원
    - 회원 가입 
    - 회원 삭제
    - 회원 조회
    - 회원 수정
  - 아이템
    - 아이템 삭제
    - 아이템 등록
    - 아이템 조회
    - 아이템 수정
  - 주문
    - 주문 삭제
    - 주문 조회
    - 주문 등록
    - 주문 취소
  - 로그인
    - 비밀 번호 검증


- 추가 구현 기능
  - 중복 회원 아이디 가입 불가
  - 페이징
  - 아이템 이름 검색
  - 주문된 아이템은 삭제 불가
  - 각종 검증 validation
  - 나의 아이템만 보기
  - 나의 상품만 들어온 주문 보기
  - 나의 주문만 보기

- 차후에 추가 기능 아이템 카테고리
- 

- dependency
  - thymeleaf
  - validation
  - spring data jpa
  - jpa
  - querydsl
  - lombok
  - p6spy-spring-boot-starter 쿼리 확인용

- 데이터 H2
