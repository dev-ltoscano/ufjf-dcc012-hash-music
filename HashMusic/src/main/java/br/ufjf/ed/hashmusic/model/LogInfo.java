package br.ufjf.ed.hashmusic.model;

/**
 *
 * @author Luis Augusto
 */
public class LogInfo 
{
    private String message;
    
    public LogInfo()
    {
        this("Log Message");
    }
    
    public LogInfo(String message)
    {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
