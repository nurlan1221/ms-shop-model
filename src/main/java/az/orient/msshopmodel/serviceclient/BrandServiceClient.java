package az.orient.msshopmodel.serviceclient;


import az.orient.msshopmodel.dto.BrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brand-service",url = "${brand.service.url}")
public interface BrandServiceClient {

    @GetMapping("/api/brands/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id);

}
