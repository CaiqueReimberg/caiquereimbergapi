package br.edu.infnet.caiquereimbergapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book extends Publication {
    @NotNull(message = "Língua é obrigatória")
    private String language;

    @Min(value = 1, message = "A edição tem que ser maior ou igual a 1.")
    private int edition;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @JsonBackReference
    private Store store;

    @Override
    public String toString() {
        return String.format(
                "%s | Língua: %s | Edição: %05d",
                super.toString(),
                language != null ? language : "N/A",
                edition
        );
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
