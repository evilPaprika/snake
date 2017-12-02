package app.gui;

import app.util.GameConsts;
import app.util.State;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class MenuPanel {
    private static BorderPane root;
    private Pane mainPane;
    private MenuBox menuBox;
    private  Scene scene;

    public MenuPanel() {

        root = new BorderPane();
        scene = new Scene(root, GameConsts.PANEL_WIDTH, GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
        ImageView img = new ImageView(new Image("img/menu.jpg"));
        img.setFitHeight(GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT);
        img.setFitWidth(GameConsts.PANEL_WIDTH);

        mainPane = new Pane();
        root.setCenter(mainPane);

        MenuItem singleGame = new MenuItem("ОДИН ИГРОК");
        MenuItem twoPlayerGame = new MenuItem("ДВА ИГРОКА");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(200,
                singleGame,twoPlayerGame, exitGame
        );


        singleGame.setOnMouseClicked(event -> {
            GamePanel panel = new GamePanel(scene, State.ONE_PLAYER);
            scene.setRoot(panel.asRoot());

        });

        twoPlayerGame.setOnMouseClicked(event -> {
            GamePanel panel = new GamePanel(scene, State.TWO_PLAYERS);
            scene.setRoot(panel.asRoot());
        });

        exitGame.setOnMouseClicked(event-> System.exit(0));
        menuBox = new MenuBox(mainMenu);
        mainPane.getChildren().addAll(img,menuBox);
        run();
    }

    public static BorderPane asRoot(){
        return root;
    }

    public Scene asScene(){ return scene;}

    public void run(){

        FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private static class MenuItem extends StackPane {
        public  MenuItem(String name){
            Rectangle bg = new Rectangle(200,20,Color.WHITE);
            bg.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD,14));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
            FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.WHITE);
            });
        }
    }
    private static class MenuBox extends Pane{
        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;
            Rectangle bg = new Rectangle(GameConsts.PANEL_WIDTH,GameConsts.PANEL_HEIGHT + GameConsts.HEIGHT,Color.LIGHTBLUE);
            bg.setOpacity(0.4);
            getChildren().addAll(bg, subMenu);
        }
        public void setSubMenu(SubMenu subMenu){
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
    }

    private static class SubMenu extends VBox {
        public SubMenu(int width, MenuItem...items){
            setSpacing(15);
            setTranslateY(150);
            setTranslateX(GameConsts.PANEL_WIDTH/2 - 2*GameConsts.WIDTH);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }
    }
}
