package by.ese.components.microservices;

import java.io.Serializable;

/**
 * Service name & location.
 */
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Service name.
     */
    public String name;

    public Service() {
    }

    public Service(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * Service URL.
     */
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
