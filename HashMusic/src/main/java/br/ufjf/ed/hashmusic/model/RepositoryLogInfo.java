package br.ufjf.ed.hashmusic.model;

/**
 * Classe de objetos para arquivos XML de log de importação de músicas
 * @author Luis Augusto
 */
public class RepositoryLogInfo extends LogInfo
{
    private String mp3FilePath;
    
    public RepositoryLogInfo()
    {
        this("");
    }
    
    public RepositoryLogInfo(String mp3FilePath)
    {
        super();
        this.mp3FilePath = mp3FilePath;
    }
    
    public RepositoryLogInfo(String mp3FilePath, String message)
    {
        super(message);
        this.mp3FilePath = mp3FilePath;
    }

    /**
     * @return the mp3FilePath
     */
    public String getMp3FilePath() {
        return mp3FilePath;
    }

    /**
     * @param mp3FilePath the mp3FilePath to set
     */
    public void setMp3FilePath(String mp3FilePath) {
        this.mp3FilePath = mp3FilePath;
    }

}
