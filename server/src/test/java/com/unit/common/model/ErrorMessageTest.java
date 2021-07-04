package com.unit.common.model;

import com.common.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.IllegalFormatConversionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorMessageTest {

    @Test
    @DisplayName("Put values into message - Should return message with values")
    void putValuesIntoMessage_ShouldReturnMessageWithValues() {

        String result = ErrorMessage.putValuesIntoMessage(ErrorMessage.C03, 1, 3);

        assertEquals("Value 1 is bigger than 3", result);
    }

    @Test
    @DisplayName("Put values into message - Should throw exception")
    void putValuesIntoMessage_ShouldThrowException() {

        assertThrows(IllegalFormatConversionException.class, () ->
                ErrorMessage.putValuesIntoMessage(ErrorMessage.C03, 1, "test"));

    }
}
