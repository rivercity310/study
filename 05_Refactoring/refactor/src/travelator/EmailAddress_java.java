package travelator;

import java.util.Objects;

// 간단한 값 타입
public class EmailAddress_java {
    private final String localPart;
    private final String domain;

    public EmailAddress_java(String localPart, String domain) {
        this.localPart = localPart;
        this.domain = domain;
    }

    // Java의 가변 인자 함수 (...)
    public static void test(int... a) {
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
        System.out.println();
    }

    // Java의 split 메서드 인자는 Regex
    public static void test2() {
        String a = "12.345-6.A";
        String[] rst = a.split("\\.|-");

        for (String s : rst) System.out.println(s);
    }

    public static void main(String[] args) {
        test(1, 2, 3, 4, 5, 6, 7);
        test2();
    }

    // 문자열을 파싱해 EmailAddress를 만드는 정적 팩터리 메서드 -> 주 생성자를 호출
    public static EmailAddress_java parse(String value) {
        var atIndex = value.lastIndexOf('@');
        if (atIndex < 1 || atIndex == value.length() - 1)
            throw new IllegalArgumentException("EmailAddress must be two parts separated by @");

        return new EmailAddress_java(
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
        );
    }

    public String getLocalPart() { return localPart; }
    public String getDomain() { return domain; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress_java that = (EmailAddress_java) o;
        return localPart.equals(that.localPart) && domain.equals(that.domain);
    }

    @Override
    public int hashCode() { return Objects.hash(localPart, domain); }

    @Override
    public String toString() { return localPart + "@" + domain; }
}
