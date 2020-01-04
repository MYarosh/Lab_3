package beans;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "point_history")
public class History {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "point_id", nullable = false)
    private GraphInfo point;
    @Column(name = "x", nullable = false)
    private Double x;
    @Column(name = "y", nullable = false)
    private Double y;
    @Column(name = "ch", nullable = false)
    private String isCheck;

    public History(){}

    public History(GraphInfo point, double x, double y){
        this.point = point;
        this.x = x;
        this.y = y;
        double r = point.getR();
        this.isCheck = ((x<=0 && y>=0 && x*x+y*y<=r*r) || (x>=0 && y>=0 && x<=r && y<=r/2) || (x>=0 && y<=0 && y>=2*x-r)) ? "Да" : "Нет";
        this.id = new Date().getTime();
    }

    public History(GraphInfo point, double x, double y, double radius){
        this.point = point;
        this.x = x;
        this.y = y;
        double r = radius;
        this.isCheck = ((x<=0 && y>=0 && x*x+y*y<=r*r) || (x>=0 && y>=0 && x<=r && y<=r/2) || (x>=0 && y<=0 && y>=2*x-r)) ? "Да" : "Нет";
        this.id = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(double r){
        this.isCheck = ((x<=0 && y>=0 && x*x+y*y<=r*r) || (x>=0 && y>=0 && x<=r && y<=r/2) || (x>=0 && y<=0 && y>=2*x-r)) ? "Да" : "Нет";
    }
}