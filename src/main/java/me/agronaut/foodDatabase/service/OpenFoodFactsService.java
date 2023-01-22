package me.agronaut.foodDatabase.service;

import me.agronaut.foodDatabase.model.OpenFoodDto;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class OpenFoodFactsService {
    private final OpenFoodFactsWrapper wrapper;

    @Autowired
    public OpenFoodFactsService(OpenFoodFactsWrapper wrapper){
        this.wrapper = wrapper;
    }

    public OpenFoodDto getByCode(String code) {
        ProductResponse response = wrapper.fetchProductByCode(code);

        if (response != null) {
            Product product = response.getProduct();

            OpenFoodDto res = new OpenFoodDto();
            res.setAllergens(product.getAllergensTags());
            res.setIngredients(product.getIngredientsText());
            res.setName(product.getProductName());
            res.setBarcode(product.getCode());
            res.setQuantity(Integer.parseInt(product.getProductQuantity()));
            res.setPhoto(downloadImage(product.getImageThumbUrl()));
            return res;
        }
        return null;
    }

    private Binary downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);

            return new Binary(toByteArray(image, "jpg"));
        } catch (IOException e) {
            return null;
        }
    }

    public static byte[] toByteArray(BufferedImage bi, String format)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);

        return baos.toByteArray();

    }
}
