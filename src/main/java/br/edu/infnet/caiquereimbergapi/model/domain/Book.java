package br.edu.infnet.caiquereimbergapi.model.domain;

public class Book extends Publication {
    private String language;
    private int edition;

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
