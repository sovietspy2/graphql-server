package stream.wortex.graphqlserver.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stream.wortex.graphqlserver.CommentPublisher;
import stream.wortex.graphqlserver.model.Comment;
import stream.wortex.graphqlserver.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService implements GraphQLQueryResolver, GraphQLSubscriptionResolver {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Autowired
    private CommentPublisher commentPublisher;


    public Publisher<Comment> anyCommentAdded() {
        return commentPublisher.getPublisher();
    }



}
