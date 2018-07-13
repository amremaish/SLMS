/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility;

/**
 *
 * @author Amr
 */
public class Utility {

    public static String getExtension(String path) {
        String ext = " ";
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '.') {
                break;
            }
            ext += path.charAt(i);
        }
        return new StringBuilder(ext).reverse().toString().toUpperCase().trim();
    }
}
