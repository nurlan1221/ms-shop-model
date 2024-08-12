package az.orient.msshopmodel.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelWithBrandDto {
    private Long modelId;
    private String modelName;
    private String modelDescription;
    @JsonProperty(value = "brandName")
    private String name;
    @JsonProperty(value = "brandDescription")
    private String description;
}
