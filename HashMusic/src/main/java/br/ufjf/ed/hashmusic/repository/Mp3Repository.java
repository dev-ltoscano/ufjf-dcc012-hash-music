package br.ufjf.ed.hashmusic.repository;

import br.ufjf.ed.hashmusic.hash.IHashDirectory;
import br.ufjf.ed.hashmusic.hash.directory.HashMd5;
import br.ufjf.ed.hashmusic.helper.FileHelper;
import br.ufjf.ed.hashmusic.helper.XmlHelper;
import br.ufjf.ed.hashmusic.model.MusicInfo;
import br.ufjf.ed.hashmusic.model.RepositoryLogInfo;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Luis Augusto
 */
public class Mp3Repository 
{
    private final String PATH_REPOSITORY;
    private ArrayList<MusicInfo> repositoryList;
    
    public Mp3Repository()
    {
        this(FileHelper.formatSubDirectory(System.getProperty("user.dir"), "HashMusic", "Repository"));
    }
    
    public Mp3Repository(String pathRepository)
    {
        this.PATH_REPOSITORY = pathRepository;
        this.repositoryList = new ArrayList<>();
    }
    
    /**
     * @return the PATH_REPOSITORY
     */
    public String getPathRepository() 
    {
        return PATH_REPOSITORY;
    }
    
    /**
     * @return the repositoryList
     */
    public ArrayList<MusicInfo> getRepositoryList() 
    {
        return repositoryList;
    }
    
    public File[] showImportDirectoryChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Escolha diretórios para importação de músicas MP3");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            return fileChooser.getSelectedFiles();
        
