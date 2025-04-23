@Data
public class StateMachineDefinition {
    private String initialState;
    private List<String> endStates;
    private List<String> states;
    private List<TransitionDefinition> transitions;
}