package beans;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name="checker")
@ViewScoped

public class AreaCheckerBean implements Serializable {
    private Double x;
    private Double y;
    private Double xCanvas;
    private Double yCanvas;
    private String resizeResult;
    private Double r;

    @ManagedProperty(value = "#{data}")
    private DataBean bean;
    @ManagedProperty(value = "#{name}")
    private UserNameBean userNameBean;

    @PostConstruct
    public void postConstruct(){
        try {
            if(userNameBean.getName()==null || userNameBean.getName().equals("")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    public Double getxCanvas() {
        return xCanvas;
    }

    public void setxCanvas(Double xCanvas) {
        this.xCanvas = xCanvas;
    }

    public Double getyCanvas() {
        return yCanvas;
    }

    public void setyCanvas(Double yCanvas) {
        this.yCanvas = yCanvas;
    }

    public AreaCheckerBean(){
        System.out.println("\n\n\nAreaChecker");
        r=3.0;
        y=-2.0;
    }

    public Double getX() {
        return x;
    }


    public void setX(Double x) {
        this.x = x;
        System.out.println("x setted, value " + x);
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
        System.out.println("y setted, value " + y);
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
        System.out.println("r setted, value " + getR());
    }

    public String getResizeResult() {
        System.out.println("get. Rval "+ getR());
        return resizeResult;
    }

    public void setResizeResult(String resizeResult) {
        System.out.println("set");
        this.resizeResult = resizeResult;
    }

    public void check() {
        System.out.println("check!!!!");
        GraphInfo p = new GraphInfo(getR(), userNameBean.getName());
        History element = new History(p, getX(), getY());
        p.addHistoryElement(element);
        bean.addPoint(p);
        bean.addElement(element);
        resizeResult = p.getIsCheck();
    }

    public void canvasCheck(){
        System.out.println("getR  " + getR());
        GraphInfo p = new GraphInfo(getR(), userNameBean.getName());
        History element = new History(p, xCanvas, yCanvas);
        p.addHistoryElement(element);
        bean.addPoint(p);
        bean.addElement(element);
        resizeResult = p.getIsCheck();
    }

    public void resizeCheck(){
        GraphInfo p = new GraphInfo(getR(), userNameBean.getName());
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRR"+ getR());
        p.addHistoryElement(new History(p, xCanvas, yCanvas, r));
        resizeResult = p.getIsCheck();
    }

    public void setBean(DataBean bean){
        this.bean=bean;
    }

    public boolean getReady(){
        System.out.println("ready "+getR()+" "+getX()+" "+getY());
        return x!=null && y!=null && r!=null;
    }


    public void setUserNameBean(UserNameBean userNameBean) {
        this.userNameBean = userNameBean;
    }
    public UserNameBean getUserNameBean() {
        return userNameBean;
    }

}