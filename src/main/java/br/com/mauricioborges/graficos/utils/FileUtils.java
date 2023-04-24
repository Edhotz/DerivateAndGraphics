package br.com.mauricioborges.graficos.utils;

import static br.com.mauricioborges.graficos.utils.FileUtils.DiretorioInicial.ESTE_COMPUTADOR;
import java.io.File;
import java.util.List;
import java.util.Objects;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Utilitários para trabalhar com arquivos
 *
 * @author Mauricio Borges
 * @since 2020
 */
public abstract class FileUtils {

    private static File diretorioAtual = ESTE_COMPUTADOR.getDiretorio();

    /**
     * Retorna o último diretorio utilizado pelo usuário em algum FileChooser
     *
     * @return diretório atual
     */
    public static File getDiretorioAtual() {
        return diretorioAtual;
    }

    private static void setDiretorioAtual(File diretorio) {
        FileUtils.diretorioAtual = Objects.requireNonNull(diretorio, "Diretório não pode ser nulo.");
    }

    private static FileChooser buildFileChooser(File diretorioInicial) {
        FileChooser chooser = new FileChooser();
        if (diretorioInicial != null) {
            chooser.setInitialDirectory(diretorioInicial.canRead() ? diretorioInicial : ESTE_COMPUTADOR.getDiretorio());
        }

        ExtensionFilter filtro = new ExtensionFilter("Arquivo de imagem PNG", "*.png");
        chooser.getExtensionFilters().add(filtro);

        return chooser;
    }

    /**
     * Escolher arquivo no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @param tipoDeJanela Abrir arquivo ou salvar
     * @return arquivo
     */
    public static File escolherArquivoNoArmazenamento(File diretorioInicial, Tipo tipoDeJanela) {
        File arquivo = switch (tipoDeJanela) {
            case OPEN ->
                buildFileChooser(diretorioInicial).showOpenDialog(new Stage());
            case SAVE ->
                buildFileChooser(diretorioInicial).showSaveDialog(new Stage());
            default ->
                throw new RuntimeException("Tipo inválido.");
        };
        if (arquivo != null) {
            setDiretorioAtual(arquivo.getParentFile());
        }
        return arquivo;
    }

    /**
     * Escolher arquivo no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @param tipoDeJanela Abrir arquivo ou salvar
     * @return arquivo
     */
    public static File escolherArquivoNoArmazenamento(DiretorioInicial diretorioInicial, Tipo tipoDeJanela) {
        return escolherArquivoNoArmazenamento(diretorioInicial.getDiretorio(), tipoDeJanela);
    }

    /**
     * Escolher vários arquivos no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @return lista de arquivos
     */
    public static List<File> escolherVariosArquivosNoArmazenamento(File diretorioInicial) {
        List<File> arquivos = buildFileChooser(diretorioInicial).showOpenMultipleDialog(new Stage());
        if (arquivos != null) {
            setDiretorioAtual(arquivos.get(0).getParentFile());
        }
        return arquivos;
    }

    /**
     * Escolher vários arquivos no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @return lista de arquivos
     */
    public static List<File> escolherVariosArquivosNoArmazenamento(DiretorioInicial diretorioInicial) {
        return escolherVariosArquivosNoArmazenamento(diretorioInicial.getDiretorio());
    }

    /**
     * Escolher diretório no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @return diretório
     */
    public static File escolherDiretorioNoArmazenamento(File diretorioInicial) {
        DirectoryChooser chooser = new DirectoryChooser();
        if (diretorioInicial != null) {
            chooser.setInitialDirectory(diretorioInicial.canRead() ? diretorioInicial : ESTE_COMPUTADOR.getDiretorio());
        }

        File diretorio = chooser.showDialog(new Stage());
        if (diretorio != null) {
            setDiretorioAtual(diretorio.getParentFile());
        }
        return diretorio;
    }

    /**
     * Escolher diretório no armazenamento do computador.
     *
     * @param diretorioInicial diretório inicial
     * @return diretório
     */
    public static File escolherDiretorioNoArmazenamento(DiretorioInicial diretorioInicial) {
        return escolherDiretorioNoArmazenamento(diretorioInicial.getDiretorio());
    }

    /**
     * Construir um pathname independente do sistema operacional, usando o
     * File.separatorChar para separar os diretórios
     *
     * @param initialPathName pathname inicial
     * @param subPaths subpaths
     * @return string com pathname completo
     */
    public static String createPathName(String initialPathName, String... subPaths) {
        Objects.requireNonNull(initialPathName);
        StringBuilder pathName = new StringBuilder();
        pathName.append(initialPathName);
        for (String subPath : subPaths) {
            pathName.append(File.separatorChar).append(subPath);
        }
        return pathName.toString();
    }

    /**
     * Tipo de janela
     */
    public static enum Tipo {
        /**
         * Abrir arquivos
         */
        OPEN,
        /**
         * Abrir múltiplos arquivos
         */
        OPEN_MULTIPLE,
        /**
         * Salvar arquivos
         */
        SAVE
    }

    /**
     * Diretório inicial ao abrir a janela
     */
    public static enum DiretorioInicial {
        /**
         * Este computador (Windows)
         */
        ESTE_COMPUTADOR(null),
        /**
         * Raiz da pasta do usuário
         */
        PASTA_USUARIO(new File(System.getProperty("user.home")));

        private final File diretorio;

        private DiretorioInicial(File diretorio) {
            this.diretorio = diretorio;
        }

        /**
         * Obter o diretório
         *
         * @return diretório
         */
        public File getDiretorio() {
            return diretorio;
        }
    }

}
