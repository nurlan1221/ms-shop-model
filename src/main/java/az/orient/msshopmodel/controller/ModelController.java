package az.orient.msshopmodel.controller;

import az.orient.msshopmodel.dto.ModelWithBrandDto;
import az.orient.msshopmodel.dto.ReqModel;
import az.orient.msshopmodel.dto.RespModel;
import az.orient.msshopmodel.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespModel createModel(@Valid @RequestBody ReqModel reqModel) {
        return modelService.createModel(reqModel);
    }

    @GetMapping("/by-brand/{brandId}")
    public List<ModelWithBrandDto> getModelsByBrandId(@PathVariable Long brandId) {
//        List<ModelWithBrandDto> modelsWithBrand = modelService.getModelsByBrandId(brandId);
//        return ResponseEntity.ok(modelsWithBrand);
        return modelService.getModelsByBrandId(brandId);
    }

    @GetMapping
    public List<RespModel> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping(path = "{id}")
    public RespModel getModelById(@PathVariable Long id) {
        return modelService.getModelById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteModelById(@PathVariable Long id) {
        modelService.deleteModelById(id);
    }

    @PutMapping(path = "{id}")
    public RespModel updateModelById(@PathVariable Long id, @RequestBody @Valid ReqModel reqModel) {
        return modelService.updateModelById(id, reqModel);
    }

}
