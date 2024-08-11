package az.orient.msshopmodel.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldException {
    private String fieldName;
    private String errorMessage;

}
