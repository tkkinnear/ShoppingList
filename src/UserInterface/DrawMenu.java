package UserInterface;

import java.util.List;

public class DrawMenu {

    private final int width;
    private final List<String> menuItems;

    public DrawMenu(List<String> menuItems) {
        this.width = setWidth(menuItems);
        this.menuItems = menuItems;
        draw();
    }

    private void draw() {

        System.out.println(topper());
        for (String each : menuItems) {
            System.out.println(menuItem(each));
        }
        System.out.println(topper());

    }

    private int setWidth(List<String> l) {
        int maxWitdth = 0;
        for (String each : l) {
            if (each.length() > maxWitdth) {
                maxWitdth = each.length();
            }
        }
        return maxWitdth + 6;
    }

    private String topper() {
        String top = "";
        for (int i = 0; i <= width; i++) {
            top += "*";
        }
        return top;
    }

    private String menuItem(String item) {
        String framedItem = "";
        int back = (width - item.length() - 3);
        framedItem += "*  ";
        framedItem += item;
        for (int i = 0; i <= back; i++) {
            if (i == back) {
                framedItem += "*";
            } else {
                framedItem += " ";
            }
        }
        return framedItem;
    }

}
