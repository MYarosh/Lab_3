package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@ManagedBean(name="data", eager = true)
@ApplicationScoped

public class DataBean {
    private List<GraphInfo> points;
    private boolean notEmpty;

    private EntityManager em = Persistence.createEntityManagerFactory( "hibernate" ).createEntityManager();

    public DataBean(){
        Query query = em.createQuery("select p from GraphInfo p");
        points = query.getResultList();
        points.sort((p1, p2) -> p1.getId() > p2.getId() ? -1 : 1);
        System.out.println(points.toString());
        setNotEmpty(true);
    }

    public List<GraphInfo> getPoints() {
        return points;
    }

    public void addPoint(GraphInfo currentPoint) {
        points.add(0, currentPoint);
        addPointToDatabase(currentPoint);
        setNotEmpty(true);
    }

    public void setNotEmpty(boolean notEmpty) {
        this.notEmpty = points.size()>0;
    }

    public boolean isNotEmpty(){
        return notEmpty;
    }

    private void addPointToDatabase(GraphInfo p){
        em.getTransaction().begin();
        em.persist(p);
        em.flush();
        em.getTransaction().commit();
        System.out.println("commited");
    }

    public void addElement(History element){
        em.getTransaction().begin();
        em.persist(element);
        em.flush();
        em.getTransaction().commit();
    }

    public void updatePoint(GraphInfo p){
        if(!(p.getX()==p.getEX()&&p.getY()==p.getEY())){
            History element = new History(p, p.getEX(), p.getEY());
            p.addHistoryElement(element);
            addElement(element);
            p.reverseEdit();
        }
        else p.reverseEdit();
    }
}