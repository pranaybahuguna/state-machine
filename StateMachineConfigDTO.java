public class StateMachineConfigDTO {
    public String machineId;
    public String initialState;
    public List<String> endStates;
    public List<String> states;
    public List<TransitionDTO> transitions;
}