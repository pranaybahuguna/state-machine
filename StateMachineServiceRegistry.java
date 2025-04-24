@Component
public class StateMachineServiceRegistry {

    private final Map<String, StateMachineService<String, String>> serviceMap;

    public StateMachineServiceRegistry(Map<String, StateMachineService<String, String>> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public StateMachineService<String, String> getService(String name) {
        StateMachineService<String, String> service = serviceMap.get(name);
        if (service == null) {
            throw new IllegalArgumentException("No StateMachineService found with name: " + name);
        }
        return service;
    }

    // Optional: For debugging or UI purposes
    public Set<String> getAvailableServices() {
        return serviceMap.keySet();
    }
}
