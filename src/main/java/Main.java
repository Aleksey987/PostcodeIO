import service.PostcodeService;
import service.PostcodeServiceImpl;

public class Main {

    public static void main(String[] args) {
        String postcode = args[0];
        PostcodeService postcodeService = new PostcodeServiceImpl();
        try {
            System.out.println("Is posctcode valid: " + postcodeService.validatePostcode(postcode));
            System.out.println("Postcode info: " + postcodeService.getPostcodeInfo(postcode));
            System.out.println("Nearest postcodes: " + postcodeService.getNearestPostcodes(postcode));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}