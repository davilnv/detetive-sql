package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.bll.MuseumSystemBll;
import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.statics.SceneInfo;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Logic {
    private List<Layer> layers;
    private List<NPC> npcs;
    private Computer computer;
    private Question currentQuestion;
    private boolean win;

    public Logic() {
        layers = new ArrayList<>();
        npcs = new ArrayList<>();
        layers.add(new Layer("floor", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/floor.txt"));
        layers.add(new Layer("second-floor", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/second-floor.txt"));
        layers.add(new Layer("colision", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/colision.txt"));
        layers.add(new Layer("top", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/top.txt"));
        layers.add(new Layer("front-top", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/front-top.txt"));

        npcs.add(new NPC(2, 64, 64, 13, 21, 136, 128, "/assets/images/sprite/it-employee_universal.png", "Sophie Campbell", "TI", "/assets/images/scene/scene-it-employee.png", SceneInfo.dialoguesItEmployee));
        npcs.add(new NPC(2, 64, 64, 13, 21, 148, 750, "/assets/images/sprite/sprite-director_universal.png", "Sir Alexander Kensington", "Diretor", "/assets/images/scene/scene-director.png", SceneInfo.dialoguesDirector));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1325, 755, "/assets/images/sprite/chief-curator_universal.png", "Dra. Eleanor Thornton", "Curadora", "/assets/images/scene/scene-chief-curator.png", SceneInfo.dialoguesChiefCurator));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1045, 925, "/assets/images/sprite/security-guard_universal.png", "William Smith", "Segurança", "/assets/images/scene/scene-security-guard.png", SceneInfo.dialoguesSecurityGuard));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1010, 250, "/assets/images/sprite/caretaker_universal.png", "James Turner", "Zelador", "/assets/images/scene/scene-caretaker.png", SceneInfo.dialoguesCaretaker));
        npcs.add(new NPC(2, 64, 64, 13, 21, 755, 545, "/assets/images/sprite/museum-visitor_universal.png", "Lucy Bennett", "Visitante", "/assets/images/scene/scene-museum-visitor.png", SceneInfo.dialoguesMuseumVisitor));
        npcs.add(new NPC(2, 64, 64, 13, 21, 940, 1040, "/assets/images/sprite/newscaster_universal.png", "Isabella Kensington", "Jornalista", "/assets/images/scene/scene-newscaster.png", SceneInfo.dialoguesNewscaster));

        computer = new Computer(32, 190, 74, 84);
    }

    public void playGame(GameController gameController) {
        currentQuestion = gameController.getMapPanel().getPhone().getCurrentQuestion();

        if (currentQuestion != null) {
            if (currentQuestion.isAnswered()) {
                gameController.getMapPanel().getPhone().nextQuestion();
            }
        }

    }

    public List<String> checkAnswer(TableModel tableModel, int questionIndex) {
        switch (questionIndex) {
            case 0: {
                List<String> correctAnswer = MuseumSystemBll.getAnswerDayAcessUsers();

                List<String> playerAnswer = new ArrayList<>();

                // Primeira pergunta
                // Verificar se no result possui uma coluna com o nome "nome"
                System.out.println("Verificando resposta da pergunta " + (questionIndex + 1));
                System.out.println("Colunas: " + tableModel.getColumnCount());
                for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                    System.out.println("Coluna: " + tableModel.getColumnName(columnIndex));
                    if (tableModel.getColumnName(columnIndex).equals("NOME")) {
                        System.out.println("Coluna encontrada");
                        for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
                            String name = (String) tableModel.getValueAt(rowIndex, columnIndex);
                            System.out.println("Nome: " + name);
                            playerAnswer.add(name);
                        }

                        System.out.println("Resposta do jogador: " + playerAnswer);
                        System.out.println("Resposta correta: " + correctAnswer);

                        // Compara a resposta do jogador com a resposta correta
                        if (playerAnswer.size() != correctAnswer.size()) {
                            System.out.println("Resultado: Resposta incorreta");
                            return null;
                        }

                        for (String npc : playerAnswer) {
                            if (!correctAnswer.contains(npc)) {
                                System.out.println("Resultado: Resposta incorreta");
                                return null;
                            }
                        }

                        System.out.println("Resultado: Resposta correta");
                        return correctAnswer;

                    }
                }
            }
            case 1: {
                List<String> correctAnswer = MuseumSystemBll.getAnswerDayAcessUsers();

                List<String> playerAnswer = new ArrayList<>();

                // Primeira pergunta
                // Verificar se no result possui uma coluna com o nome "nome"
                System.out.println("Verificando resposta da pergunta " + (questionIndex + 1));
                System.out.println("Colunas: " + tableModel.getColumnCount());
                for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                    System.out.println("Coluna: " + tableModel.getColumnName(columnIndex));
                    if (tableModel.getColumnName(columnIndex).equals("NOME")) {
                        System.out.println("Coluna encontrada");
                        for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
                            String name = (String) tableModel.getValueAt(rowIndex, columnIndex);
                            System.out.println("Nome: " + name);
                            playerAnswer.add(name);
                        }

                        System.out.println("Resposta do jogador: " + playerAnswer);
                        System.out.println("Resposta correta: " + correctAnswer);

                        // Compara a resposta do jogador com a resposta correta
                        if (playerAnswer.size() != correctAnswer.size()) {
                            System.out.println("Resultado: Resposta incorreta");
                            return null;
                        }

                        for (String npc : playerAnswer) {
                            if (!correctAnswer.contains(npc)) {
                                System.out.println("Resultado: Resposta incorreta");
                                return null;
                            }
                        }

                        System.out.println("Resultado: Resposta correta");
                        return correctAnswer;

                    }
                }
            }
            default:
                return null;
        }

    }

    public Layer getLayer(String key) {
        for (Layer layer : layers) {
            if (layer.getKey().equals(key)) {
                return layer;
            }
        }
        return null;
    }
}
