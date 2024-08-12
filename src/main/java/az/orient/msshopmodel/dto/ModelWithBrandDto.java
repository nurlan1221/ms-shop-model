package az.orient.msshopmodel.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelWithBrandDto {
    private Long modelId;
    private String modelName;
    private String modelDescription;
    private String brandName;
    private String brandDescription;

}
