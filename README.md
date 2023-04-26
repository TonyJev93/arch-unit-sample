# ArchUnit

애플리케이션 아키텍처의 일관성을 검증하고 아키텍처의 변경으로 인한 예기치 않은 부작용을 방지하기 위해 사용

## 동작 원리

- Java 바이트 코드를 분석
    - 이를 통해 클래스, 패키지, 인터페이스, 어노테이션 및 다른 요소들 간의 관계와 의존성 확인
- 구조 검사를 위해 ASM 라이브러리 사용
    - ASM : Java 바이트 코드 조작 및 분석을 위한 라이브러리
        - 직접 바이트 코드를 수정하는 기능을 제공 -> 런타임 시점에 클래스 파일 변경 가능
        - 제공 기능
            - 바이트 코드 생성 및 수정
            - 클래스, 필드, 메소드 및 어노테이션 정보 분석
            - 바이트 코드 검증 및 최적화

## 구성

### ArchRule 클래스

- 테스트 규칙을 정의하는데 사용되는 클래스
- 코드베이스의 특정 부분을 검사하기 위한 특정 조건을 정의
    - ex) 클래스의 패키지, 필드 또는 메서드가 다른 패키지의 클래스에만 접근해야 한다. (ArchRuleTest.java 참고)

### ArchCondition 클래스

- `ArchRule`에서 정의한 조건이 참인지 여부를 결정하는데 사용되는 클래스
- 검사 대상 코드에 대한 추가 검증로직 제공
    - ex) 클래스가 특정 인터페이스를 구현하거나 어노테이션을 가지고 있는지 여부 확인. (ArchConditionTest.java 참고)

### JavaClasses 객체

- 검사 대상 클래스들을 나타내는 ArchUnit의 내부 데이터 구조
- 일반적으로 ArchUnit 규칙 실행 전 @BeforeAll 메서드를 통해 JavaClasses 객체를 초기화 한다.

```java
public class MyArchitectureTest {

    private static JavaClasses myClasses;

    @BeforeAll
    public static void setUp() {
        myClasses = new ClassFileImporter().importPackages("com.example");
    }

    // ...
}
```