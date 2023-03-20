package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description,dimensions,blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }
    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int c=0;
        /// given
        int a = screenDimensions.indexOf('X');
        int x = Integer.parseInt(screenDimensions.substring(0,a));
        int y = Integer.parseInt(screenDimensions.substring(a));
         //find
        Image image = imageRepository2.findById(id).get();
        int b = image.getDimensions().indexOf('X');
        int x1 = Integer.parseInt(image.getDimensions().substring(0,b));
        int y1 = Integer.parseInt(image.getDimensions().substring(b));

        c = (x*y)/(x1*y1);

        return c;
    }
}
