@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

    private final DynamicStateMachineBuilder stateMachineBuilder;

    public StateMachineController(DynamicStateMachineBuilder builder) {
        this.stateMachineBuilder = builder;
    }

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestBody StateMachineConfigDTO config) {
        try {
            stateMachineBuilder.buildStateMachine(config);
            return ResponseEntity.ok("State machine started");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
