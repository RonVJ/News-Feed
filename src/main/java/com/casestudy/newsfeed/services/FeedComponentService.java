package com.casestudy.newsfeed.services;

import com.casestudy.newsfeed.exceptions.FeedComponentDoesNotExist;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Comment;
import com.casestudy.newsfeed.models.FeedComponent;
import com.casestudy.newsfeed.models.Post;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.repositories.CommentRepository;
import com.casestudy.newsfeed.repositories.PostRepository;
import com.casestudy.newsfeed.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class FeedComponentService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public FeedComponentService(UserRepository userRepository
    , PostRepository postRepository
    , CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Post post(Long userId, String description) throws UserDoesNotExistException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();
        Post post = new Post();
        post.setAuthor(user);
        post.setDescription(description);
        return postRepository.save(post);
    }

    public Comment comment(Long userId, String description, Long parentFeedComponentId)
            throws UserDoesNotExistException, FeedComponentDoesNotExist {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        FeedComponent parentFeedComponent = null;

        Optional<Post> postOptional = postRepository.findById(parentFeedComponentId);
        if(postOptional.isEmpty()) {
            Optional<Comment> commentOptional = commentRepository.findById(parentFeedComponentId);
            if(commentOptional.isEmpty()) {
                throw new FeedComponentDoesNotExist();
            }
            else {
                parentFeedComponent = commentOptional.get();
            }
        }
        else {
            parentFeedComponent = postOptional.get();
        }


        User user = userOptional.get();
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setDescription(description);
        comment.setParentFeedComponent(parentFeedComponent);
        return commentRepository.save(comment);
    }
}
