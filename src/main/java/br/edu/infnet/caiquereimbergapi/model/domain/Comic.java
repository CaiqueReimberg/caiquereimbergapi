package br.edu.infnet.caiquereimbergapi.model.domain;

public class Comic extends Publication {
    private String illustrator;
    private int issueNumber;
    private String universe;

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
