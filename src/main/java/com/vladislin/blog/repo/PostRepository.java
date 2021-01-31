package com.vladislin.blog.repo;

import com.vladislin.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post, Long>{
}
