package ro.polak.webserver;

import java.util.Hashtable;

/**
 * HTTP headers representation
 *
 * @author Piotr Polak piotr [at] polak [dot] ro
 * @version 201509
 * @since 200802
 */
public class Headers {
    protected String status = "";
    protected String headersString = "";
    protected String postParameters = "";
    protected Hashtable vars = new Hashtable<String, String>();

    /**
     * Parses headers
     *
     * @param headersString raw headers
     */
    public void parse(String headersString) {
        this.headersString = headersString;

        String headerLines[] = headersString.split("\n");
        for (int i = 0; i < headerLines.length; i++) {
            try {
                String headerLineValues[] = headerLines[i].split(": ");
                setHeader(headerLineValues[0], headerLineValues[1].substring(0, headerLineValues[1].length() - 1)); // Avoid
                // \n\r
            } catch (ArrayIndexOutOfBoundsException e) {
                // e.printStackTrace();
            }
        }
    }

    /**
     * Sets header
     *
     * @param headerName  header name
     * @param headerValue header value
     */
    public void setHeader(String headerName, String headerValue) {
        vars.put(headerName, headerValue);
    }

    /**
     * Returns header's value
     *
     * @param headerName name of the header
     * @return header's value
     */
    public String getHeader(String headerName) {
        return (String) vars.get(headerName);
    }

    /**
     * Returns the status, the first line of HTTP headers
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns raw headers
     *
     * @return raw headers
     */
    public String toString() {
        return headersString;
    }
}