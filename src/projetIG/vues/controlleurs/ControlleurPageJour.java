package vues.controlleurs;

import cahierIG.*;
import exceptions.CahierException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import vues.controlleurs.ControlleurSmallNodeImage;
import vues.controlleurs.ControlleurSmallNodeText;
import vues.Observateur;
import vues.PanneauDeControle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ControlleurPageJour implements Observateur {

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Pane largeNode;

    @FXML
    private Pane smallNode1;

    @FXML
    private Pane smallNode2;

    @FXML
    private Label titrePage;

    @FXML
    private Button tournerDroite;

    @FXML
    private Button tournerGauche;

    @FXML
    private Text textSmallNode1;

    Pane nodeSelected;
    Cahier cahier;
    PanneauDeControle panneauDeControle;

    public ControlleurPageJour(Cahier c, PanneauDeControle panneauDeControle) {
        this.cahier = c;
        this.panneauDeControle = panneauDeControle;
        panneauDeControle.controlleurPageJour=this;
        c.ajouterObservateur(this);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        System.out.println("Initialisation...");
        PageJourIG page = null;
        try {
            page = (PageJourIG)cahier.getPage(cahier.getCourante());
            System.out.println(page);
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            this.largeNode.getChildren().clear();
            this.smallNode1.getChildren().clear();
            this.smallNode2.getChildren().clear();




        } catch (CahierException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void ajouterGPS(ActionEvent event) {

    }

    @FXML
    void ajouterPhoto(ActionEvent event) {

    }

    @FXML
    void ajouterTexte(ActionEvent event) {
        NodeTexteIG node = new NodeTexteIG();
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nouvelle node texte");
        text.setHeaderText("Que veux-tu écrire à cet emplacement? :)");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            cahier.getPageCourante().setNodeIG(new NodeTexteIG(e), getIntNodeSelected());
        });

        cahier.notifierObservateurs();
    }

    @FXML
    void changerStylePage(ActionEvent event) {

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
        System.out.println(event.getSource().toString());

        if(event.getSource() == nodeSelected)
        {
            nodeSelected = null;
            cahier.getPageCourante().setNodeSelectionnee(0);
        }else {
            nodeSelected = (Pane) event.getSource();
        }

        System.out.println("La node séléctionnée : " + nodeSelected);
        System.out.println("La node séléctionnée dans la page : " + cahier.getPageCourante().getNodeSelectionnee());
        cahier.notifierObservateurs();
    }

    public int getIntNodeSelected()
    {
        if(nodeSelected == null)
        {
            return -1;
        }
        if(nodeSelected == largeNode)
        {
            return 1;
        }else if(nodeSelected == smallNode1)
        {
            return 2;
        }
        else if(nodeSelected == smallNode2)
        {
            return 3;
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


    @Override
    public void reagir() {
        PageJourIG page = null;




        if(cahier.getCourante() != null) {
            page = cahier.getPageCourante();
            System.out.println(page.toString());
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            smallNode1.getChildren().clear();
            smallNode2.getChildren().clear();
            largeNode.getChildren().clear();


            if(page.getLargeNode() != null)
            {
                System.out.println("Il y'a une Node ici" + page.getLargeNode());
                largeNode = page.getLargeNode();
            }else{
                largeNode.setStyle("-fx-background-color: #C7DCD5");
            }

            if(page.getSmallNodeLeft() != null)
            {
                if(page.getSmallNodeLeft().estTexte())
                {

                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeLeft();
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

                }else if(page.getSmallNodeLeft().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeLeft();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage= new ControlleurSmallNodeImage(node);

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
            }else
            {
                smallNode1.setStyle("-fx-background-color: #C7DCD5");
            }

            if(page.getSmallNodeRight() != null)
            {
                if(page.getSmallNodeRight().estTexte())
                {
                    NodeTexteIG node = (NodeTexteIG)page.getSmallNodeRight();
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

                }else if(page.getSmallNodeRight().estImage())
                {
                    NodeImageIG node = (NodeImageIG)page.getSmallNodeRight();
                    final URL url = getClass().getResource("/fxml/smallPaneImage.fxml");
                    // Création du loader.
                    final FXMLLoader fxmlLoader = new FXMLLoader(url);

                    ControlleurSmallNodeImage controlleurSmallNodeImage = new ControlleurSmallNodeImage(node);

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


            if (nodeSelected != null) {
                nodeSelected.setStyle("-fx-background-color: #a5c589");
            }



        }
    }
}