@RestController
@RequestMapping("/workflow")
@RequiredArgsConstructor
public class StateMachineController {

    private final DynamicStateMachineBuilder stateMachineBuilder;

    @PostMapping("/start")
    public ResponseEntity<String> startWorkflow(@RequestBody StateMachineDefinition definition) {
        try {
            StateMachine<String, String> machine = stateMachineBuilder.build(definition);
            return ResponseEntity.ok("Workflow started in state: " + machine.getState().getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
