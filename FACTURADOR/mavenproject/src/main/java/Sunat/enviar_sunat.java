package Sunat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.github.project.openubl.xmlsenderws.webservices.managers.BillServiceManager;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import io.github.project.openubl.xmlsenderws.webservices.wrappers.ServiceConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author r_c_h
 */
public class enviar_sunat {

    public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
        ServiceConfig config = new ServiceConfig.Builder()
                .url("https://e-beta.sunat.gob.pe/ol-ti-itcpfegem-beta/billService")
                .username("20487823024MODDATOS")
                .password("moddatos")
                .build();

        File file = Paths.get("12345678912-01-F001-1.zip").toFile();

        BillServiceModel result = BillServiceManager.sendBill(file, config);

        System.out.println(result.getStatus());
        System.out.println("CDR tiene contenido:" + (result.getCdr() != null));
    }
}
