package com.igriss.Reddis.repository;

import com.igriss.Reddis.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}