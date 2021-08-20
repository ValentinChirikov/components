package by.ese.components.microservices;

import java.util.List;

/**
 * Microservices Registry.
 */
public interface Registry {
    String REGISTER = "/bind";
    String GET_SERVICE = "/lookup";
    String GET_SERVICES = "/list";

    RegistrationStatus register(Service service);

    Service getService(String name);

    List<Service> getServices();
}
