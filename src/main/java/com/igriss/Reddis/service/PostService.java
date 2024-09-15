package com.igriss.Reddis.service;

import com.igriss.Reddis.dto.PostCreateDto;
import com.igriss.Reddis.dto.PostUpdateDto;
import com.igriss.Reddis.entity.Post;

public interface PostService {
    Post create(PostCreateDto dto);

    Post get(Integer id);

    void delete(Integer id);
    void update(PostUpdateDto dto);
}
