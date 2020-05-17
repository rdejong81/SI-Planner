package Windows;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProjectColor
{
    GREEN(0,"style1","Green",Color.rgb(119, 192, 75, 0.9)),
    BLUE(1,"style2","Blue",Color.rgb(65, 143, 203, 0.9)),
    YELLOW(2,"style3","Yellow",Color.rgb(247, 209, 91, 0.9)),
    PURPLE(3,"style4","Purple",Color.rgb(157, 91, 159, 0.9)),
    RED(4,"style5","Red",Color.rgb(208, 82, 95, 0.9)),
    ORANGE(5,"style6","Orange",Color.rgb(249, 132, 75, 0.9)),
    BROWN(6,"style7","Brown",Color.rgb(174, 102, 62, 0.9)),
    BLACK(7,"style8","Black",Color.BLACK);

private int id;
private String type,name;
private Color color;
private ProjectColor(int id, String type,String name, Color color){ this.id = id; this.type = type; this.name = name; this.color = color;}
public int getId() {return id;}
public String getName() {return name;}
public String getType() {return type;}
public Color getColor() {return color;}

    static final Map<Integer, ProjectColor> ids = Arrays.stream(ProjectColor.values())
            .collect(Collectors.toMap(ProjectColor::getId, Function.identity()));

    public static ProjectColor fromId(final int id) {
        if(id > ids.size()) return BLUE;
        return ids.get(id);
    }


}
