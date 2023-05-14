package mb.io.instacarwrite.service;


import mb.io.instacarwrite.model.DeepLink;
import mb.io.instacarwrite.model.Post;

public interface DeepLinkService {

    public DeepLink saveDeepLink(Post post, String link);
}
