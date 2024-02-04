package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

@Getter
@Setter
public class MuseumSystemPanel extends BasePanel {
    private JList<String> tableList;
    private JButton executeButton, closeButton;
    private JTextArea queryField;
    private JTable resultTable;

    public MuseumSystemPanel(String key) {
        super(key);

        // Carrega o Tema FlatIntelliJLaf
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            ErrorHandler.logAndExit(e);
        }

        // Configura o layout do painel
        setLayout(new BorderLayout());

        // Painel esquerdo com as tabelas disponíveis
        tableList = new JList<>();
        JScrollPane leftPanel = new JScrollPane(tableList);
        leftPanel.setPreferredSize(new Dimension(200, 0));
        add(leftPanel, BorderLayout.WEST);

        // Painel superior com o botão de execução
        JPanel topPanel = new JPanel();
        executeButton = new JButton("Executar consulta");
        topPanel.add(executeButton, BorderLayout.CENTER);


        // Botão de fechar no canto superior direito
        closeButton = new JButton("X");
        closeButton.setSize(40, 40);
        closeButton.setContentAreaFilled(true);
        closeButton.setBackground(Color.RED);
        closeButton.setBorderPainted(true);
        closeButton.setOpaque(true);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        closeButton.setFont(BaseFrame.getFont(16));
        topPanel.add(closeButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Painel central com o campo de consulta
        queryField = new JTextArea();
        JScrollPane queryPanel = new JScrollPane(queryField);
        add(queryPanel, BorderLayout.CENTER);

        // Painel inferior com a tabela de resultados
        resultTable = new JTable();
        JScrollPane resultPanel = new JScrollPane(resultTable);
        add(resultPanel, BorderLayout.SOUTH);

    }

    /**
     * Carrega a lista de tabelas disponíveis no banco de dados
     *
     * @param tables Lista de tabelas disponíveis no banco de dados
     */
    public void setTableList(String[] tables) {
        tableList.setListData(tables);
    }

    /**
     * Carrega o modelo de tabela com os resultados da consulta
     *
     * @param model Modelo de tabela com os resultados da consulta
     */
    public void setResultTable(TableModel model) {
        resultTable.setModel(model);
    }

    public void setResultError(String errorMessage) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Erro");
        model.addRow(new Object[]{errorMessage});

        setResultTable(model);

    }

}