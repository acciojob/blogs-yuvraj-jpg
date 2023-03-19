package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        blogRepository2.save(blog);
        blog.getImageList().add(image);
        return image;
    }
    public void deleteImage(Integer id){
        blogRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int c=0;
        Blog blog = blogRepository2.findById(id).get();
        List<Image> imageList = blog.getImageList();
        for(Image x : imageList){
            if(x.getDimension().equals(screenDimensions)){
                c++;
            }
        }
        return c;
    }
}
