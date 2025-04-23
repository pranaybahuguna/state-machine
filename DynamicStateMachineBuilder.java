@Component
@RequiredArgsConstructor
public class DynamicStateMachineBuilder {

    private final StateMachineFactory<String, String> stateMachineFactory;
    private final JpaPersistingStateMachineInterceptor<String, String> persister;

    public StateMachine<String, String> build(StateMachineDefinition definition) throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

        builder.configureStates()
            .withStates()
            .initial(definition.getInitialState())
            .states(new HashSet<>(definition.getStates()))
            .end(definition.getEndStates().toArray(new String[0]));

        StateMachineTransitionConfigurer<String, String> transitions = builder.configureTransitions();

        for (TransitionDefinition t : definition.getTransitions()) {
            transitions
                .withExternal()
                .source(t.getSource())
                .target(t.getTarget())
                .event(t.getEvent());
        }

        StateMachine<String, String> machine = builder.build();
        machine.getStateMachineAccessor()
            .doWithAllRegions(accessor -> accessor.addStateMachineInterceptor(persister));
        machine.start();

        return machine;
    }
}
