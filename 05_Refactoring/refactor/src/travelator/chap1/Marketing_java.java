package travelator.chap1;

public class Marketing_java {
    public static boolean isHotmailAddress_java(EmailAddress_kt address) {
        // getDomain()으로 코틀린 프로퍼티에 접근
        return address.getDomain().equalsIgnoreCase("hotmail.com");
    }
}
