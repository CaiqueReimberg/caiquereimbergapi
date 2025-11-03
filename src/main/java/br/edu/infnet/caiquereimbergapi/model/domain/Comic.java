package br.edu.infnet.caiquereimbergapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Comic extends Publication {
    private String illustrator;
    private int issueNumber;
    private String universe;

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
                "%s | Ilustrador: %s | N° Edição: %05d | Universo: %s",
                super.toString(),
                illustrator != null ? illustrator : "N/A",
                issueNumber,
                universe != null ? universe : "N/A"
        );
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }
}
