# Mercedes-Benz-Hackathon
With only 2 developers and 1 DevOps engineer on our team, we worked tirelessly for 24 hours to create this project for the Untangle The Cloud Mercedes-Benz IO Hackathon, ultimately emerging as the winning team.


Solution project documentation: [CarCultureClub](https://drive.google.com/file/d/1haPA9s5rJgixREfNyTH3tXCsdabwt3Qk/view?usp=share_link)


# Challenge

## Instacar

Welcome, tech enthusiasts, developers, and car aficionados! We are thrilled to announce the upcoming hackathon, an event that will bring together the brightest minds and passionate hearts to build an exciting new platform, Instacar.

Instacar is envisioned as an Instagram clone, but with a twist. Our platform is aimed specifically at the new generations of car enthusiasts, who harbor a deep passion for everything automobile. Instacar will serve as the perfect digital garage where like-minded people can connect, share their experiences, showcase their vehicles, and delve into the fascinating world of cars.

This hackathon is your opportunity to be part of something groundbreaking. By participating, you will be contributing to the creation of a space that bridges the gap between technology and car culture, a platform that will bring automobile lovers closer than ever before.

This challenge will focus on the core features that make Instagram so popular: sharing high-quality images, creating and maintaining user profiles, curating and following feeds, and enabling interactions through likes and comments. However, to keep our focus laser-sharp on the car-loving community, we won't be supporting music, videos, and some of the other advanced features at this stage.

Your mission, should you choose to accept it, is to combine your technical prowess, creativity, and love for cars to help us create Instacar. Together, we'll build an inspiring platform for car enthusiasts worldwide.

So, gear up, prepare your coding tools, and join us on this thrilling journey. This hackathon promises to be a high-octane adventure, combining coding marathons with car passion. Rev your engines, developers, and let's build Instacar!


### Challenge Requirements

- Expose the service as an API
- Posts include one or more images (up to 10, with max size 5MB per image)
- Posts should support tags and other metadata
- Users can follow other users
- Users can like posts of each other
- Generate user feed
- Users can add tags to their posts.
    - If one of these submitted tags matches a Mercedes model the post should be augmented automatically by adding a deeplink to the corresponding model page.
    The model deeplinks can be obtained through an external service available online with the following specification:
        https://dev.api.oneweb.mercedes-benz.com/hackathon/deeplinks/swagger-ui.html
        The tags that should trigger a deeplink augmentation are available in the /models endpoint described in the link above.
    - As an example, if a post contains a tag "#C_Class", the solution should invoke the deeplink external service and add a deeplink attribute containing the value "https://www.mercedes-benz.co.uk/passengercars/models/estate/c-class/overview.html"
    - As an hint, the deeplink external service was intentionally developed with performance and reliability issues. The solution should take this into account.

- Scale to support 5 million active users worldwide in one year; – active users are users that upload a photo per day.

### Deliverables

- A functional API that meets the requirements outlined above.
    - A must have feature for this service is the integration with the external models API.
- One repository with the implemented solution.
- Automated build and deployment pipeline of your service to AWS
