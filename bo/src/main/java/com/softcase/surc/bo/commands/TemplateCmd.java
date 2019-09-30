package com.softcase.surc.bo.commands;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Survey under editing.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateCmd {
  private String id;
  private String description;

  @Builder.Default
  private List<QuestionCmd> questions = new ArrayList<>();

}
