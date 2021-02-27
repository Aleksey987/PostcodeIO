package dto;

import java.util.Objects;

public class PostcodeInfo {
    private String postcode;
    private String country;
    private String region;

    public PostcodeInfo(String postcode, String country, String region) {
        this.postcode = postcode;
        this.country = country;
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostcodeInfo that = (PostcodeInfo) o;
        return Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postcode, country, region);
    }

    @Override
    public String toString() {
        return "PostcodeInfo{" +
                "postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
