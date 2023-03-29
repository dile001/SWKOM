package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exceptions.badexception.BadAddressException;
import at.fhtw.swen3.services.exceptions.badexception.BadParcelException;
import at.fhtw.swen3.services.exceptions.badexception.BadTrackingIDException;
import at.fhtw.swen3.services.exceptions.duplicationexception.DuplicationTrackingIDException;
import at.fhtw.swen3.services.exceptions.notemptyexception.FutureHopsNotEmptyException;
import at.fhtw.swen3.services.exceptions.notfoundexception.HopNotFoundException;
import at.fhtw.swen3.services.exceptions.notfoundexception.ParcelNotFoundException;

public interface NotificationService {

    void sendEmail(String subject, String body);

}