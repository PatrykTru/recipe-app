package tru.springframework.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        try {
        Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[multipartFile.getBytes().length];

            int i=0;

            for(Byte b : multipartFile.getBytes())
            {
                bytes[i++]= b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        }
        catch (IOException e)
        {
            //todo handle better
            log.debug("problem with imageFile");
            e.printStackTrace();
        }
    }
}
