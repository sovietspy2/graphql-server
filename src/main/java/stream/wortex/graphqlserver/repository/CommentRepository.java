package stream.wortex.graphqlserver.repository;

import org.springframework.data.repository.CrudRepository;
import stream.wortex.graphqlserver.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAll();
}
