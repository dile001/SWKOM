package at.fhtw.swen3.controller;

import at.fhtw.swen3.controller.rest.ParcelApi;
import at.fhtw.swen3.services.NotificationService;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exceptions.notemptyexception.FutureHopsNotEmptyException;
import at.fhtw.swen3.services.exceptions.notfoundexception.HopNotFoundException;
import at.fhtw.swen3.services.exceptions.notfoundexception.ParcelNotFoundException;
import at.fhtw.swen3.services.exceptions.badexception.BadAddressException;
import at.fhtw.swen3.services.exceptions.badexception.BadParcelException;
import at.fhtw.swen3.services.exceptions.badexception.BadTrackingIDException;
import at.fhtw.swen3.services.exceptions.duplicationexception.DuplicationTrackingIDException;
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

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T14:13:18.811165Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    private static final Logger log = LoggerFactory.getLogger(ParcelApiController.class);

    private final ParcelService parcelService;

    private final NotificationService notificationService;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelService parcelService, NotificationService notificationService) {
        this.request = request;
        this.parcelService = parcelService;
        this.notificationService = notificationService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Parcel parcel
    )  {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                NewParcelInfo newParcelInfo = parcelService.submitNewParcel(parcel);
                return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
            }catch (BadParcelException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.BAD_REQUEST);
            }catch (BadAddressException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_FOUND);
            }catch (DuplicationTrackingIDException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (HopNotFoundException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(in = ParameterIn.PATH, description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required=true, schema=@Schema()) @PathVariable("trackingId") String trackingId,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Parcel parcel
    )  {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try{
                NewParcelInfo newParcelInfo=parcelService.transferExistingParcel(trackingId, parcel);
                return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
            }catch (BadParcelException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.BAD_REQUEST);
            }catch (BadTrackingIDException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.BAD_REQUEST);
            }catch (BadAddressException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_FOUND);
            }catch (DuplicationTrackingIDException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.CONFLICT);
            }catch (HopNotFoundException e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(in = ParameterIn.PATH, description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required=true, schema=@Schema()) @PathVariable("trackingId") String trackingId
    )  {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                TrackingInformation trackingInformation = parcelService.trackParcel(trackingId);
                return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
            }catch (BadTrackingIDException e){
                log.error(e.getMessage());
                return new ResponseEntity<TrackingInformation>(HttpStatus.BAD_REQUEST);
            }catch (ParcelNotFoundException e){
                log.error(e.getMessage());
                return new ResponseEntity<TrackingInformation>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<TrackingInformation>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<TrackingInformation>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(in = ParameterIn.PATH, description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required=true, schema=@Schema()) @PathVariable("trackingId") String trackingId
    ) {
        try{
            parcelService.reportParcelDelivery(trackingId);
            notificationService.sendEmail("Parcel Delivery","Parcel successfully delivered.");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (BadTrackingIDException e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }catch (FutureHopsNotEmptyException e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }catch (ParcelNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(in = ParameterIn.PATH, description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required=true, schema=@Schema()) @PathVariable("trackingId") String trackingId,
            @Pattern(regexp="^[A-Z]{4}\\d{1,4}$") @Parameter(in = ParameterIn.PATH, description = "The Code of the hop (Warehouse or Truck).", required=true, schema=@Schema()) @PathVariable("code") String code
    ) {
        try{
            parcelService.reportParcelArrivalAtHop(trackingId, code);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch (BadTrackingIDException e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }catch (ParcelNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }catch (HopNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
