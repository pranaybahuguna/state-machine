@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Autowired
    private StateMachineRuntimePersister<String, String, String> jpaPersister;

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config
            .withConfiguration()
            .autoStartup(true)
            .and()
            .withPersistence()
            .runtimePersister(jpaPersister);
    }

    @Bean
    public StateMachineRuntimePersister<String, String, String> jpaPersister(JpaStateMachineRepository repository) {
        return new JpaPersistingStateMachineInterceptor<>(repository);
    }
}
