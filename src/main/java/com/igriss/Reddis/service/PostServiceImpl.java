package com.igriss.Reddis.service;

import com.igriss.Reddis.dto.PostCreateDto;
import com.igriss.Reddis.dto.PostUpdateDto;
import com.igriss.Reddis.entity.Post;
import com.igriss.Reddis.repository.PostRepository;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final Cache cache;

    public PostServiceImpl(PostRepository postRepository, CacheManager cacheManager) {
        this.postRepository = postRepository;
        this.cache = cacheManager.getCache("posts");
    }





    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    @SneakyThrows
    public Post get(Integer id) {
        Post cachedPost = cache.get(id, Post.class);
        if (cachedPost != null)
            return cachedPost;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(2);
        cache.put(id, post);
        return post;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        cache.evict(id); // delete from cache
    }

    @Override
    public void update(PostUpdateDto dto) {
        Post post = get(dto.getId());
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        postRepository.save(post);
        cache.put(dto.getId(), post);
    }
}
