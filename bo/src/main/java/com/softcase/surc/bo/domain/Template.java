package com.softcase.surc.bo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

/**
 * Survey Template under editing.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {

  @Id
  private String id;
  private String title;

  @Builder.Default
  private List<Question> questions = new ArrayList<>();

  public Template addQuestion(String id, String cat, String txt, String type, String... vals) {
    addQuestion(new Question(id, cat, txt, type, Arrays.asList(vals)));
    return this;
  }

  public Template addQuestion(Question question) {
    questions.add(question);
    return this;
  }

}
