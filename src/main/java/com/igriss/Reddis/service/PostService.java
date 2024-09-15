package com.igriss.Reddis.service;

import com.igriss.Reddis.dto.PostCreateDto;
import com.igriss.Reddis.dto.PostUpdateDto;
import com.igriss.Reddis.entity.Post;

import java.util.List;

public interface PostService {
    Post create(PostCreateDto dto);

    Post get(Integer id);

    void delete(Integer id);
    Post update(PostUpdateDto dto);
    List<Post> getAll();
}
