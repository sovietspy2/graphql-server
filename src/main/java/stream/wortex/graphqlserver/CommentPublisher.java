package stream.wortex.graphqlserver;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import stream.wortex.graphqlserver.model.Comment;

@Slf4j
@Component
public class CommentPublisher {

    private final Flowable<Comment> publisher;

    private ObservableEmitter<Comment> emitter;

    public CommentPublisher() {
        Observable<Comment> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });

        ConnectableObservable<Comment> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();


        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Comment comment) {
        emitter.onNext(comment);
    }


    public Flowable<Comment> getPublisher() {
        return publisher;
    }

}
