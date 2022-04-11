package ru.geekbrains.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель данных пользователя")
public class OrderData {

    @Schema(description = "Адрес", required = true, example = "Москва, ул.Ленина,15, кв.6")
    private String address;

    @Schema(description = "Телефон", required = true, example = "89174363697")
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
