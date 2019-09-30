package com.softcase.surc.bo.repos;

import com.softcase.surc.bo.domain.Template;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepo extends MongoRepository<Template, String> {

}
