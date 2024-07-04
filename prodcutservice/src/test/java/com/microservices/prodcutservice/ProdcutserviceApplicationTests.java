package com.microservices.prodcutservice;

import com.microservices.prodcutservice.dto.ProductRequest;
import com.microservices.prodcutservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProdcutserviceApplicationTests {


	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.12");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;




	@DynamicPropertySource
	static void  setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);

	}
	@Test
	void createProduct(){
		try {

			ObjectMapper objectMapper= new ObjectMapper();
			ProductRequest productRequest = ProductRequest.builder()
							.name("iphone 13")
									.description("apple")
											.price(BigDecimal.valueOf(750000))
													.build();
			mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(productRequest)))
					.andExpect(status().isCreated());

			Assertions.assertEquals(1,productRepository.findAll().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
