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
