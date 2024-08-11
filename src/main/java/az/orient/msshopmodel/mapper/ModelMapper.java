package az.orient.msshopmodel.mapper;


import az.orient.msshopmodel.dto.ReqModel;
import az.orient.msshopmodel.dto.RespModel;
import az.orient.msshopmodel.dto.Response;
import az.orient.msshopmodel.entity.ModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE= Mappers.getMapper(ModelMapper.class);

    ModelEntity reqModelToModelEntity(ReqModel reqModel);
    RespModel modelEntityToRespModel(ModelEntity modelEntity);
    List<RespModel> modelEntityToRespModelList(List<ModelEntity> modelEntities);
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    ModelEntity updateModelEntity(ReqModel reqModel,@MappingTarget ModelEntity modelEntity);

}
