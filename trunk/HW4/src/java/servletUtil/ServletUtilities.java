/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servletUtil;
import java.text.DecimalFormat;
import javax.servlet.http.*;

/** Some simple timesavers. Note that most are static methods.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ServletUtilities {

  public static String PATHROOT = "";
  //http://coit-servlet01.uncc.edu:8080/enwillia_Eservlet4/Login

  public static int LONGLIFECOOKIE = 60*60*24*365;//lasts a year

  public static final String DOCTYPE =
    "<!DOCTYPE html\n"+
"PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n"+
"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

  public static final String END= "</body></html>";

  public static String explanation="Notice: If the quantity entered is not a non-negative integer, " +
          "then the quantity on the resulting Transaction Details page will be one.";

  public static String explanationTransaction="Notice: If the quantity entered is not a non-negative integer, " +
          "then the quantity on the resulting Transaction Details page will be one." +
          "If you are trying to sell more shares than you have, the quantity will be 0." +
          "If you are trying to sell shares that you do not have, the quantity will be 0.";

  public static String headWithTitle(String title) {
    return(DOCTYPE + "\n" +
           "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n" +
           "<head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>" +
           "<link rel=\"stylesheet\" type=\"text/css\" href=\"stockmart.css\"/>" +
           "<title>" + title + "</title></head>" );
  }


  public static String header(String message)
  {
      return ("<div id=\"header\">"+
        "<p class=\"banner\">"+
        message+
        "</p>"+
        "</div><!--header-->"+

        "<br/>"+
        "<hr/>"+
        "<br/>");
  }

  public static String footer="<br/>"+
              "<hr/>" +
              "<br/>" +
              "<div id=\"footer\">"+
                "E. Williams Company 2009 </div><!-- footer-->";

   public static String CSSfile(){
        String css=
                "<style type=\"text/css\">\n"+
                /*css file*/

                "body"+
                "{"+
                "font-family:serif;"+
                "}"+

                "#content"+
                "{"+
                "width:100%;"+
                "}"+

                "#header"+
                "{"+
                "background-color:#4169E1;"+
                "padding:.5%;"+
                "}"+

                "#footer"+
                "{"+
                "padding:3px;"+
                "background-color:#4169E1;"+
                "clear:both;"+
                "}"+

                "table.login"+
                "{"+
                "margin-left:35%;"+
                "width:30%;"+
                "}"+


                "table.full"+
                "{"+
                "width:100%;"+
                "}"+

                "table"+
                "{"+
                "background-color:#4169E1;/*royal blue*/"+
                "padding:3px;"+
                "border-collapse:collapse;"+
                "border:0;"+
                "empty-cells:show;"+
                "}"+

                "p.center"+
                "{"+
                "text-align:center;"+
                "}"+

                "p.banner"+
                "{"+
                "text-align:center;"+
                "font-size: xx-large;"+
                "font-weight: bold;"+
                "}"+


                "a:link"+
                "{"+
                "color: black;"+
                "}"+

                "td"+
                "{"+
                "text-align:center;"+
                "}"+


                "</style>";
        return css;
    }

   public static String toDollarFigures(Double figure)
   {
       DecimalFormat nf = new DecimalFormat("0.00");
       return nf.format(figure);
   }

  /** Read a parameter with the specified name, convert it
   *  to an int, and return it. Return the designated default
   *  value if the parameter doesn't exist or if it is an
   *  illegal integer format.
  */

  public static int getIntParameter(HttpServletRequest request,
                                    String paramName,
                                    int defaultValue) {
    String paramString = request.getParameter(paramName);
    int paramValue;

    try {
      paramValue = Integer.parseInt(paramString);
      if(paramValue<0)
      {
          paramValue = defaultValue;
      }
    }
    catch(NullPointerException npe)
    {
        paramValue = defaultValue;
    }
    catch(NumberFormatException nfe) { // null or bad format
      paramValue = defaultValue;
    }
    return(paramValue);
  }

  public static double getDoubleParameter
                                 (HttpServletRequest request,
                                  String paramName,
                                  double defaultValue) {
    String paramString = request.getParameter(paramName);
    double paramValue;
    try {
      paramValue = Double.parseDouble(paramString);
      if (paramValue < 0) {
          paramValue = defaultValue;
      }
      } catch (NullPointerException npe) {
          paramValue = defaultValue;
      } catch (NumberFormatException nfe) { // null or bad format
          paramValue = defaultValue;
      }
      return(paramValue);
  }

  /** Replaces characters that have special HTML meanings
   *  with their corresponding HTML character entities.
   */

  // Note that Javadoc is not used for the more detailed
  // documentation due to the difficulty of making the
  // special chars readable in both plain text and HTML.
  //
  // Given a string, this method replaces all occurrences of
  //  '<' with '&lt;', all occurrences of '>' with
  //  '&gt;', and (to handle cases that occur inside attribute
  //  values), all occurrences of double quotes with
  //  '&quot;' and all occurrences of '&' with '&amp;'.
  //  Without such filtering, an arbitrary string
  //  could not safely be inserted in a Web page.

  public static String filter(String input) {
    if (!hasSpecialChars(input)) {
      return(input);
    }
    StringBuffer filtered = new StringBuffer(input.length());
    char c;
    for(int i=0; i<input.length(); i++) {
      c = input.charAt(i);
      switch(c) {
        case '<': filtered.append("&lt;"); break;
        case '>': filtered.append("&gt;"); break;
        case '"': filtered.append("&quot;"); break;
        case '&': filtered.append("&amp;"); break;
        default: filtered.append(c);
      }
    }
    return(filtered.toString());
  }

  private static boolean hasSpecialChars(String input) {
    boolean flag = false;
    if ((input != null) && (input.length() > 0)) {
      char c;
      for(int i=0; i<input.length(); i++) {
        c = input.charAt(i);
        switch(c) {
          case '<': flag = true; break;
          case '>': flag = true; break;
          case '"': flag = true; break;
          case '&': flag = true; break;
        }
      }
    }
    return(flag);
  }
}

