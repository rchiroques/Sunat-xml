package Sunat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.github.project.openubl.xmlsenderws.webservices.managers.BillServiceManager;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import io.github.project.openubl.xmlsenderws.webservices.wrappers.ServiceConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author r_c_h
 */
public class enviar_sunat {    
   
    public static void main(String[] args) throws FileNotFoundException, IOException {       
       //leer text que contiene el nombre de la factura o boleta
       String sCadena = null;    
       BufferedReader bf = new BufferedReader(new FileReader("D:\\ERP_JSIC_SUNAT\\00_CERTIFICADO_DIGITAL\\temp.txt"));        
        int concador=0;
        while ((sCadena = bf.readLine())!=null) {if(concador==0){concador=concador+1;System.out.println(sCadena.trim());break;}}  
       
       //envio a sunat
       FileOutputStream fout=null;       
       ServiceConfig config = new ServiceConfig.Builder()
            .url("https://e-beta.sunat.gob.pe/ol-ti-itcpfegem-beta/billService")
            .username("20487823024MODDATOS")
            .password("moddatos")            
            .build();
            
       File file = new File("D:\\ERP_JSIC_SUNAT\\02_ENVIAR_XML\\"+sCadena+".zip");
       BillServiceModel result = BillServiceManager.sendBill(file, config);
       //cargo la respuesta
       fout = new FileOutputStream("D:\\ERP_JSIC_SUNAT\\04_RESPUESTA_XML\\"+sCadena+".zip");
       fout.write(result.getCdr());
       fout.close();
    

   }  
}
