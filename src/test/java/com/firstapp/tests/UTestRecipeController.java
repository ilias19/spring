package com.firstapp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.firstapp.controllers.RecipeController;
import com.firstapp.dto.RecipeDto;
import com.firstapp.exceptions.EntityNotFoundException;
import com.firstapp.services.RecipeServiceImp;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RecipeController.class, secure = false)
public class UTestRecipeController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RecipeServiceImp service;
	
	@Test
	public void testGetRecipeByName() throws Exception{
		
		RecipeDto recipe = RecipeDto.builder().id(1L).name("name1").imagePath("imagePath1").description("description1").build();
		
		Mockito.when(
				service.getRecipeByName(Mockito.anyString())).thenReturn(recipe);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/recipes/getByName/name1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:name1,description:description1,imagePath:imagePath1}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

}
