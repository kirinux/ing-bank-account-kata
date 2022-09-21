package fr.ing.interview.bankaccountkata.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorResponse {
    private Integer code = null;
    private String message = null;
    private String description = null;
}
