package lv.maeteman.cleanmap.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "type", nullable = false)
    private MarkType type;

    @Column(name = "description")
    private String description;

    @Column(name = "client_id")
    private Long clientId;

    public Mark() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public MarkType getType() {
        return type;
    }

    public void setType(MarkType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return id == mark.id &&
                Objects.equals(x, mark.x) &&
                Objects.equals(y, mark.y) &&
                type == mark.type &&
                Objects.equals(description, mark.description) &&
                Objects.equals(clientId, mark.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, type, description, clientId);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
