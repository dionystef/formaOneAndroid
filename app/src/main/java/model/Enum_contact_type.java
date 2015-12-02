package model;

/**
 * Created by dionys on 26/09/15.
 */
public class Enum_contact_type {

    private int id;
    private String label;

    public Enum_contact_type(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
