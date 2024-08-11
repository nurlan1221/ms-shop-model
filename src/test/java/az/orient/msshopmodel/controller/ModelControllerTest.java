package az.orient.msshopmodel.controller;

import az.orient.msshopmodel.dto.ReqModel;
import az.orient.msshopmodel.exception.ModelNotFoundException;
import az.orient.msshopmodel.service.ModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ModelController.class)
@ExtendWith(MockitoExtension.class)
class ModelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelService modelService;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createModelGivenValidReqModelAndThenReturn201() throws Exception {
        ReqModel reqModel = new ReqModel();
        reqModel.setName("Mock");
        reqModel.setDescription("Mock");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/models")
                .content(objectMapper.writeValueAsString(reqModel))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 201);
    }

    @Test
    void createModelGivenInvalidReqModelAndThenReturn400() throws Exception {
        ReqModel reqModel = new ReqModel();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/models")
                .content(objectMapper.writeValueAsString(reqModel))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 400);
    }
    @Test
    void updateModelGivenValidReqModelAndThenReturn200() throws Exception {

        Long id = 1L;
        ReqModel reqModel = new ReqModel();
        reqModel.setName("mock");
        reqModel.setDescription("mock");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/models/"+id)
                .content(objectMapper.writeValueAsString(reqModel))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
    }
    @Test
    void updateModelGivenInvalidReqModelAndThenReturn404() throws Exception {
        Long id = 1L;
        ReqModel reqModel = new ReqModel();
        reqModel.setName("mock");
        reqModel.setDescription("mock");

        ModelNotFoundException exception=new ModelNotFoundException("Model not found with id:"+id);
        when(modelService.updateModelById(id,reqModel)).thenThrow(exception);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.put("/models/"+id)
                .content(objectMapper.writeValueAsString(reqModel))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus());

    }

    @Test
    void deleteModelGivenValidIdAndThenReturn200() throws Exception {
        Long id = 1L;
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete("/models/"+id)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
        verify(modelService).deleteModelById(id);

    }
    @Test
    void deleteModelGivenInvalidIdAndThenReturn404() throws Exception {
        Long id = 1L;
        doThrow(new ModelNotFoundException("Model not found with id: 1")).when(modelService).deleteModelById(id);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete("/models/"+id)).andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus());
        verify(modelService).deleteModelById(id);
    }

    @Test
    void getModelGivenValidIdAndThenReturn200() throws Exception {
        Long id = 1L;
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/models/"+id)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
        verify(modelService).getModelById(id);
    }
    @Test
    void getModelGivenInvalidIdAndThenReturn404() throws Exception {
        Long id = 1L;
        when(modelService.getModelById(id)).thenThrow(new ModelNotFoundException("Model not found with id: 1"));
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/models/"+id)).andReturn();
        assertEquals(404,mvcResult.getResponse().getStatus());
        verify(modelService).getModelById(id);

    }





}