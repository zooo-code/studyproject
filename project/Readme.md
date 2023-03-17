
중복아이디회원 가입 불가

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
  - 검증 validation
  - 나의 아이템만 보기
  - 나의 상품만 들어온 주문 보기
  - 나의 주문만 보기
  - 주소 검색 API 를 활용
  - messages.properties
  - errors.properties
  - 상품 이미지 업로드

- 차후에 추가 기능 아이템 카테고리

- dependency
  - thymeleaf
  - validation
  - spring data jpa
  - jpa
  - querydsl
  - lombok
  - p6spy-spring-boot-starter 쿼리 확인용

- 데이터 H2

- controller 예외 상황 수정 해야함
- 해야할것
  - admin 만들기
  - 수정 이미지 업로드 item