@Component("gradeController")
public class GradeController {
    @Value("app.name")     private String appName;
    @Value("app.passMark") private int passMark;

    @Route("/hello")
    public String hello() { return "Welcome to " + appName; }

    @Route("/status")
    public String status() { return "Pass mark is " + passMark; }
}
