package az.orient.msshopmodel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespModel {
    private String name;
    private String description;

}
