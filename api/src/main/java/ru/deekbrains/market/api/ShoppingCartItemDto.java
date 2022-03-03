package ru.deekbrains.market.api;

public class ShoppingCartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private float pricePerProduct;
    private float price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(float pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
