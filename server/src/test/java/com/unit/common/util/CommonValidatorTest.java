package com.unit.common.util;

import com.common.exception.ValidatorException;
import com.common.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.common.util.CommonValidator.validateNotNullArguments;
import static org.junit.jupiter.api.Assertions.*;

class CommonValidatorTest {

    @Test
    @DisplayName("Validate Not Null Arguments - Should throw exception - First null second value")
    void validateNotNullArguments_ShouldThrowException_FirstNullSecondValue() {
        ValidatorException exception = assertThrows(ValidatorException.class, () -> validateNotNullArguments(null, "TEST"));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Validate Not Null Arguments - Should throw exception - First value second null")
    void validateNotNullArguments_ShouldThrowException_FirstValueSecondNull() {
        ValidatorException exception = assertThrows(ValidatorException.class, () -> validateNotNullArguments(12, null));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Validate Not Null Arguments - Should not throw exception - First value second value")
    void validateNotNullArguments_ShouldNotThrowException() {
        assertDoesNotThrow(() -> validateNotNullArguments(12, "TEST"));
    }
}