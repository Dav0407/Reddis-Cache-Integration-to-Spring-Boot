package com.igriss.Reddis.controller;

import com.igriss.Reddis.dto.PostCreateDto;
import com.igriss.Reddis.dto.PostUpdateDto;
import com.igriss.Reddis.entity.Post;
import com.igriss.Reddis.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDto dto) {
        return ResponseEntity.status(201).body(postService.create(dto));
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable Integer id) {
        return postService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody PostUpdateDto dto) {
        postService.update(dto);
    }

}
