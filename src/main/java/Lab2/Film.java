package Lab2;

public class Film {

    private Integer id;
    private String title;
    private String description;
    private final Integer languageId = 1;

    public Film(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Film(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
