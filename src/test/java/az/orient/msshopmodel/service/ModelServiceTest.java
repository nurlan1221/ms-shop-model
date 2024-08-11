package az.orient.msshopmodel.service;

import az.orient.msshopmodel.dto.ReqModel;
import az.orient.msshopmodel.dto.RespModel;
import az.orient.msshopmodel.entity.ModelEntity;
import az.orient.msshopmodel.exception.ModelNotFoundException;
import az.orient.msshopmodel.mapper.ModelMapper;
import az.orient.msshopmodel.repository.ModelRepository;
import az.orient.msshopmodel.type.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelServiceTest {
    @Mock
    ModelRepository modelRepository;

    @InjectMocks
    ModelService modelService;


    @Test
    void updateModelGivenValidIdThenReturnUpdatedModel() {

        Long id = 1L;
        ReqModel reqModel = new ReqModel();
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("Mock");
        when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.of(modelEntity));
        when(modelRepository.save(modelEntity)).thenReturn(modelEntity);

        RespModel respModel = modelService.updateModelById(id, reqModel);

        assertNotNull(respModel);
        verify(modelRepository, Mockito.times(1)).save(modelEntity);
    }

    @Test
    void updateModelGivenInvalidIdThenReturnUpdatedModel() {
        Long id = 1L;

        when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.empty());
        ModelNotFoundException modelNotFoundException = assertThrows(ModelNotFoundException.class, () -> modelService.updateModelById(id, new ReqModel()));
        assertNotNull(modelNotFoundException);
    }

    @Test
    void getModelGivenValidIdThenReturnModel() {
        Long id = 1L;
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("Mock");
        when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.of(modelEntity));
        RespModel respModel = modelService.getModelById(id);
        assertNotNull(respModel);
    }

    @Test
    void getModelGivenInvalidIdThenReturn404() {
        Long id = 1L;
        when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.empty());
        ModelNotFoundException modelNotFoundException= assertThrows(ModelNotFoundException.class, () -> modelService.getModelById(id));
        assertNotNull(modelNotFoundException);
    }

    @Test
    void deleteModelGivenValidIdThenReturnTrue() {
        Long id = 1L;
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(id);
        modelEntity.setStatus(Status.ACTIVE);
        when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.of(modelEntity));
        modelService.deleteModelById(id);
        verify(modelRepository).findByIdAndStatus(id,Status.ACTIVE);
        verify(modelRepository).save(modelEntity);
        assertEquals(Status.DELETED, modelEntity.getStatus());
    }
    @Test
    void deleteModelGivenInvalidIdThenReturn404() {
       Long id = 1L;
       when(modelRepository.findByIdAndStatus(id, Status.ACTIVE)).thenReturn(Optional.empty());
       ModelNotFoundException modelNotFoundException= assertThrows(ModelNotFoundException.class, () -> modelService.deleteModelById(id));
       assertNotNull(modelNotFoundException);
    }


}