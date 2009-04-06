/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

/**
 *
 * @author owner
 */
public class RegistrationBean {

    private String regMessage;

    public RegistrationBean(String msg)
    {
        regMessage=msg;
    }

    public String getRegMessage()
    {
        return regMessage;
    }

}
