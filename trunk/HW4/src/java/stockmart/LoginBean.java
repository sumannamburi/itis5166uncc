/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmart;

/**
 *
 * @author owner
 */
public class LoginBean {

    private String loginMessage;

    public LoginBean(String msg)
    {
        loginMessage=msg;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }

}
