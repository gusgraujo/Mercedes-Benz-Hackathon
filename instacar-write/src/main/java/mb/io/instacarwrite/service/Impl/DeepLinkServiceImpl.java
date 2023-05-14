package mb.io.instacarwrite.service.Impl;

import mb.io.instacarwrite.model.DeepLink;
import mb.io.instacarwrite.model.Post;
import mb.io.instacarwrite.repository.DeepLinkRepository;
import mb.io.instacarwrite.service.DeepLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeepLinkServiceImpl implements DeepLinkService {

    @Autowired
    private DeepLinkRepository deepLinkRepository;

    @Override
    public DeepLink saveDeepLink(Post post, String link){
        DeepLink deepLink = new DeepLink();
        deepLink.setPost(post);
        deepLink.setUri(link);
        deepLink = deepLinkRepository.save(deepLink);
        return  deepLink;
    }
}
