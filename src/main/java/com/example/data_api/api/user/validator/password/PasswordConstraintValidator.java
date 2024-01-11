package com.example.data_api.api.user.validator.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {


    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(

                        //Length rule. Min 10 max 128 characters
                        new LengthRule(8, 30),

                        //At least 1 upper case
                        new CharacterRule(EnglishCharacterData.UpperCase,1),

                        //At least 1 lower case
                        new CharacterRule(EnglishCharacterData.LowerCase,1),

                        //At least 1 number
                        new CharacterRule(EnglishCharacterData.Digit,1),

                        //At least 1 special character
                        new CharacterRule(EnglishCharacterData.Special,1),

                        //Allow white space
                        new WhitespaceRule()
                )
        );

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        //send one message each time failed validation.
        context.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();


        return false;
    }
}
