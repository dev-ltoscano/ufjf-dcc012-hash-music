package br.ufjf.ed.hashmusic.repository;

import br.ufjf.ed.hashmusic.hash.IHashDirectory;
import br.ufjf.ed.hashmusic.hash.IHashName;
import br.ufjf.ed.hashmusic.hash.directory.HashDivision;
import br.ufjf.ed.hashmusic.hash.name.HashMultiplication;
import br.ufjf.ed.hashmusic.helper.FileHelper;
import br.ufjf.ed.hashmusic.helper.XmlHelper;
import br.ufjf.ed.hashmusic.model.MusicInfo;
import br.ufjf.ed.hashmusic.model.RepositoryLogInfo;
import br.ufjf.ed.hashmusic.sort.MergeSort;
import br.ufjf.ed.hashmusic.viewmodel.component.comparator.MusicInfoArtistAndAlbumComparator;
import br.ufjf.ed.hashmusic.viewmodel.component.MusicInfoFilterList;
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
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Classe que representa um repositório de músicas Mp3
 * @author Luis Augusto
 */
public class Mp3Repository 
{
    // Diretório do repositório
    private final String PATH_REPOSITORY;

    // Lista de MusicInfo das músicas contidas no repositório
    private List<MusicInfo> repositoryList;
    
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
    public List<MusicInfo> getRepositoryList() 
    {
        return repositoryList;
    }
    
    /**
     * Mostra a janela de escolha dos diretórios para a importação de novas músicas
     * @return Lista de diretórios selecionados
     */
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

