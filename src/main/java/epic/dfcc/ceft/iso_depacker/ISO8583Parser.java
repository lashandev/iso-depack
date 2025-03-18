/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epic.dfcc.ceft.iso_depacker;

/**
 *
 * @author lashan_chandika
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
//import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;

public class ISO8583Parser {

    public static List<Vector> unpack(String hexString, String XML_FILE_PATH) throws Exception {
        List<Vector> list = new ArrayList<>();
        try {

            InputStream is = new FileInputStream(XML_FILE_PATH);

            if (is == null) {
                throw new RuntimeException("Packager XML file not found at: " + XML_FILE_PATH);
            }

            GenericPackager packager = new GenericPackager(is);
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
//            String hexString = "30323030F23A440108A0802000000000060000103136313233343536373839313233343536373438303030303030303030303033323030303033303431313435353530303236353731373135353430333034303330353630313330353130344E4F565337343130383837353438353130303032303037344446434320303033204C4B412052616D616E6179616B652020202020202020202020202020202020313434303230303737313534323734343037313031363436383131364C4B523135353232303030313038303031323130323035393538303936323030344E4F5653";
            // Convert hex string to byte array
            byte[] isoMessageBytes = ISOUtil.hex2byte(hexString);
            // Load packager from external path
//            ISOMsg isoMsg = getPackager(XML_FILE_PATH);

            isoMsg.unpack(isoMessageBytes);

            // Print MTI and fields
            System.out.println("MTI: " + isoMsg.getMTI());
            Vector vMTI = new  Vector();
            vMTI.add("MTI");
            vMTI.add("Field 0");
            vMTI.add("4");
            vMTI.add(isoMsg.getMTI());
            list.add(vMTI);
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.println("Field (" + i + "): " + isoMsg.getString(i));
                    Vector v = new Vector<>();
                    String fieldDesc = packager.getFieldPackager(i).getDescription();
                    v.add(fieldDesc);
                    v.add("Field " + i);
                    v.add("" + isoMsg.getString(i).toString().length());
                    v.add(isoMsg.getString(i));
                    list.add(v);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static ISOMsg getPackager(String xmlFilePath) throws Exception {
        InputStream is = new FileInputStream(xmlFilePath);

        if (is == null) {
            throw new RuntimeException("Packager XML file not found at: " + xmlFilePath);
        }

        GenericPackager packager = new GenericPackager(is);
        ISOMsg m = new ISOMsg();
        m.setPackager(packager);
        return m;
    }

}
