package mb.io.instacarwrite.service.Impl;

import lombok.extern.slf4j.Slf4j;
import mb.io.instacarwrite.model.DeepLink;
import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.model.User;
import mb.io.instacarwrite.model.enums.PostStatus;
import mb.io.instacarwrite.model.request.PostRequest;
import mb.io.instacarwrite.repository.PostRepository;
import mb.io.instacarwrite.service.*;
import mb.io.instacarwrite.service.exception.ImageAmountException;
import mb.io.instacarwrite.service.exception.NoElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private DeepLinkService deepLinkService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private PhotoService photoService;

    @Override
    public Post findById(Long postId){
        Optional<Post> optionalEntity = postRepository.findById(postId);
        if (optionalEntity.isPresent()) {
            return optionalEntity.get();
        } else {
            log.error("Post with id {} not found!", postId);
            throw new NoElementException(String.format("Post with id %d not found!", postId));
        }
    }

    @Override
    public Post publishPost(Long postId) {
        Post post = findById(postId);
        post.setPostStatus(PostStatus.PUBLISHED.getValue());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post = postRepository.save(post);
        return post;
    }

    @Override
    public void archivePost(long postId) {
        Post post = findById(postId);
        post.setPostStatus(PostStatus.ARCHIVED.getValue());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public Post createPost(PostRequest postRequest){
        User user = userService.findById(postRequest.getUserId());
        Post post = new Post();
        Set<String> links = verifyLinks(postRequest);
        post.setText(postRequest.getDescription());
        post.setUser(user);
        post.setPostStatus(PostStatus.CREATED.getValue());
        post = this.postRepository.save(post);
        List<DeepLink> deepLinks = new LinkedList<>();
        for(String link: links) {
            DeepLink deepLink =   deepLinkService.saveDeepLink(post,link);
            deepLinks.add(deepLink);
        }
        post.setLinks(deepLinks);
        String bucketName = user.getId()+"/"+post.getId();
        log.info("creating bucket for post");
        s3Service.createS3Bucket(bucketName);
        return post;
    }

    @Override
    public void addPhotoInPost(MultipartFile file, Long postId) {
        Post post = findById(postId);
        if(post.getPhoto().size()==10){
            throw new ImageAmountException("this post reached the image limit!");
        }
        String fileKey = post.getUser().getId()+"/"+post.getId();
        String url = s3Service.uploadFile(file,fileKey);
        photoService.savePhoto(post,url);
    }

    // here we will call the mbio app to see if the tags contain any model
    private Set<String> verifyLinks(PostRequest postRequest) {
        Set<String> links = new HashSet<>();
        Set<String> tags = extractTags(postRequest.getDescription());
        tags.forEach(tag -> {
            String link = carModelService.getLinkFromApi(tag);
            if(!link.isEmpty()){
                links.add(link);
            }
        });
        return links;
    }


    private Set<String> extractTags(String input) {
            Set<String> hashtags = new HashSet<>();
            Pattern pattern = Pattern.compile("#\\w+");
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String hashtag = matcher.group();
                hashtags.add(hashtag);
            }
            return hashtags;
    }
}
