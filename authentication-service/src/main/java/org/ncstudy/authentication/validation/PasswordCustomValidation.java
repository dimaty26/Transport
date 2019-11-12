package org.ncstudy.authentication.validation;

import org.passay.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordCustomValidation {
    private String messageTemplate;

    public boolean isValid(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 20),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid())
            return true;

        List<String> messages = validator.getMessages(result);
        messageTemplate = messages.stream().collect(Collectors.joining(","));
        return false;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}