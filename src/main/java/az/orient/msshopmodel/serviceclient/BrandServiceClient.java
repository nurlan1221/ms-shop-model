package az.orient.msshopmodel.serviceclient;


import az.orient.msshopmodel.dto.ModelWithBrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-shop-brand", url = "${brand.service.url}/brands")
public interface BrandServiceClient {

    @GetMapping(path = "{id}")
    ModelWithBrandDto getBrandById(@PathVariable Long id);
}
