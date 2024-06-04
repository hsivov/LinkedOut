package bg.softuni.linkedout.model;

public enum EducationLevel {
    MASTER,
    BACHELOR,
    SECONDARY;

    @Override
    public String toString() {
        String name = name().toLowerCase();

        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
