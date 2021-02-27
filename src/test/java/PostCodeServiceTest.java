import dto.PostcodeInfo;
import org.junit.Test;
import service.PostcodeService;
import service.PostcodeServiceImpl;

import java.util.Collections;

import static org.junit.Assert.*;

public class PostCodeServiceTest {
    public static final String VALID_POSTCODE = "OX49 5NU";
    public static final String INVALID_POSTCODE = "OOOOOOO";
    public static final PostcodeInfo POSTCODE_INFO = new PostcodeInfo(VALID_POSTCODE, "England", "South East");

    private PostcodeService postcodeService = new PostcodeServiceImpl();

    @Test
    public void testValidatePostcodePositive() {
        assertTrue(postcodeService.validatePostcode(VALID_POSTCODE));
    }

    @Test
    public void testValidatePostcodeNegative() {
        assertFalse(postcodeService.validatePostcode(INVALID_POSTCODE));
    }

    @Test
    public void testGetPostcodeInfo() {
        assertEquals(POSTCODE_INFO, postcodeService.getPostcodeInfo(VALID_POSTCODE));
    }

    @Test
    public void testGetNearestPostcodes() {
        assertEquals(Collections.singletonList(POSTCODE_INFO), postcodeService.getNearestPostcodes(VALID_POSTCODE));
    }
}
