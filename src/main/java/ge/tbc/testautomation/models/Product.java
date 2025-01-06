    package ge.tbc.testautomation.models;

    import java.util.Objects;

    public class Product {
        private String name;
        private String price;
        private String link;

        public Product(String name, String price, String link) {
            this.name = name;
            this.price = price;
            this.link = link;
        }

        public String getName() { return name; }
        public String getPrice() { return price; }
        public String getLink() { return link; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Product product = (Product) o;
            return Objects.equals(name, product.name) &&
                    Objects.equals(price, product.price) &&
                    Objects.equals(link, product.link);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price, link);
        }

        @Override
        public String toString() {
            return "Product{name='" + name + "', price='" + price + "', link='" + link + "'}";
        }
    }