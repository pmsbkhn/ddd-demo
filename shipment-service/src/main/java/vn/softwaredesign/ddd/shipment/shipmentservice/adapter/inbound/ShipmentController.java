package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.inbound;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.softwaredesign.ddd.shipment.shipmentservice.service.ShipmentService;

@RestController
@RequestMapping("/api/shipments")
@Tag(name = "Shipment API", description = "API for Shipment Operations")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{shipmentId}/assign-driver")
    @Operation(summary = "Assign Driver to Shipment", description = "Find and assign a driver to a shipment")
    public ResponseEntity<String> findDriverForShipment(@PathVariable String shipmentId) {
        try {
            shipmentService.findDriverAndAssign(shipmentId);
            return ResponseEntity.ok("Driver assigned successfully to shipment ID: " + shipmentId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to assign driver: " + e.getMessage());
        }
    }
}
