package jp.te4a.spring.boot.myapp11;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@ContextConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
	@Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    WebClient webClient;
    
    @BeforeAll
    public void テスト前処理() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/templates");
	    viewResolver.setSuffix(".html");
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @BeforeEach
    void 前処理() {
    	webClient = MockMvcWebClientBuilder
    			.webAppContextSetup(wac)
    			.build();
    }
    
    @Test
    public void RedirectToList() throws Exception {
        MvcResult result = mockMvc.perform(  get("/books")  )
            .andExpect(  status().is2xxSuccessful()  )
            .andExpect(  view().name("books/list")  )
            .andReturn();
    }
    @Test
    public void CreateForm() throws Exception {
        MvcResult result = mockMvc.perform(  get("/books")  )
    		.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andReturn();
        
        String url = result.getRequest().getRequestURL().toString();
        HtmlPage page = webClient.getPage(url);
        
        List<DomElement> inputForms = Arrays.asList(
            	page.getElementById("title"),
            	page.getElementById("writter"),
            	page.getElementById("publisher"),
            	page.getElementById("price")
    		);
    	assertThat(inputForms).doesNotContainNull();
    }
}
