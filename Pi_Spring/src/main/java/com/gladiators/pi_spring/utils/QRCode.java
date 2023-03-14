package com.gladiators.pi_spring.utils;

import com.gladiators.pi_spring.Entities.Reservation;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.var;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCode {
    public static void generateQRCode(Reservation reservation) throws WriterException, IOException {
        String qrCodePath = "D:\\blog-posts\\QRCode\\";
        String qrCodeName = qrCodePath+reservation.getNomCentre ()+reservation.getIdReservation()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter ();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "id_reservation: "+reservation.getIdReservation()+ "\n"+
                        "date_reservation: "+reservation.getDateReservation ()+ "\n"+
                        "reservation_price: "+reservation.getReservation_price ()+ "\n" +
                        "nom_centre: "+reservation.getNomCentre (), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
