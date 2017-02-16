package domain;

import java.io.Serializable;

/**
 * @author  lanhnguyen on 08/03/2016.
 */
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
