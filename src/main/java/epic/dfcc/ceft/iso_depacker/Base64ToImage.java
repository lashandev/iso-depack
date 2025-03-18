/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epic.dfcc.ceft.iso_depacker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author lashan_chandika
 */
public class Base64ToImage {
    
    public static void init(){
        try {
            String baseDir = System.getProperty("os.name").toLowerCase().contains("win")
                    ? "C:/ISO_Unpacker_Config"
                    : "/opt/ISO_Unpacker_Config";
            String faviIconPath = baseDir+"/favi.png";
            
            
            String faviBase64 = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAX4SURBVFhH7VZbbBRlFP7mtpduW1wuTSm12JuCrhCIQLij4A0iEESJGAwaH4wSjcbg3QSDPBCCgA8WfYLEhGClRgkoiURDoYE2JsjFxpZLV0oLLb3s7uxtdmY955/Zm9Q3Y1/4sjv/zD/z/+c753znzEhpAkYRsjOOGu4QGHUCt4mwp1/HsZYgBobjSBqWPSnZB0mSkDIBsYLOM3PiASmNZMKAaaag6zqGhwcRjYSQNpMY6y/F4gWzsO6xOt6oAAUEGhrPY0vDaRR5VMgKbW5bJqRhJnWkkhFYPBoxWPQ3U1F7NGhMJZC2DFimIVbJqgZF0WjOJGIRxHt+x/iJZei7fsne0kGWQEdwCPM3NqKizAdZePVPsJc8n++1QzL7PJ87I5Hmnz0lEyE3us79hHGRJlz44zI/JJDVwJHmLowpdhUYZ265APGGlJK0Kby0KLQcXstMkPdx529HxuKI8Ohcm4YOIzaAydOexMX2PlwLXnH2zCPQei4IVc0Zt6w0IrqBWDyVJcFjLG6Ief4nDRJEHvh+IpESa4yUlUfeBhNTymaipeWkM5NHoK/vJlTKGYONM5lt7y3CphdnIhqzSURjBp56tB67tjyCbe8uQt09fjHHMGlNnIwvmF2JNcvrMam8GHrUKCDBZ6wLPRKxJwhZAszOzp9NYJzfi2lTJ+Ch6eXCUzb08vrp2PhsAJUVJaiv8WP7h4tRSySSSRNx8nr7B0vwzmtzsOHpAPZ8shTrVk5BWKdUOSSEMtJESlzZyBJwSaRihwHLIBROijK0vQC8Hg3LFk4WZHY0nMHOL1uhaQqeWz0Vg6E4nl9zP6bUj8POr1rx0ltHESLDL6wNoHayn0o3LwpUFSbXsoMsAY/KTDMEJBFOYdkBk5JlrgDAIGKn2rrxzQ/t2HfwPHxeDSuW1uDWYAzHTwTR3RtG29lexGgPnmddZKBQeer6CClwa5JQdgZGnsDIrtDBoSN/wu1S8f7rc7F2xRTs3NuG62SsnErXV6QJ8bGzXuojnVeGxNrqu8cIfTD4KLMGopRuB7kUqGkqGybgeJmiErRvCRR5VexvvICtu09RGNOkhQewdfMChCJJIqUI3ThdQZTyEHVS3kdRyER2ozRFURWdMoMcAYXER/VdAFqoqrJYz2VVObEEzaevYf2mwxTmCBbPq8LsGRPRezMq0saeuzRZeMydlIn0U1o4dRlIRCASCTtXeQQ0SoHJEWDaBB7YsJuEliCVL5lbhYN7V+HzT5eRwQi+pXSYpoX7asZiOBxHd08YxT4NddV+DIUSeJjIsfrbO2/l+gttqKguIjCCBuwI2Bpg7WnkCS/zeBRoFIVfTgZxuWsIVRWlOPDFSmwg1cuyjIsd/UIXTT92ULglvP3KLOzbtRz3EjGO3tHjV+DizQUoMv9GQJFMUSKsAf7ZpaeKUlQ4hPTkR9tPCO+qJpWitMSNxsPtON/eLzw/9utVNB3twBia5/tMfEdDq+icChHNQFFcBY0o+zJ6c/PHONTmw/jyGjJuIkU599/lEbnXqaZZTFwZnF/ucmES38BQXKif88/bcM+YMLYIVZUluPpXCIMkRHbCfnmRD6oHXWe/x4wJHTj28wl7ThwJCqjdWs77n8DhYyVnjDO48bDib/RFRclljDN45OtINIlzFBXunPnGbXAVyNQ5c+WeJVA2vhTJeIh2yg+XnDWeAW/I5Hi+cHP7Hs+7iOhI9+kJipRVMJ/dfeH8+bhx9TdIzgvpvwLnl9PDiTbhpo4WRHVN7suo4ItIdRUh8MwBWkG55p4gbhFbQdhpMw753LndfARG8FhERVUpdRqqiy7h6z2vorOzA7W1NokCAq1nWjB7zjzMXfEGKqofpJwWIxGnzzB6U6aJkEGfXYbBxEikNJopar00b5omhZLe//RJJnymMAshGwnEozp6uy+hp+uCsPFdUxNWrV4tzhkFBBgGLdr92Q6cbG7G4OAA1bAbHq9X3LvN69xBgL11uSnMDNrVTec+XzHq6uvx+BPLEQgE7Ht5uI3A/41CiY8C7hAYZQLA35Ka/jYYiQO9AAAAAElFTkSuQmCC";
            if(!new File(faviIconPath).exists())
            saveBase64Image(faviBase64, faviIconPath);
            
            String homeBase64 = "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";
            String homeIconPath = baseDir + "/iso.png";            
            if(!new File(homeIconPath).exists())
            saveBase64Image(homeBase64, homeIconPath);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void saveBase64Image(String base64String, String filePath) {
        try {
            // Decode Base64 string
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            
            // Write bytes to file
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                fos.write(imageBytes);
                System.out.println("Image saved as " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
