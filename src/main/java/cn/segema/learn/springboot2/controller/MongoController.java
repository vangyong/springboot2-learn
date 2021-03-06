//package cn.segema.learn.springboot2.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import cn.segema.learn.springboot2.domain.Tweet;
//import cn.segema.learn.springboot2.exception.TweetNotFoundException;
//import cn.segema.learn.springboot2.payload.ErrorResponse;
//import cn.segema.learn.springboot2.repository.TweetRepository;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.validation.Valid;
//
//@RestController
//public class MongoController {
//
//    @Autowired
//    private TweetRepository tweetRepository;
//
//    @GetMapping("/tweets")
//    public Flux<Tweet> getAllTweets() {
//        return tweetRepository.findAll();
//    }
//
//    @PostMapping("/tweets")
//    public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
//        return tweetRepository.save(tweet);
//    }
//
//    @GetMapping("/tweets/{id}")
//    public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId) {
//        return tweetRepository.findById(tweetId)
//                .map(savedTweet -> ResponseEntity.ok(savedTweet))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/tweets/{id}")
//    public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
//                                                   @Valid @RequestBody Tweet tweet) {
//        return tweetRepository.findById(tweetId)
//                .flatMap(existingTweet -> {
//                    existingTweet.setText(tweet.getText());
//                    return tweetRepository.save(existingTweet);
//                })
//                .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @DeleteMapping("/tweets/{id}")
//    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {
//
//        return tweetRepository.findById(tweetId)
//                .flatMap(existingTweet ->
//                        tweetRepository.delete(existingTweet)
//                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
//                )
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Tweet> streamAllTweets() {
//        return tweetRepository.findAll();
//    }
//
//    @ExceptionHandler(DuplicateKeyException.class)
//    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
//    }
//
//    @ExceptionHandler(TweetNotFoundException.class)
//    public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex) {
//        return ResponseEntity.notFound().build();
//    }
//
//}
