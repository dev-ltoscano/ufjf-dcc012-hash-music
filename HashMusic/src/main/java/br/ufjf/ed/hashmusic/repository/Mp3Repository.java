package br.ufjf.ed.hashmusic.repository;

import br.ufjf.ed.hashmusic.helper.FileHelper;
import br.ufjf.ed.hashmusic.helper.XmlHelper;
import br.ufjf.ed.hashmusic.model.MusicInfo;
import br.ufjf.ed.hashmusic.view.MainWindow;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author Luis Augusto
 */
public class Mp3Repository 
{
    private final String PATH_REPOSITORY;
    
    public Mp3Repository()
    {
        this(String.format("%s%s%s", System.getProperty("user.dir"), File.separator, "Repository"));
    }
    
    public Mp3Repository(String pathRepository)
    {
        this.PATH_REPOSITORY = pathRepository;
    }
    
    /**
     * @return the PATH_REPOSITORY
     */
    public String getPathRepository() 
    {
        return PATH_REPOSITORY;
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
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                ArrayList<MusicInfo> musicInfoList = new ArrayList<>();

                for (File dir : dirList) {
                    if (!isCancelled()) {
                        ArrayList<String> allMp3Files = FileHelper.getAllFilesList(dir, "mp3");

                        for (String mp3Path : allMp3Files) {
                            if (!isCancelled()) {
                                try {
                                    Mp3File mp3File = new Mp3File(mp3Path);

                                    if (mp3File.hasId3v2Tag()) {
                                        ID3v2 tagv2 = mp3File.getId3v2Tag();
                                        musicInfoList.add(new MusicInfo(tagv2.getArtist(), tagv2.getAlbum(), tagv2.getTitle()));
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnsupportedTagException ex) {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InvalidDataException ex) {
                                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }

                if (!isCancelled()) {
                    try {
                        if (XmlHelper.saveXml(String.format("%s%s%s", getPathRepository(), File.separator, "Music.xml"), musicInfoList)) {
                            JOptionPane.showMessageDialog(null, "Músicas importadas com sucesso!");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
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
                JOptionPane.showMessageDialog(null, "Pasta do repositório criada com sucesso!");
        }
    }
    
    public void loadRepository()
    {
//	Map<Object, Icon> icons = new HashMap<>();
//        
//        for(int i = 0; i < 10; i++)
//        {
//            icons.put(String.format("Music %s", String.valueOf(i)), MetalIconFactory.getTreeLeafIcon());
//        }
        
//        musicList.setModel(getMusicList());
//        musicList.setCellRenderer(new IconListRenderer(icons));
    }
    
    public void saveRepository()
    {
        
    }
}
