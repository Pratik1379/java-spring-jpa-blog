package com.pluralsight.blog;

import com.pluralsight.blog.data.CategoryRepository;
import com.pluralsight.blog.data.PostRepository;
import com.pluralsight.blog.model.Category;
import com.pluralsight.blog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;

    public BlogController(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String listPosts(ModelMap modelMap) {
        List<Post> posts = postRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        modelMap.put("posts", posts);
        modelMap.put("categories", categories);
        return "home";
    }

    @RequestMapping("/post/{id}")
    public String postDetails(@PathVariable Long id, ModelMap modelMap) {
        Post post = postRepository.findById(id).orElse(null);
        modelMap.put("post", post);
        return "post-details";
    }

    @RequestMapping("/category/{id}")
    public String categoryList(@PathVariable Long id, ModelMap modelMap) {
        Category category = categoryRepository.findById(id).orElse(null);
        List<Post> posts = category.getPosts();
        modelMap.put("category", category);
        modelMap.put("posts", posts);
        return "category-list";
    }

    @RequestMapping("/categories")
    public String listCategories(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAll();
        modelMap.put("categories", categories);
        return "allCategories";
    }

//    @RequestMapping("/categories/{categoryId}/posts")
//    public String listPostsByCategory(@PathVariable Long categoryId, ModelMap modelMap) {
//        Category category = categoryRepository.findById(categoryId).orElse(null);
//        List<Post> posts = category.getPosts();
//        modelMap.put("category", category);
//        modelMap.put("posts", posts);
//        return "category-list";
//    }
}
