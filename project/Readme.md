
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
  - 주문 
    - 트랜잭션
  - 배송
    - 배송 상태
    - 배송 확정

- 추가 구현 기능
  - 중복 회원 아이디 가입 불가
  - 페이징
  - 아이템 이름 검색
  - 주문된 아이템은 삭제 불가
  - 검증 validation
  - 주문 고객에 대한 관리
  - 주소 API 활용
  - messages.properties
  - errors.properties
  - 상품 이미지 업로드
  - 이미지 예외 처리 runtimeException 으로 변경 해결
  - 테스트 데이터 베이스 설정 격리, 테스트를 반복적으로 할수 있게 실행
  - log trace 추가 AOP 매서드 적용(log)

- dependency
  - thymeleaf
  - validation
  - spring data jpa
  - jpa
  - querydsl
  - lombok
  - p6spy-spring-boot-starter 쿼리 확인용

- 데이터 H2


