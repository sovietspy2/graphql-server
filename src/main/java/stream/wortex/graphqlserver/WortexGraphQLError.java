package stream.wortex.graphqlserver;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.validation.ValidationError;

import java.util.List;

public class WortexGraphQLError implements GraphQLError {

    private String message;
    private ErrorType errorType;
    private List<SourceLocation> locations;

    public WortexGraphQLError(String message, ErrorType errorType,List<SourceLocation> locations) {
        this.message=message;
        this.errorType = errorType;
        this.locations = locations;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return this.locations;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }
}