    /**
     * Gera um worker (Multi-Threading) para a operação de importações de músicas
     * @param dirList Lista de diretórios para importação
     * @return Worker da operação de importação
     */
    public SwingWorker getImportDirectoryWorker(final File[] dirList)
    {
        SwingWorker worker;
        worker = new SwingWorker()
        {
            // Lista que guarda informações de erros durante o processo de importação
            private final ArrayList<RepositoryLogInfo> logInfoList = new ArrayList<>();
            
            @Override
            protected Void doInBackground() throws InterruptedException 
            {
                // Percorre a lista de todos os diretórios para importação
                for (File dir : dirList) 
                {
                    // Verifica se o worker não foi cancelado, se foi, sai da iteração
                    if (!isCancelled())
                    {
                        // Lista com todos os Mp3 do diretório
                        ArrayList<File> allMp3Files = FileHelper.getAllFilesList(dir, "mp3");
                        
                        // Percorre cada arquivo da lista de Mp3 do diretório
                        for (File mp3File : allMp3Files) 
                        {
                            // Verifica se o worker não foi cancelado, se foi, sai da iteração
                            if (!isCancelled()) 
                            {
                                try 
                                {
                                    // Novo objeto da classe Mp3File da biblioteca mp3agic
                                    Mp3File mp3 = new Mp3File(mp3File.getAbsolutePath());
                                    
                                    // Verifica se o arquivo Mp3 contém tags ID3v2
                                    if (mp3.hasId3v2Tag())
                                    {
                                        // Pega as tags ID3v2
                                        ID3v2 tag = mp3.getId3v2Tag();
                                        
                                        // Verifica se todas os atributos da tag estão preenchidos
                                        if (((tag.getArtist() != null) && !tag.getArtist().isEmpty())
                                                && ((tag.getAlbum() != null) && !tag.getAlbum().isEmpty())
                                                && ((tag.getTitle() != null) && !tag.getTitle().isEmpty()))
                                        {
                                            // Faz a verificação se a música já não existe no repositório, só adiciono músicas que não existem
                                            if(!containsMusicInfo(tag.getArtist(), tag.getAlbum(), tag.getTitle()))
                                            {
                                                // Preenche um novo objeto MusicInfo com as informações da tag
                                                MusicInfo musicInfo = new MusicInfo(tag.getArtist(), tag.getAlbum(), tag.getTitle());
                                                
                                                // Pega o hash para alocação do diretório
                                                String mp3HashDir = getHashDir(musicInfo);
                                                
                                                // Verifica se o diretório já existe, senão existir, cria o novo diretório
                                                if (!FileHelper.checkPathExists(mp3HashDir))
                                                    FileHelper.createDirectory(mp3HashDir);
                                                
                                                // Pega o hash para a renomeação do arquivo
                                                String mp3HashName = getHashName(musicInfo);
                                                
                                                // Concatena o diretório com o nome do arquivo
                                                String mp3Path = FileHelper.formatSubDirectory(mp3HashDir, mp3HashName);
                                                
                                                // Copia a música para o repositório
                                                File mp3HashFile = new File(mp3Path);
                                                Files.copy(mp3File.toPath(), mp3HashFile.toPath(), REPLACE_EXISTING);
                                                
                                                // Adiciona as informações da música para a lista do repositório
                                                repositoryList.add(musicInfo);
                                            }
                                        }
                                    }
                                }
                                catch (IOException | UnsupportedTagException | InvalidDataException ex) 
                                {
                                    // Guardo o arquivo da música que gerou o erro e as informações da exceção para gerar um log da importação
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
                    // Ao término da operação de importação, crio um arquivo XML para o log da importação e um para guardar a lista de informações das músicas do repositório
                    XmlHelper.saveXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "ImportLog.xml"), logInfoList);
                    XmlHelper.saveXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), repositoryList);
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
    
    /**
     * Cria o repositório se ele não existir
     */
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
    
    /**
     * Carrega a lista de informações das músicas adicionadas no repositório
     * @throws IOException 
     */
    public void loadRepository() throws IOException
    {
        ArrayList<MusicInfo> tempList = (ArrayList<MusicInfo>)XmlHelper.readXml(FileHelper.formatSubDirectory(PATH_REPOSITORY, "Music.xml"), repositoryList);
        
        MergeSort mergeSort = new MergeSort();
        this.repositoryList = mergeSort.sort(tempList, new MusicInfoArtistAndAlbumComparator());
    }

    /**
     * Pega as informações de uma música do repositório filtrando pelo título
     * @param title Título da música
     * @return Informações da música se a mesma for encontrada
     */
    public MusicInfo getMusicInfo(String title)
    {
        if((title != null) && !title.isEmpty())
        {
            for (MusicInfo info : repositoryList) 
            {
                if (info.getTitle().equalsIgnoreCase(title))
                    return info;
            }
        }
        
        return null;
    }
    
    /**
     * Toca uma música do repositório no player padrão do sistema
     * @param musicInfo Informações da música do repositório
     * @throws IOException
     * @throws UnsupportedOperationException 
     */
    public void openMusic(MusicInfo musicInfo) throws IOException, UnsupportedOperationException
    {
        // Verifica se o sistema dá suporte para a chamada
        if(Desktop.isDesktopSupported())
        {
            // Pega o hash do diretório
            String mp3HashDir = getHashDir(musicInfo);
            
            //Pega o hash do nome do arquivo
            String mp3HashName = getHashName(musicInfo);
            
            // Concatena os hashes para descobrir o caminho completo do arquivo Mp3 no repositório
            String mp3Path = FileHelper.formatSubDirectory(mp3HashDir, mp3HashName);
            
            // Abre a música no player padrão do sistema
            Desktop.getDesktop().open(new File(mp3Path));
        }
        else
            throw new UnsupportedOperationException();
    }
    
    /**
     * Filtra as músicas do repositório por um artista
     * @param artist Nome do artista
     * @return Lista das músicas do artista
     */
    public List<MusicInfo> filterByArtist(String artist)
    {
        return this.filterByArtist(artist, repositoryList);
    }
    
    /**
     * Filtra as músicas do repositório por um álbum
     * @param album Nome do album
     * @return Lista das músicas de um álbum
     */
    public List<MusicInfo> filterByAlbum(String album)
    {
        return this.filterByAlbum(album, repositoryList);
    }
    
    /**
     * Filtra as músicas do repositório por um artista e um álbum
     * @param artist Nome do artista
     * @param album Nome do album
     * @return Lista das músicas do artista e do álbum
     */
    public List<MusicInfo> filterByArtistAndAlbum(String artist, String album)
    {
        List<MusicInfo> artistMusicList = this.filterByArtist(artist, repositoryList);
        return this.filterByAlbum(album, artistMusicList);
    }
    
    /**
     * Função auxiliar para filtrar as músicas pelo artista
     * @param artist Nome do artista
     * @param musicInfoList Lista com as informações das músicas a serem buscadas
     * @return Lista das músicas do artista
     */
    private List<MusicInfo> filterByArtist(String artist, List<MusicInfo> musicInfoList)
    {
        if(musicInfoList != null)
        {
            MusicInfoFilterList infoList = new MusicInfoFilterList();
            infoList.addAll(musicInfoList);

            MusicInfo info = new MusicInfo();
            info.setArtist(artist);

            int firstIndex = infoList.firstIndex(info);
            int lastIndex = infoList.lastIndex(firstIndex, info);

            if (firstIndex != -1) {
                return infoList.subList(firstIndex, lastIndex + 1);
            }
        }
        
        return null;
    }
    
    /**
     * Função auxiliar para filtrar as músicas pelo artista
     * @param album Nome do álbum
     * @param musicInfoList Lista com as informações das músicas a serem buscadas
     * @return Lista das músicas do álbum
     */
    private List<MusicInfo> filterByAlbum(String album, List<MusicInfo> musicInfoList)
    {
        if(musicInfoList != null)
        {
            MusicInfoFilterList infoList = new MusicInfoFilterList();
            infoList.addAll(musicInfoList);

            MusicInfo infoT = new MusicInfo();
            infoT.setAlbum(album);

            int firstIndex = infoList.firstIndex(infoT);
            int lastIndex = infoList.lastIndex(firstIndex, infoT);

            if (firstIndex != -1) {
                return infoList.subList(firstIndex, lastIndex + 1);
            }
        }
        
        return null;
    }
    
    /**
     * Verifica se uma música já existe no repositório
     * @param artist Nome do artista
     * @param album Nome do álbum
     * @param title Título da música
     * @return (true) Se a música já existe; (false) Se a música não existe
     */
    private boolean containsMusicInfo(String artist, String album, String title)
    {
        for (MusicInfo info : repositoryList) 
        {
            if(info.equals(new MusicInfo(artist, album, title)))
                return true;
        }
        
        return false;
    }
    
    /**
     * Retorna o hash do diretório
     * @param musicInfo Informações da música
     * @return Hash do diretório
     */
    public String getHashDir(MusicInfo musicInfo)
    {
        // Chave usada para alocação do diretório: Nome do artista + '#' + Nome do álbum
        String musicKey = String.format("%s#%s", musicInfo.getArtist(), musicInfo.getAlbum());

        // Função de hash usada
        IHashDirectory hashing = new HashDivision(); // Para alterar a função de alocação de diretórios, basta alterar a instância da classe de Hash
        String hashKey = hashing.getHash(musicKey);
        
        // Formata o caminho do diretório colocando-o como subdiretório da pasta do repositório
        return FileHelper.formatSubDirectory(PATH_REPOSITORY, hashKey);
    }
    
    /**
     * Retorna o hash do nome do arquivo
     * @param musicInfo Informações da música
     * @return Hash do nome do arquivo
     */
    public String getHashName(MusicInfo musicInfo)
    {
        // Chave usada para alocação do diretório: Nome do artista + '#' + Nome do álbum + '#' + Título da música
        String musicKey = String.format("%s#%s#%s", musicInfo.getArtist(), musicInfo.getAlbum(), musicInfo.getTitle());

        // Função de hash usada
        IHashName hashing = new HashMultiplication();// Para alterar a função de renomeação de arquivos, basta alterar a instância da classe de Hash
        String hashKey = hashing.getHash(musicKey);
        
        // Formata o hash retirando caracteres inválidos para nomes de arquivo
        return FileHelper.replaceInvalidCharacters(hashKey).concat(".mp3");
    }
}