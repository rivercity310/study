package org.example.pwd;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

// 랜덤한 패스워드를 생성해주는 객체
public class RandomPasswordGenerator implements org.example.pwd.PasswordGenerator {
    /**
     * Special characters allowed in password.
     */
    public static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";
    public static final String ERROR_CODE = "ERRONEOUS_SPECIAL_CHARS";

    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return ALLOWED_SPL_CHARACTERS;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        // 0 ~ 12
        return gen.generatePassword((int) (Math.random() * 13), splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }
}