package stream.wortex.graphqlserver;

import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> graphQLErrors) {

        List<GraphQLError> errors = graphQLErrors.stream().map(err->{
            return new WortexGraphQLError(err.getMessage(), err.getErrorType(), err.getLocations());
        }).collect(Collectors.toList());

        return errors;
    }
}
