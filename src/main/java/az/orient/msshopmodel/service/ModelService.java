package az.orient.msshopmodel.service;


import az.orient.msshopmodel.dto.*;
import az.orient.msshopmodel.entity.ModelEntity;
import az.orient.msshopmodel.exception.ModelNotFoundException;
import az.orient.msshopmodel.mapper.ModelMapper;
import az.orient.msshopmodel.repository.ModelRepository;
import az.orient.msshopmodel.serviceclient.BrandServiceClient;
import az.orient.msshopmodel.type.Status;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final BrandServiceClient brandServiceClient;

    public RespModel createModel(ReqModel reqModel) {
        ModelEntity modelEntity = ModelMapper.INSTANCE.reqModelToModelEntity(reqModel);
        modelEntity.setStatus(Status.ACTIVE);
        ModelEntity savedEntity = modelRepository.save(modelEntity);
        return ModelMapper.INSTANCE.modelEntityToRespModel(savedEntity);
    }
    public List<ModelWithBrandDto> getModelsByBrandId(Long brandId) {
        // Fetch models associated with the given brandId
        List<ModelEntity> models = modelRepository.findByBrandId(brandId);
        // Fetch brand information using BrandServiceClient
        ModelWithBrandDto brandDto = brandServiceClient.getBrandById(brandId);

        // Combine ModelEntity and BrandDto into ModelWithBrandDto
        return models.stream().map(model -> ModelWithBrandDto.builder()
                        .modelId(model.getId())
                        .modelName(model.getName())
                        .modelDescription(model.getDescription())
                        .name(brandDto.getName())
                        .description(brandDto.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public List<RespModel> getAllModels() {
        List<ModelEntity> modelEntities = modelRepository.findAllByStatus(Status.ACTIVE);
        return ModelMapper.INSTANCE.modelEntityToRespModelList(modelEntities);
    }

    public RespModel getModelById(Long id) {
        ModelEntity modelEntity = modelRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ModelNotFoundException("Model not found with id:" + id));
        return ModelMapper.INSTANCE.modelEntityToRespModel(modelEntity);
    }

    public void deleteModelById(Long id) {
        ModelEntity modelEntity = modelRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ModelNotFoundException("Model not found with id:" + id));
        modelEntity.setStatus(Status.DELETED);
        modelRepository.save(modelEntity);
    }

    public RespModel updateModelById(Long id, ReqModel reqModel) {
        ModelEntity modelEntity = modelRepository.findByIdAndStatus(id, Status.ACTIVE
        ).orElseThrow(() -> new ModelNotFoundException("Model not found with id:" + id));
        ModelEntity savedModelEntity = ModelMapper.INSTANCE.updateModelEntity(reqModel, modelEntity);
        ModelEntity savedEntity = modelRepository.save(savedModelEntity);
        return ModelMapper.INSTANCE.modelEntityToRespModel(savedEntity);
    }
}

