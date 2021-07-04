package com.common.util;

import com.common.exception.ValidatorException;
import com.common.model.ErrorMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonValidator {

    public static void validateNotNullArguments(final Object... values) {
        for (Object value : values) if (value == null) throw new ValidatorException(ErrorMessage.C02);
    }

}
