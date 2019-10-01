package com.softcase.surc.bo.controllers;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softcase.surc.bo.commands.QuestionCmd;
import com.softcase.surc.bo.commands.TemplateCmd;
import com.softcase.surc.bo.domain.Template;
import com.softcase.surc.bo.repos.TemplateRepo;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TemplateCtlIT {

  private MockMvc mockMvc;
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired private WebApplicationContext wac;
  @Autowired private TemplateRepo templateRepo;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void loadTest() throws Exception {

    String res = mockMvc
        .perform(get(TemplateCtl.TEMPLATE + "/1").contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    
    // TODO complete the test with meaningful checks

  }

  @Test
  public void saveTest() throws Exception {

    String d = "description";
    TemplateCmd s0 = TemplateCmd.builder().title(d).id("id").build();
    s0.getQuestions()
        .add(new QuestionCmd("id", "category", "text", "type", mkList("val1", "val2", "val3")));

    String res = mockMvc.perform(post(TemplateCtl.TEMPLATE)
          .contentType(APPLICATION_JSON_UTF8)
          .content(mapper.writeValueAsBytes(s0))
        ).andExpect(status().isOk())
        .andReturn()
        .getResponse().getContentAsString();

    assertNotSame("",res);

    Template s = templateRepo.findById(res).orElse(Template.builder().title("NOT" + d).build());
    assertSame(d, s.getTitle());

  }

  private List<String> mkList(String... string) {
    return Arrays.asList(string);
  }

  public static final MediaType APPLICATION_JSON_UTF8 = 
      new MediaType(MediaType.APPLICATION_JSON.getType(), 
          MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

}
