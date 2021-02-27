package service;

import dto.PostcodeInfo;

import java.util.List;

public interface PostcodeService {
    boolean validatePostcode(String postcode);

    PostcodeInfo getPostcodeInfo(String postcode);

    List<PostcodeInfo> getNearestPostcodes(String postcode);
}