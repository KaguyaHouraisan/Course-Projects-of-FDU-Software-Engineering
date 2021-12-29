package SELab.domain;

import javax.persistence.*;


@Entity
@Table(name="BackGround")
public class BackGround {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="groundName")
    private String groundName;
    @Column(name="groundWidth")
    private int groundWidth;
    @Column(name="groundHeight")
    private int groundHeight;
    @Column(name="currX")
    private double currX;
    @Column(name="currY")
    private double currY;

    public BackGround() {
    }

    public BackGround(String groundName, int groundWidth, int groundHeight, double currX, double currY) {
        this.groundName = groundName;
        this.groundWidth = groundWidth;
        this.groundHeight = groundHeight;
        this.currX = currX;
        this.currY = currY;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public int getGroundWidth() {
        return groundWidth;
    }

    public void setGroundWidth(int groundWidth) {
        this.groundWidth = groundWidth;
    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public void setGroundHeight(int groundHeight) {
        this.groundHeight = groundHeight;
    }

    public double getCurrX() {
        return currX;
    }

    public void setCurrX(double currX) {
        this.currX = currX;
    }

    public double getCurrY() {
        return currY;
    }

    public void setCurrY(double currY) {
        this.currY = currY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
