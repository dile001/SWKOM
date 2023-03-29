package at.fhtw.swen3.controller;

import at.fhtw.swen3.controller.rest.WarehouseApi;
import at.fhtw.swen3.services.NotificationService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exceptions.badexception.BadWarehouseException;
import at.fhtw.swen3.services.exceptions.notfoundexception.HierarchyNotFoundException;
import at.fhtw.swen3.services.exceptions.notfoundexception.HopNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T14:13:18.811165Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseApiController.class);

    private final NativeWebRequest request;

    private final WarehouseService warehouseService;

    private final NotificationService notificationService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService, NotificationService notificationService) {
        this.request = request;
        this.warehouseService = warehouseService;
        this.notificationService = notificationService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("code") String code) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<>(warehouseService.getWarehouse(code), HttpStatus.OK) ;
            }catch (HopNotFoundException e){
                logger.error("Hop not found ", e);
                notificationService.sendEmail("Error at Application","Hop not found");
                return new ResponseEntity<Hop>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                logger.error("Response not serializable", e);
                notificationService.sendEmail("Error at Application","Response not serializable");
                return new ResponseEntity<Hop>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Hop>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> importWarehouses(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @RequestBody Warehouse warehouse) {
        try{
            warehouseService.importWarehouses(warehouse);
        }catch (BadWarehouseException e){
            logger.error("Response not serializable", e);
            notificationService.sendEmail("Error at Application","Response not serializable");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Warehouse>(warehouseService.exportWarehouses(), HttpStatus.OK);
            } catch(HierarchyNotFoundException e){
                logger.error("Hierarchy not found", e);
                return new ResponseEntity<Warehouse>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.error("Response not serializable", e);
                return new ResponseEntity<Warehouse>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Warehouse>(HttpStatus.NOT_IMPLEMENTED);
    }
}
