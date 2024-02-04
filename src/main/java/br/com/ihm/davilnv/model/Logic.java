package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.statics.SceneInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Logic {
    private int num1, num2;
    private List<Layer> layers;
    private List<NPC> npcs;
    private Computer computer;
    private boolean ganhou;

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

    public Layer getLayer(String key) {
        for (Layer layer : layers) {
            if (layer.getKey().equals(key)) {
                return layer;
            }
        }
        return null;
    }
}