        return null;
    }

    public SwingWorker getImportDirectoryWorker(final File[] dirList)
    {
        SwingWorker worker;
        worker = new SwingWorker()
        {
            private final ArrayList<RepositoryLogInfo> logInfoList = new ArrayList<>();
            
            @Override
            protected Void doInBackground() throws InterruptedException 
            {
                for (File dir : dirList) 
                {
                    if (!isCancelled())
                    {
                        ArrayList<File> allMp3Files = FileHelper.getAllFilesList(dir, "mp3");
                        
                        for (File mp3File : allMp3Files) 
                        {
                            if (!isCancelled()) 
                            {
                                try 
                                {
                                    Mp3File mp3 = new Mp3File(mp3File.getAbsolutePath());

                                    if (mp3.hasId3v2Tag())
                                    {
                                        ID3v2 tag = mp3.getId3v2Tag();
                                        
                                        if (((tag.getArtist() != null) && !tag.getArtist().isEmpty())
                                                && ((tag.getAlbum() != null) && !tag.getAlbum().isEmpty())
                                                && ((tag.getTitle() != null) && !tag.getTitle().isEmpty()))
                                        {
                                            MusicInfo musicInfo = new MusicInfo(tag.getArtist(), tag.getAlbum(), tag.getTitle());
                                            
                                            if(getMusicInfo(musicInfo.getTitle()) == null)
                                            {
                                                String mp3HashDir = getHashDir(musicInfo);

                                                if (!FileHelper.checkPathExists(mp3HashDir))
                                                    FileHelper.createDirectory(mp3HashDir);
                                                    
                                                String mp3HashName = getHashName(musicInfo);
                                                
                                                String mp3Path = FileHelper.formatSubDirectory(mp3HashDir, mp3HashName);

                                                File mp3HashFile = new File(mp3Path);
                                                Files.copy(mp3File.toPath(), mp3HashFile.toPath(), REPLACE_EXISTING);

                                                repositoryList.add(musicInfo);
                                            }
                                        }
                                    }
                                }
                                catch (IOException | UnsupportedTagException | InvalidDataException ex) 
                                {
                                    logInfoList.add(new RepositoryLogInfo(mp3File.getAbsolutePath(), ex.getMessage()));
                                }
                            }
                            else
                                break;
                        }
                    }
                    else
                        break;
                }

                return null;
            }
            
            @Override
            protected void done()
            {
                try 
                {
                    XmlHelper.saveXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "ImportLog.xml"), logInfoList);
                    
                    if (XmlHelper.saveXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), repositoryList)) 
                        JOptionPane.showMessageDialog(null, "Músicas importadas com sucesso!");
                } 
                catch (FileNotFoundException ex) 
                {
                    JOptionPane.showMessageDialog(null, String.format("Erro ao importar as músicas: %s", ex.getMessage()));
                } 
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(null, String.format("Erro ao importar as músicas: %s", ex.getMessage()));
                }
            }
        };
        
        return worker;
    }
    
    public void createRepository()
    {
        if(!FileHelper.checkPathExists(PATH_REPOSITORY))
        {
            if(FileHelper.createDirectory(PATH_REPOSITORY))
                JOptionPane.showMessageDialog(null, "Pasta do repositório criada com sucesso!");
            else
                JOptionPane.showMessageDialog(null, "Não foi possível criar a pasta do repositório!");
        }
    }
    
    public void loadRepository() throws IOException
    {
        this.repositoryList = (ArrayList<MusicInfo>)XmlHelper.readXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), repositoryList);
    }

    public MusicInfo getMusicInfo(String title)
    {
        if((title != null) && !title.isEmpty())
        {
            for (MusicInfo info : repositoryList) 
            {
                if (info.getTitle().toLowerCase().equals(title.toLowerCase()))
                    return info;
            }
        }
        
        return null;
    }
    
    public void openMusic(MusicInfo musicInfo) throws IOException, UnsupportedOperationException
    {
        if(Desktop.isDesktopSupported())
        {
            String mp3HashDir = getHashDir(musicInfo);
            String mp3HashName = getHashName(musicInfo);
            String mp3Path = FileHelper.formatSubDirectory(mp3HashDir, mp3HashName);

            Desktop.getDesktop().open(new File(mp3Path));
        }
        else
            throw new UnsupportedOperationException();
    }

    public ArrayList<MusicInfo> filterByArtist(String artist)
    {
        return this.filterByArtist(artist, repositoryList);
    }
    
    public ArrayList<MusicInfo> filterByAlbum(String album)
    {
        return this.filterByAlbum(album, repositoryList);
    }

    public ArrayList<MusicInfo> filterByArtistAndAlbum(String artist, String album)
    {
        ArrayList<MusicInfo> artistMusicList = this.filterByArtist(artist, repositoryList);
        return this.filterByAlbum(album, artistMusicList);
    }
    
    private ArrayList<MusicInfo> filterByArtist(String artist, ArrayList<MusicInfo> musicInfoList)
    {
        ArrayList<MusicInfo> filtred = new ArrayList<>();
        
        for(MusicInfo info : musicInfoList)
        {
            if(info.getArtist().toLowerCase().equals(artist.toLowerCase()))
                filtred.add(info);
        }
        
        return filtred;
    }
    
    private ArrayList<MusicInfo> filterByAlbum(String album, ArrayList<MusicInfo> musicInfoList)
    {
        ArrayList<MusicInfo> filtred = new ArrayList<>();
        
        for(MusicInfo info : musicInfoList)
        {
            if(info.getAlbum().toLowerCase().equals(album.toLowerCase()))
                filtred.add(info);
        }
        
        return filtred;
    }
    
    private String getHashDir(MusicInfo musicInfo)
    {
        String musicKey = String.format("%s#%s", musicInfo.getArtist(), musicInfo.getAlbum());

        IHashDirectory hashing = new HashMd5();
        String hashKey = hashing.getHash(musicKey);
        
        return FileHelper.formatSubDirectory(PATH_REPOSITORY, hashKey);
    }
    
    private String getHashName(MusicInfo musicInfo)
    {
//        String musicKey = String.format("%s#%s", musicInfo.getArtist(), musicInfo.getAlbum());
//
//        IHash hashing = new HashTest();
//        String hashKey = hashing.hashName(musicKey);
//        
        
        return FileHelper.replaceInvalidCharacters(musicInfo.getTitle()) + ".mp3";
    }
}
