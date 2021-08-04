package service;

import com.himel.eshoppers.dto.ProductDTO;
import com.himel.eshoppers.repository.ProductRepository;
import com.himel.eshoppers.service.ProductService;
import com.himel.eshoppers.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImplTest{
    private static final ProductDTO APPLE_I_PAD = new ProductDTO(
            "Apple iPad",
            "Apple iPad 10.2 32GB",
            BigDecimal.valueOf(369.99)
    );

    private static final ProductDTO HEADPHONE = new ProductDTO(
            "Headphones",
            "Jabra Elite Bluetooth Headphones",
            BigDecimal.valueOf(249.99)
    );

    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }
    @Test
    public void testFindAllByName(){
        when(productRepository.findAllProducts()).thenReturn(List.of(HEADPHONE,APPLE_I_PAD));
        var sortedByName = productService.findAllProductSortedByName();
        Assert.assertEquals(APPLE_I_PAD.getName(),sortedByName.get(0).getName());
        Assert.assertEquals(HEADPHONE.getName(),sortedByName.get(1).getName());
    }

}
