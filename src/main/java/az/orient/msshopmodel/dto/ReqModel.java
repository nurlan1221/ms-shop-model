package az.orient.msshopmodel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class ReqModel {
    @NotNull(message = "Model name is required")
    private String name;
    private String description;
}
