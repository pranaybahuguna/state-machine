@Configuration
public class StateMachinePersistenceConfig {

    @Bean
    public JpaPersistingStateMachineInterceptor<String, String> stateMachinePersistInterceptor(
            JpaStateMachineRepository repository) {
        return new JpaPersistingStateMachineInterceptor<>(repository);
    }
}
