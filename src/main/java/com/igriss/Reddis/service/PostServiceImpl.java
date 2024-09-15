package com.igriss.Reddis.service;

import com.igriss.Reddis.dto.PostCreateDto;
import com.igriss.Reddis.dto.PostUpdateDto;
import com.igriss.Reddis.entity.Post;
import com.igriss.Reddis.repository.PostRepository;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    @SneakyThrows
    @Cacheable(value = "posts", key = "#id")
    public Post get(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(2);
        return post;
    }

    @Override
    @CacheEvict(value = "posts", key = "#id")
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "posts", key = "#dto.id")
    public Post update(PostUpdateDto dto) {
        Post post = get(dto.getId());
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        return postRepository.save(post);
    }

    @Override
    @Cacheable(value = "posts", key = "#root.methodName")
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
