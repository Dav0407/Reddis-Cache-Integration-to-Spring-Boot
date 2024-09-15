package com.igriss.Reddis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igriss.Reddis.entity.Post;
import com.igriss.Reddis.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
@Slf4j
@EnableScheduling
public class UsingReddisInSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsingReddisInSpringBootApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper, PostRepository postRepository) {
        return args -> {
            List<Post> posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), new TypeReference<>() {
            });
            postRepository.saveAll(posts);
        };
    }

    @CacheEvict(value = "posts", allEntries = true)
    @Scheduled(initialDelay = 8, fixedDelay = 4, timeUnit = TimeUnit.SECONDS)
    public void deleteAllCachedPosts() {
        log.info("All Entries of posts cache is Flushing");
    }

}
