package util.am;

import fr.liglab.adele.cream.annotations.provider.OriginEnum;

import java.util.Set;

/*ToDo*/
public class MonitoredCreator {

    private String element;

    private OriginEnum origin;

    private boolean enabled;

    private Set<String> potentiallyProvidedServices;

    private Set<String> potentiallyRequiredServices;

    private Set<String> instances;

    public MonitoredCreator(String element, OriginEnum origin, boolean enabled, Set<String> potentiallyProvidedServices, Set<String> potentiallyRequiredServices, Set<String> instances) {
        this.element = element;
        this.origin = origin;
        this.enabled = enabled;
        this.potentiallyProvidedServices = potentiallyProvidedServices;
        this.potentiallyRequiredServices = potentiallyRequiredServices;
        this.instances = instances;
    }

    public String getElement() {
        return element;
    }

    public OriginEnum getOrigin() {
        return origin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<String> getPotentiallyProvidedServices() {
        return potentiallyProvidedServices;
    }

    public Set<String> getPotentiallyRequiredServices() {
        return potentiallyRequiredServices;
    }

    public Set<String> getInstances() {
        return instances;
    }
}
