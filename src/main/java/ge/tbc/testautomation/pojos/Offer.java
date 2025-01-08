package ge.tbc.testautomation.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer {
    private String id;
    private String name;
    private String price;
    @JsonProperty("discount_price")
    private String discountPrice;
    @JsonProperty("discount_percent")
    private String discountPercent;
    @JsonProperty("merchant_name")
    private String merchantName;
    @JsonProperty("image_path")
    private String imagePath;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id.equals(offer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}