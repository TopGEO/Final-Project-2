package ge.tbc.testautomation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchOffersResponse {

    private Prices prices;
    private Pagination pagination;
    private List<Offer> offers;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Prices {
        @JsonProperty("min_price")
        private String minPrice;

        @JsonProperty("max_price")
        private String maxPrice;

        public String getMinPrice() { return minPrice; }
        public void setMinPrice(String minPrice) { this.minPrice = minPrice; }

        public String getMaxPrice() { return maxPrice; }
        public void setMaxPrice(String maxPrice) { this.maxPrice = maxPrice; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        @JsonProperty("current_page")
        private int currentPage;

        @JsonProperty("first_page")
        private int firstPage;

        @JsonProperty("total_page")
        private int totalPage;

        public int getCurrentPage() { return currentPage; }
        public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

        public int getFirstPage() { return firstPage; }
        public void setFirstPage(int firstPage) { this.firstPage = firstPage; }

        public int getTotalPage() { return totalPage; }
        public void setTotalPage(int totalPage) { this.totalPage = totalPage; }
    }

    public Prices getPrices() { return prices; }
    public void setPrices(Prices prices) { this.prices = prices; }

    public Pagination getPagination() { return pagination; }
    public void setPagination(Pagination pagination) { this.pagination = pagination; }

    public List<Offer> getOffers() { return offers; }
    public void setOffers(List<Offer> offers) { this.offers = offers; }
}