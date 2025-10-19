package br.edu.infnet.caiquereimbergapi.model.domain;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private BigDecimal price;
    private boolean active = true;

    @Override
    public String toString() {
        return String.format(
                "Título: %s | Autor: %s | Editora: %s | Categoria: %s | Preço: %.2f | Estoque: %s",
                title,
                author,
                publisher,
                category,
                price,
                active ? "Sim" : "Não"
        );


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
