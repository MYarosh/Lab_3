package beans;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name= "points")
public class GraphInfo {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "r", nullable = false)
    private Double r;
    @Column(name = "own", nullable = false)
    private String owner;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "point")
    private Set<History> pointHistoryElements;

    @Transient
    private boolean edit = false;
    @Transient
    private double xEdit;
    @Transient
    private double yEdit;



    public GraphInfo(Double r, String owner){
        this.pointHistoryElements = new LinkedHashSet<>();
        this.r = r;
        this.id = new Date().getTime();
        this.owner = owner;
    }

    public GraphInfo(){}

    public long getId() {
        return id;
    }
    public Double getR() {
        return r;
    }

    public Double getX() {
        return getLastHistoryElement().getX();
    }

    public Double getY() {
        return getLastHistoryElement().getY();
    }

    public String getIsCheck() {
        //getLastHistoryElement().setIsCheck(r);
        return getLastHistoryElement().getIsCheck();
    }

    public String getDate(){
        return new SimpleDateFormat("dd.MM.yy, HH:mm:ss").format(new Date(id));
    }

    public String getOwner() {
        return owner;
    }
    public Set<History> getPointHistoryElements() {
        return pointHistoryElements;
    }
    public List<History> getHistory(){
        ArrayList<History> historyElements = new ArrayList<>(getPointHistoryElements());
        historyElements.sort((p1, p2) -> p1.getId() > p2.getId() ? -1 : 1);
        return historyElements;
    }

    public History getLastHistoryElement(){
        return getHistory().get(0);
    }

    public void addHistoryElement(History element){
        pointHistoryElements.add(element);
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public double getxEdit() {
        xEdit = getX();
        return xEdit;
    }

    public double getyEdit() {
        yEdit = getY();
        return yEdit;
    }

    public void setxEdit(double xEdit) {
        this.xEdit = xEdit;
    }

    public void setyEdit(double yEdit) {
        this.yEdit = yEdit;
    }

    public void reverseEdit(){
        this.edit = !this.edit;
    }
    public double getEX(){return xEdit;}
    public double getEY(){return yEdit;}
}