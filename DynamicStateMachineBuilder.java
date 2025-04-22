@Service
public class DynamicStateMachineBuilder {

    private final StateMachineFactory<String, String> stateMachineFactory;
    private final StateMachineRuntimePersister<String, String, String> runtimePersister;

    public DynamicStateMachineBuilder(
        ObjectStateMachineFactory factory,
        StateMachineRuntimePersister<String, String, String> runtimePersister) {
        this.stateMachineFactory = factory;
        this.runtimePersister = runtimePersister;
    }

    public StateMachine<String, String> buildStateMachine(StateMachineConfigDTO config) throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
            .withConfiguration()
            .machineId(config.machineId)
            .listener(new StateMachineListenerAdapter<>())
            .and()
            .withPersistence()
            .runtimePersister(runtimePersister);

        builder.configureStates()
            .withStates()
            .initial(config.initialState)
            .states(new HashSet<>(config.states))
            .end(config.endStates.toArray(new String[0]));

        StateMachineTransitionConfigurer<String, String> transitions = builder.configureTransitions().withExternal();
        for (TransitionDTO t : config.transitions) {
            transitions.source(t.source).target(t.target).event(t.event).and();
        }

        StateMachine<String, String> machine = builder.build();
        machine.start();
        return machine;
    }
}
