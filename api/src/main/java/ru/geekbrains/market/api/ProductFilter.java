package ru.geekbrains.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель фильтра")
public class ProductFilter {

    @Schema(description = "Часть названия продукта", required = false, example = "сыр")
    private String word;

    @Schema(description = "Минимальная цена продукта", required = false, example = "120.00")
    private Double minPrice;

    @Schema(description = "Максимальная цена продукта", required = false, example = "150.00")
    private Double maxPrice;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
