package az.orient.msshopmodel.service;


import az.orient.msshopmodel.dto.ReqModel;
import az.orient.msshopmodel.dto.RespModel;
import az.orient.msshopmodel.dto.Response;
import az.orient.msshopmodel.entity.ModelEntity;
import az.orient.msshopmodel.exception.ModelNotFoundException;
import az.orient.msshopmodel.mapper.ModelMapper;
import az.orient.msshopmodel.repository.ModelRepository;
import az.orient.msshopmodel.type.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public RespModel createModel(ReqModel reqModel) {
        ModelEntity modelEntity = ModelMapper.INSTANCE.reqModelToModelEntity(reqModel);
        modelEntity.setStatus(Status.ACTIVE);
        ModelEntity savedEntity = modelRepository.save(modelEntity);
        return ModelMapper.INSTANCE.modelEntityToRespModel(savedEntity);
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
        ModelEntity modelEntity = modelRepository.findByIdAndStatus(id,Status.ACTIVE).orElseThrow(() -> new ModelNotFoundException("Model not found with id:" + id));
        modelEntity.setStatus(Status.DELETED);
        modelRepository.save(modelEntity);
    }

    public RespModel updateModelById(Long id, ReqModel reqModel) {
        ModelEntity modelEntity = modelRepository.findByIdAndStatus(id,Status.ACTIVE
        ).orElseThrow(() -> new ModelNotFoundException("Model not found with id:" + id));
        ModelEntity savedModelEntity = ModelMapper.INSTANCE.updateModelEntity(reqModel, modelEntity);
        ModelEntity savedEntity = modelRepository.save(savedModelEntity);
        return ModelMapper.INSTANCE.modelEntityToRespModel(savedEntity);


    }
}

