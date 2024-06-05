package vues.controlleurs;

import cahierIG.*;
import exceptions.CahierException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import vues.Observateur;
import vues.PanneauDeControle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ControlleurPageJour implements Observateur {


    @FXML
    private MenuItem ajoutPageButton;

    @FXML
    private MenuItem ajoutPhotoButton;

    @FXML
    private MenuItem ajoutTexteButton;

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Button retourPDGButton;

    @FXML
    /**
     * La node en bas à droite
     */
    private Pane smallNode1;

    @FXML
    /**
     * La node en bas à gauche
     */
    private Pane smallNode2;

    @FXML
    /**
     * La node en haut à droite
     */
    private Pane smallNode3;

    @FXML
    /**
     * La node en haut à gauche
     */
    private Pane smallNode4;

    @FXML
    private Label titrePage;

    @FXML
    private Button tournerDroite;

    @FXML
    private Button tournerGauche;

    @FXML
            private MenuItem supprNode;

    Stage stage;

    Pane nodeSelected;
    Cahier cahier;
    PanneauDeControle panneauDeControle;

    public ControlleurPageJour(Cahier c, PanneauDeControle panneauDeControle, Stage stage) {
        this.cahier = c;
        this.panneauDeControle = panneauDeControle;
        panneauDeControle.controlleurPageJour=this;
        c.ajouterObservateur(this);
        this.stage = stage;



    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        //System.out.println("Initialisation...");
        PageIG page = null;
        try {
            page = (PageIG)cahier.getPage(cahier.getCourante());
            //System.out.println("Chargement lors de l'initialisation :" +page);
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            this.smallNode1.getChildren().clear();
            this.smallNode2.getChildren().clear();
            this.smallNode3.getChildren().clear();
            this.smallNode4.getChildren().clear();

            retourPDGButton.setTooltip(new Tooltip("Retour à la page de garde"));
            tournerGauche.setTooltip(new Tooltip("Passage au jour précédent"));
            tournerDroite.setTooltip(new Tooltip("Passage au jour suivant"));

            ajoutPageButton.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
            ajoutPhotoButton.setAccelerator(KeyCombination.keyCombination("P"));
            ajoutTexteButton.setAccelerator(KeyCombination.keyCombination("T"));
            supprNode.setAccelerator(KeyCombination.keyCombination("Ctrl + S"));


        } catch (CahierException e) {
            throw new RuntimeException(e);
        }



    }


    @FXML
    void ajouterPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            if(cahier.getPageCourante().getNodeIG(this.getIntNodeSelected()) != null)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous remplacer la node actuelle?");
                ButtonType buttonYes = new ButtonType("Oui");
                ButtonType buttonNo = new ButtonType("Non");
                alert.getButtonTypes().setAll(buttonYes, buttonNo);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonYes) {
                    cahier.getPageCourante().setNodeIG(new NodeImageIG(image),getIntNodeSelected());
                }
            }else
            {
                cahier.getPageCourante().setNodeIG(new NodeImageIG(image),getIntNodeSelected());
            }
        }
        cahier.getPageCourante().deselectionnerNode();
        cahier.notifierObservateurs();
    }

    @FXML
    void ajouterTexte(ActionEvent event) {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nouvelle node texte");
        text.setHeaderText("Que veux-tu écrire à cet emplacement? :)");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            if(cahier.getPageCourante().getNodeIG(this.getIntNodeSelected()) != null)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous remplacer la node actuelle?");
                ButtonType buttonYes = new ButtonType("Oui");
                ButtonType buttonNo = new ButtonType("Non");
                alert.getButtonTypes().setAll(buttonYes, buttonNo);
                Optional<ButtonType> res = alert.showAndWait();

                if (res.isPresent() && res.get() == buttonYes) {
                    cahier.getPageCourante().setNodeIG(new NodeTexteIG(e.toString()),getIntNodeSelected());
                }
            }else
            {
                cahier.getPageCourante().setNodeIG(new NodeTexteIG(e.toString()),getIntNodeSelected());
            }
        });
        cahier.getPageCourante().deselectionnerNode();

        cahier.notifierObservateurs();
    }

    @FXML
    void changerStylePage(ActionEvent event) {

    }

    @FXML
    void changerTitre()
    {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nouveau titre");
        text.setHeaderText("Choisis le titre de cette page");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            try {
                cahier.getPageCourante().setTitre(e.toString());


            } catch (CahierException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors du changement du titre!");
                alert.setContentText(e);
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(5));
                p.setOnFinished(event-> alert.close());

                p.play();
                throw new RuntimeException(ex);
            }
        });

        cahier.notifierObservateurs();
    }

    @FXML
    void pagePrecedente(ActionEvent event) {
        panneauDeControle.jourPrecedent();
        panneauDeControle.chargerPageActuelle();
        cahier.notifierObservateurs();
    }

    @FXML
    void pageSuivante(ActionEvent event) {
        panneauDeControle.jourSuivant();
        panneauDeControle.chargerPageActuelle();

        cahier.notifierObservateurs();

    }

    @FXML
    void selectionnerNode(MouseEvent event)
    {
        //System.out.println(event.getSource().toString());

        if(event.getSource() == nodeSelected)
        {
            nodeSelected = null;
            cahier.getPageCourante().setNodeSelectionnee(0);
        }else {
            nodeSelected = (Pane) event.getSource();
        }

        //System.out.println("La node séléctionnée : " + nodeSelected);
        //System.out.println("La node séléctionnée dans la page : " + cahier.getPageCourante().getNodeSelectionnee());
        cahier.notifierObservateurs();
    }

    public int getIntNodeSelected()
    {
        if(nodeSelected == null)
        {
            return -1;
        }
        else if(nodeSelected == smallNode1)
        {
            return 2;
        }
        else if(nodeSelected == smallNode2)
        {
            return 3;
        }
        else if(nodeSelected == smallNode3)
        {
            return 5;
        }
        else if(nodeSelected == smallNode4)
        {
            return 4;
        }
        return -1;

    }

    @FXML
    void fermerCarnet(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void retourPageDeGarde(ActionEvent event) {
        panneauDeControle.retourPageDeGarde();
    }

    @FXML
    void ajouterNouvellePage(ActionEvent event) {

        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nouvelle page");
        text.setHeaderText("Veuillez renseignez la date de la nouvelle page au format DD/MM/YYYY");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            try {
                panneauDeControle.ajouterPage(new DateCahier(e),"Nouvelle page");
            } catch (CahierException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    void supprimerPage(ActionEvent event) {
        cahier.supprimerPage(cahier.getCourante());
        panneauDeControle.retourPageDeGarde();
    }

    @FXML
    void supprimerNode(ActionEvent event) {
        cahier.getPageCourante().supprimerNodeIG(getIntNodeSelected());
        cahier.getPageCourante().deselectionnerNode();
        cahier.notifierObservateurs();
    }


    @Override
    public void reagir() {
        PageIG page = null;




        if(cahier.getCourante() != null) {
            page = cahier.getPageCourante();
            //System.out.println(page.toString());
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            smallNode1.getChildren().clear();
            smallNode2.getChildren().clear();
            smallNode3.getChildren().clear();
            smallNode4.getChildren().clear();

            smallNode1.setStyle("-fx-background-color: #C7DCD5");
            smallNode2.setStyle("-fx-background-color: #C7DCD5");
            smallNode3.setStyle("-fx-background-color: #C7DCD5");
            smallNode4.setStyle("-fx-background-color: #C7DCD5");


            

            //S'occupe de la node en bas à gauche
            if(page.getSmallNodeLeftBottom() != null)
            {
                if(page.getSmallNodeLeftBottom().estTexte())
                {

                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeLeftBottom();
                    final URL url = getClass().getResource("/fxml/smallPaneText.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeText controlleurSmallNodeText = new ControlleurSmallNodeText(node);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeText.class)) return controlleurSmallNodeText;
                        
                        else return null;
                    });

                    try {
                        smallNode1.getChildren().add((Pane)fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else if(page.getSmallNodeLeftBottom().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeLeftBottom();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage= new ControlleurSmallNodeImage(node,stage);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeImage.class)) return controlleurSmallNodeImage;
                        
                        else return null;
                    });

                    try {
                        smallNode1.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            else
            {
                smallNode1.setStyle("-fx-background-color: #C7DCD5");
            }

            //S'occupe de la node en bas à droite
            if(page.getSmallNodeRightBottom() != null)
            {
                if(page.getSmallNodeRightBottom().estTexte())
                {
                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeRightBottom();
                    final URL url = getClass().getResource("/fxml/smallPaneText.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeText controlleurSmallNodeText= new ControlleurSmallNodeText(node);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeText.class)) return controlleurSmallNodeText;
                        
                        else return null;
                    });

                    try {
                        smallNode2.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else if(page.getSmallNodeRightBottom().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeRightBottom();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage = new ControlleurSmallNodeImage(node,stage);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeImage.class)) return controlleurSmallNodeImage;
                        
                        else return null;
                    });

                    try {
                        smallNode2.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else
            {
                smallNode2.setStyle("-fx-background-color: #C7DCD5");
            }

            //S'occupe de la node en haut à droite
            if(page.getSmallNodeRightTop() != null)
            {
                if(page.getSmallNodeRightTop().estTexte())
                {
                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeRightTop();
                    final URL url = getClass().getResource("/fxml/smallPaneText.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeText controlleurSmallNodeText= new ControlleurSmallNodeText(node);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeText.class)) return controlleurSmallNodeText;

                        else return null;
                    });

                    try {
                        smallNode3.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else if(page.getSmallNodeRightTop().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeRightTop();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage = new ControlleurSmallNodeImage(node,stage);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeImage.class)) return controlleurSmallNodeImage;

                        else return null;
                    });

                    try {
                        smallNode3.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else
            {
                smallNode3.setStyle("-fx-background-color: #C7DCD5");
            }

            //S'occupe de la node en haut à gauche
            if(page.getSmallNodeLeftTop() != null)
            {
                if(page.getSmallNodeLeftTop().estTexte())
                {
                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeLeftTop();
                    final URL url = getClass().getResource("/fxml/smallPaneText.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeText controlleurSmallNodeText= new ControlleurSmallNodeText(node);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeText.class)) return controlleurSmallNodeText;

                        else return null;
                    });

                    try {
                        smallNode4.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else if(page.getSmallNodeLeftTop().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeLeftTop();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage = new ControlleurSmallNodeImage(node,stage);

                    fxmlLoader.setControllerFactory(ic-> {
                        if(ic.equals(vues.controlleurs.ControlleurSmallNodeImage.class)) return controlleurSmallNodeImage;

                        else return null;
                    });

                    try {
                        smallNode4.getChildren().add((Pane) fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else
            {
                smallNode4.setStyle("-fx-background-color: #C7DCD5");
            }

            //Si aucune node n'est séléctionnée
            if (nodeSelected != null) {
                nodeSelected.setStyle("-fx-background-color: #a5c589");
            }



        }
    }
}