package com.vladislin.blog.repo;

import com.vladislin.blog.models.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {
    Institution findByTitle(String s);
}
