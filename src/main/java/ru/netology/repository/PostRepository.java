package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {
  private final CopyOnWriteArrayList<Post> postRepository1 = new CopyOnWriteArrayList<>();

  public List<Post> all() {
    return postRepository1.stream().toList();
  }

  public Optional<Post> getById(long id) {
    if (postRepository1.size() >= id) {
      return Optional.of(postRepository1.get((int) (id - 1)));
    } else {
      throw new NotFoundException();
    }
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(postRepository1.size() + 1);
      postRepository1.add(post);
    } else {
      if (post.getId() <= postRepository1.size()) {
        postRepository1.get((int) (post.getId() - 1)).setContent(post.getContent());
      } else {
        throw new NotFoundException();
      }
    }
    return post;
  }

  public void removeById(long id) {
    if (postRepository1.size() < id) {
      throw new NotFoundException();
    } else {
      postRepository1.remove((int) (id - 1));
      for (int i = (int) (id - 1); i < postRepository1.size(); i++) {
        postRepository1.get(i).setId(i + 1);
      }
    }
  }
}
