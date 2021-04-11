package DataBase1.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FarmProductMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int totalScore;

    @ElementCollection
    private Set<String> categoriesOfMarket;

    public FarmProductMarket() {
    }

    public FarmProductMarket(String name, int totalScore, Set<String> categoriesOfMarket) {
        this.name = name;
        this.totalScore = totalScore;
        this.categoriesOfMarket = categoriesOfMarket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Set<String> getCategoriesOfMarket() {
        return categoriesOfMarket;
    }

    public void setCategoriesOfMarket(Set<String> categoriesOfMarket) {
        this.categoriesOfMarket = categoriesOfMarket;
    }
}
