package br.ufjf.ed.hashmusic.repository;

import br.ufjf.ed.hashmusic.hash.HashTest;
import br.ufjf.ed.hashmusic.hash.IHash;
import br.ufjf.ed.hashmusic.helper.FileHelper;
import br.ufjf.ed.hashmusic.helper.XmlHelper;
import br.ufjf.ed.hashmusic.model.MusicInfo;
import br.ufjf.ed.hashmusic.view.MainWindow;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        fileChooser.setDialogTitle("Escolha diretÃ³rios para importaÃ§Ã£o de mÃºsicas MP3");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            return fileChooser.getSelectedFiles();
        
        return null;
    }

    public SwingWorker getImportDirectoryWorker(final File[] dirList)
    {
        SwingWorker worker = new SwingWorker()
        {
            @Override
            protected Void doInBackground() throws InterruptedException 
            {
                ArrayList<MusicInfo> musicInfoList = new ArrayList<>();

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
                                        
                                        if((tag.getArtist() != null) && (tag.getAlbum() != null) && (tag.getTitle() != null))
                                        {
                                            MusicInfo musicInfo = new MusicInfo(tag.getArtist(), tag.getAlbum(), tag.getTitle());

                                            String mp3HashDir = HashMusic(musicInfo);

                                            if (!FileHelper.checkPathExists(mp3HashDir))
                                                FileHelper.createDirectory(mp3HashDir);

                                            File mp3HashFile = new File(FileHelper.formatSubDirectory(mp3HashDir, musicInfo.getTitle() + ".mp3"));
                                            Files.copy(mp3File.toPath(), mp3HashFile.toPath(), REPLACE_EXISTING);

                                            musicInfoList.add(musicInfo);
                                        }
                                    }
                                } 
                                catch (IOException ex) 
                                {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                                catch (UnsupportedTagException ex) 
                                {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                                catch (InvalidDataException ex) 
                                {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else
                                break;
                        }
                    }
                    else
                        break;
                }

                if (!isCancelled()) 
                {
                    try
                    {
                        if (XmlHelper.saveXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), musicInfoList))
                            JOptionPane.showMessageDialog(null, "MÃºsicas importadas com sucesso!");
                    } 
                    catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                return null;
            }
        };
        
        return worker;
    }
    
    public void createRepository()
    {
        if(!FileHelper.checkPathExists(PATH_REPOSITORY))
        {
            if(FileHelper.createDirectory(PATH_REPOSITORY))
                JOptionPane.showMessageDialog(null, "Pasta do repositÃ³rio criada com sucesso!");
        }
    }
    
    public void loadRepository() throws IOException
    {
        this.repositoryList = (ArrayList<MusicInfo>)XmlHelper.readXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), repositoryList);
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
    
    public void openMusic(MusicInfo musicInfo) throws IOException
    {
        if((musicInfo != null) && Desktop.isDesktopSupported())
        {
            String mp3HashDir = HashMusic(musicInfo);

            if (FileHelper.checkPathExists(FileHelper.formatSubDirectory(mp3HashDir, musicInfo.getTitle() + ".mp3"))) 
            {
                File mp3File = new File(FileHelper.formatSubDirectory(mp3HashDir, musicInfo.getTitle() + ".mp3"));
                
                Desktop.getDesktop().open(mp3File);
            }
        }
    }
    
    private String HashMusic(MusicInfo musicInfo)
    {
        String musicKey = String.format("%s#%s", musicInfo.getArtist(), musicInfo.getAlbum());

        IHash hashing = new HashTest();
        String hashKey = hashing.hashDirectory(musicKey);

        return FileHelper.formatSubDirectory(PATH_REPOSITORY, hashKey);
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
}
